package scu.wims.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  23:08
 */

/**
        天气实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Weather {
    private String city;//城市名
    private Date date;//日期
    private String tem_max;//最高气温
    private String tem_min;//最低气温
    private String wind_power;//风力
    private String wind_direction;//风向
    private String status;//天气描述

}
