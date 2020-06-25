package scu.wims;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import scu.wims.dao.WeatherDAO;
import scu.wims.entity.TokenModel;
import scu.wims.entity.User;
import scu.wims.entity.Weather;
import scu.wims.util.HttpUtils;
import scu.wims.util.MailUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  19:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WimsApplicationTests {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     *  redis常见五大数据类型
     *  String,List,Set,Hash,ZSet(有序集合）
     */
    @Test
    public  void redistest(){

//        stringRedisTemplate.opsForValue().append("msg","hello");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        TokenModel token = new TokenModel(1001,"123");
//        redisTemplate.opsForValue().set("1001",token);
        Object msg = redisTemplate.opsForValue().get(1001);
        System.out.println(msg);
    }

    @Test
    public void contextLoads() throws SQLException {
        //org.apache.tomcat.jdbc.pool.DataSource
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

    @Test
    public void  testDate() {
        String city ="成都";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String beginDate =   dateFormat.format(new Date().getTime()-24*60*60*1000);
        String endDate =    dateFormat.format(new Date().getTime()+3*24*60*60*1000);
        String sql = "select * from weather_history where city = ? and date >= ? and date <= ?";
        List<Weather> weathers = null;
        try {
            weathers = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<Weather>(Weather.class),
                    city,beginDate,endDate);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        System.out.println(weathers);
    }

    @Test
    public void testSearchByNameHis() {
        String city = "成都";
        String sql = "select * from weather_history where city = ? limit 730 ";
        List<Weather> weathers = null;
        try {
            weathers = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<Weather>(Weather.class),
                    city);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        System.out.println(weathers.get(0).getDate().getTime());
    }

    @Autowired
    WeatherDAO weatherDAO;

    @Test
    public void testSearchByNameHisL() {
        String city = "成都";
        ArrayList<ArrayList<String>> weatherlist = new ArrayList<>();
        List<Weather> weathers = weatherDAO.searchByNameHis(city);
        for(Weather weather : weathers ){
            ArrayList<String> temp = new ArrayList<>();
            temp.add(String.valueOf(weather.getDate().getTime()));
            temp.add(weather.getTem_min());
            weatherlist.add(temp);
        }
        System.out.println(weatherlist);
    }

    @Test
    public void testEmail(){
        User user =new User();
        user.setEmail("396880776@qq.com");
        MailUtils.sendMail(user.getEmail(), "测试邮件", "测试邮件");
    }

    @Test
    public void TestDoGet() throws IOException {
        String results = HttpUtils.doGet("https://way.jd.com/jisuapi/weather","city=成都&appkey=e389e8a225facf42b3fe0faa3eb495ca" );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(results);
        //一层一层提取
        JsonNode result = root.path("result");
        JsonNode res = result.path("result");
        //提取天气指数
        JsonNode index = res.path("index");
    }
}
