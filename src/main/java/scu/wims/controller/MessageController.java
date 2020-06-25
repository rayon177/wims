package scu.wims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scu.wims.entity.User;
import scu.wims.service.MessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-06-14  15:03
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping("send")
    public Map<String ,Object> send(@RequestBody ArrayList<User> users){
        System.out.println(users);
        HashMap<String,Object> msg = new HashMap<>();
        try {
            messageService.send(users);
            msg.put("status",true);
            msg.put("msg","推送天气信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  msg;
    }

}
