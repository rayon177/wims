package scu.wims.controller;

import com.alibaba.druid.util.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import scu.wims.annotation.NoneAuth;
import scu.wims.entity.TokenModel;
import scu.wims.entity.User;
import scu.wims.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-17  23:33
 */
//这个类的所有方法返回的数据直接写给浏览器，（如果是对象转为json数据）
/*@ResponseBody
@Controller*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService ;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     *  用户登录
     */
    @NoneAuth
    @PostMapping("/login")
    // 使用自定义对象来接受参数时 不加@RequestParam,前端将参数转化为了url形式
    public Map<String, Object> login( User user) {
        System.out.println(user);
        User u = userService.login(user);
        Map<String, Object> msgs = new HashMap<>();
        if(u== null){
            msgs.put("msg","账号或密码错误，请重新输入");

        }
        else {
            //用UUID随机生成Token
            String token = UUID.randomUUID().toString().replace("-", "");
            TokenModel tokenModel = new TokenModel(u.getUser_id(),token);
            //将token和和用户id存入redis 设置失效时间为10分钟
            stringRedisTemplate.opsForValue().set(u.getUser_id().toString(),tokenModel.getToken(),60 * 10, TimeUnit.SECONDS);
            System.out.println(stringRedisTemplate.opsForValue().get(u.getUser_id().toString()));
            msgs.put("msg","登录成功");
            //将token传回前端
            msgs.put("token",tokenModel);
            msgs.put("city",u.getCity());
            msgs.put("username",u.getUsername());
            msgs.put("usertype",u.getUser_type());
            msgs.put("name",u.getName());

        }
        return msgs;
    }

    /**
     * 返回当前用户权限
     *
     */
    @GetMapping("/usertype")
    public Map<String,String> userType(@RequestParam Integer id){
        User user = userService.findById(id);
        Map<String,String> type = new HashMap<>();
        type.put("userType",user.getUser_type());
        return type;
    }


    /**
        用户管理模块 restful接口
     */
    @GetMapping("/users")
    public List<User> findAllUsers(){
        List<User> users = userService.findAllUsers();
        return  users;
    }

    /**
     *   删除用户
     *
     */
    @DeleteMapping("delete/{id}")
    public Map<String, Object> deleteOne(@PathVariable String id){
        System.out.println("方法被调用");
        HashMap<String, Object> map = new HashMap<>();
        try{
            userService.deleteUser(id);
            map.put("status",true);
            map.put("msg","删除用户成功！");
        }catch (Exception e){
            map.put("msg","删除用户失败！");
            e.printStackTrace();
        }
        return map;
    }

    /**
     *  增加用户或编辑用户
     */
    @PostMapping("saveOrUpdate")
    public Map<String, Object> saveOrUpdate(@RequestBody User user) {
        Map<String, Object> map =new HashMap<>();
        try {
            if (user.getUser_id() == null){
                if(userService.findByUsername(user)){
                    throw  new Exception();
                }
                userService.save(user);
                map.put("status",true);
                map.put("msg","用户信息保存成功!!!");
            }else{
                userService.update(user);
                map.put("status",true);
                map.put("msg","用户信息编辑成功!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","用户名已经在!!!");
        }
        return map;
    }
}
