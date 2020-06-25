package scu.wims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scu.wims.dao.WeatherDAO;
import scu.wims.entity.Weather;
import scu.wims.service.WeatherService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-06-08  20:15
 */
@Service
@Transactional
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    WeatherDAO weatherDAO;
    @Override
    public List<Weather> searchByName(String city) {
        return weatherDAO.searchByName(city);
    }

    @Override
    public ArrayList<ArrayList<Long>> searchByNameHisL(String city) {
        ArrayList<ArrayList<Long>> weatherlist = new ArrayList<>();
        List<Weather> weathers = weatherDAO.searchByNameHis(city);
        for(Weather weather : weathers ){
            ArrayList<Long> temp = new ArrayList<>();
            temp.add(Long.valueOf(weather.getDate().getTime()));
            temp.add(Long.valueOf(weather.getTem_min()));
            weatherlist.add(temp);
        }
        Collections.reverse(weatherlist);
        return weatherlist;
    }

    @Override
    public ArrayList<ArrayList<Long>> searchByNameHisH(String city) {
        ArrayList<ArrayList<Long>> weatherlist = new ArrayList<>();
        List<Weather> weathers = weatherDAO.searchByNameHis(city);
        for(Weather weather : weathers ){
            ArrayList<Long> temp = new ArrayList<>();
            temp.add(Long.valueOf(weather.getDate().getTime()));
            temp.add(Long.valueOf(weather.getTem_max()));
            weatherlist.add(temp);
        }
        Collections.reverse(weatherlist);
        return weatherlist;
    }
}
