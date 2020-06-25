package scu.wims.entity;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:56
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
       用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    private Integer user_id;//用户id
    private String username;//用户名，账号
    private String password;//密码
    private String email;//邮箱
    private String city;//默认城市
    private String user_type;//用户类型 权限类型
    private String name;//用户姓名

}
