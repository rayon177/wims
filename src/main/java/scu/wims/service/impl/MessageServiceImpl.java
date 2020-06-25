package scu.wims.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import scu.wims.entity.User;
import scu.wims.service.MessageService;
import scu.wims.util.HttpUtils;
import scu.wims.util.MailUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-06-14  15:57
 */
@Service
public class MessageServiceImpl implements MessageService {
    /**
     *  给用户邮箱发送天气消息
     * @param users
     */
    @Override
    public void send(ArrayList<User> users) throws IOException {
        for(User user: users){
            //调用接口
            String results = HttpUtils.doGet("https://way.jd.com/jisuapi/weather","city=成都&appkey=e389e8a225facf42b3fe0faa3eb495ca" );
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(results);
            JsonNode result = root.path("result");
            //天气查询结果
            JsonNode res = result.path("result");
            //提取天气指数
            JsonNode index = res.path("index");
            String weather= res.get("weather").toString().replace("\"","");
            String temphigh= res.get("temphigh").toString().replace("\"","");
            String templow= res.get("templow").toString().replace("\"","");

            List<String> indexs = new ArrayList<>();
            for (JsonNode node : index){
                String temp = "";
                temp+= node.get("iname").toString().replace("\"","")+": ";
                temp+= node.get("detail").toString().replace("\"","");
                indexs.add(temp);
            }

            //发送天气信息邮件
            String content ="<div>"+user.getName()+",您好！ "+user.getCity()+"" +
                    "今日天气信息：<br>" +
                    "天气:"+weather+"&nbsp&nbsp最高气温: "+temphigh+"&nbsp&nbsp最低气温: "+templow+"</div>";
            for(int i =0; i<index.size();i++){
                content+="<div>"+indexs.get(i)+"</div>";
            }

            content += "<a href='http://127.0.0.1/'>查看更多天气信息</a>";
            MailUtils.sendMail(user.getEmail(), content, "天气信息推送");
        }
    }
}
