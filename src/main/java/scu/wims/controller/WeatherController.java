package scu.wims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scu.wims.entity.Weather;
import scu.wims.service.WeatherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:49
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @GetMapping("/searchByName")
    public List<Weather> searchByName(@RequestParam Map<String, String> params){
        String  city = params.get("city");
        List<Weather> weathers = weatherService.searchByName(city);
//        System.out.println(weathers);
        return weathers;
    }

    @GetMapping("/searchByNameHis")
    public ArrayList<Object> searchByNameHisL(@RequestParam Map<String, String> params){
        String  city = params.get("city");
        ArrayList<ArrayList<Long>> weathersL = weatherService.searchByNameHisL(city);
        ArrayList<ArrayList<Long>> weathersH = weatherService.searchByNameHisH(city);
        ArrayList<Object> weathers = new ArrayList<>();
        weathers.add(weathersH);
        weathers.add(weathersL);
        return weathers;
    }

//    @GetMapping("/searchByNameHisH")
//    public ArrayList<ArrayList<String>> searchByNameHisH(@RequestParam Map<String, String> params){
//        String  city = params.get("city");
//        ArrayList<ArrayList<String>> weathers = weatherService.searchByNameHisH(city);
////        System.out.println(weathers);
//        return weathers;
//    }
}
