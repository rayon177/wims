package scu.wims.service;

import scu.wims.entity.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:44
 */
public interface WeatherService {
    public List<Weather> searchByName(String city);

    public ArrayList<ArrayList<Long>> searchByNameHisL(String city);

    public ArrayList<ArrayList<Long>> searchByNameHisH(String city);
}
