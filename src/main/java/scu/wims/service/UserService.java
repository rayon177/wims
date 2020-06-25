package scu.wims.service;

import scu.wims.entity.User;

import java.util.List;


/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:44
 */
public interface UserService {


    User login(User user);

    User findById(Integer id);

    List<User> findAllUsers();

    void deleteUser(String id);

    void save(User user);

    void update(User user);

    boolean findByUsername(User user);
}
