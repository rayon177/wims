package scu.wims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scu.wims.entity.User;
import scu.wims.mapper.UserDAO;
import scu.wims.service.UserService;

import java.util.List;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  23:23
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User login(User user) {
        return userDAO.findByUserNameAndPassWord(user.getUsername(),user.getPassword());
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return  userDAO.findAllUsers();
    }

    @Override
    public void deleteUser(String id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public boolean findByUsername(User user) {
       if(userDAO.findByUsername(user)==null){
           return  false;
       }
       else  return  true;
    }

}
