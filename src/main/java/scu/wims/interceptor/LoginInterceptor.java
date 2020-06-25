package scu.wims.interceptor;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import scu.wims.annotation.NoneAuth;
import scu.wims.entity.TokenModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description  用于验证用户状态的拦截器
 * @Author hyr
 * @Version
 * @Date 2020-06-03  21:57
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    StringRedisTemplate   stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("用户登录状态拦截器");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //如果被@NoneAuth注解代表不需要登录验证，直接通过
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getAnnotation(NoneAuth.class) != null) return true;
        //token验证
        String authStr = request.getHeader("token");
        System.out.println(authStr);
        TokenModel model =  null;
        if(authStr!= null){
            String[] modelArr = authStr.split("_");
            if(modelArr.length == 2) {
                Integer userId = Integer.parseInt(modelArr[0]);
                String token = modelArr[1];
                model = new TokenModel(userId, token);
            }
        }

        //验证通过
        if(check(model)) {
            request.setAttribute("userId", model.getUserId());
            return true;
        }
        //验证未通过
        System.out.println("验证未通过");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Map<String,String> msgs = new HashMap<>();
        msgs.put("code","401");
        msgs.put("msg","用户未登录！");
        writeValue(msgs,response);
        return false;
    }
    public boolean check(TokenModel model){
        boolean result = false;
        if(model != null){
            Integer uid = model.getUserId();
            String  token = model.getToken();
            String  authToken= stringRedisTemplate.opsForValue().get(uid.toString());
            System.out.println(authToken);
            System.out.println(model);
            if(authToken != null && authToken.equals(token)) {
                stringRedisTemplate.expire(uid.toString(), 10*60, TimeUnit.SECONDS);
                result = true;
            }
        }
        return  result;
    }

    public void writeValue(Object obj, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(),obj);
    }
}
