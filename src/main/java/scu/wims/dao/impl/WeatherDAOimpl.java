package scu.wims.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import scu.wims.dao.WeatherDAO;
import scu.wims.entity.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-06-08  20:25
 */
@Repository
public class WeatherDAOimpl  implements WeatherDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Weather> searchByName(String city) {
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
        return weathers;
    }

    @Override
    public List<Weather> searchByNameHis(String city) {
        String sql = "select * from weather_history where city = ? order by date desc limit 730 ";
        List<Weather> weathers = null;
        try {
            weathers = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<Weather>(Weather.class),
                    city);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return weathers;
    }
}
