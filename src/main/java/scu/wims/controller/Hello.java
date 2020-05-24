package scu.wims.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
public class Hello {
    @RequestMapping("/hello")
    public Map<String,Object> hello(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","成都");
        return map;
    }
    // RESTAPI的方式
}
