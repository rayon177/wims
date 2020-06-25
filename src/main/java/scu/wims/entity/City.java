package scu.wims.entity;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:57
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
     城市实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class City {
    private Long city_code;//城市代码
    private String city_name;//城市名称

}
