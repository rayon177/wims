package scu.wims.dao;

import scu.wims.entity.Weather;

import java.util.List;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:47
 */
public interface WeatherDAO {
    public List<Weather> searchByName(String city);

    public List<Weather> searchByNameHis(String city);
}
