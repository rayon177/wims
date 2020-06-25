package scu.wims.mapper;

import org.apache.ibatis.annotations.*;
import scu.wims.entity.User;

import java.util.List;

/**
 * @Description  放在mapper下面的 用maybatis生成实现类
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:46
 */
@Mapper
public interface UserDAO {
//    根据用户名密码查询用户
//    @Select("select * from user where username=#{username} and password = #{password}")
    User findByUserNameAndPassWord(@Param("username") String  username,@Param("password") String password);
//  根据id查询用户
    @Select("select * from user where user_id= #{id}")
    User findById(Integer id);
    @Select("select * from user")
    List<User> findAllUsers();

    @Delete("delete from user where user_id = #{id}")
    //根据id删除一个用户
    void deleteUser(String id);

    @Insert("insert into user(username,password,email,city,user_type,name)" +
            "values(#{username},#{password},#{email},#{city},#{user_type},#{name})")
    void save(User user);

    @Update("update user set username=#{username},password=#{password},email=#{email}," +
            "city=#{city},user_type=#{user_type},name=#{name} where user_id =#{user_id}"  )
    void update(User user);

    @Select("select * from user where username = #{username}")
    User findByUsername(User user);
}
