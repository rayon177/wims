package scu.wims.dao.impl;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-31  22:53
 */
//@Repository
//public class UserDAOImpl implements UserDAO {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    @Override
//    public User findByUserNameAndPassWord(String username, String password) {
//        String sql = "select * from user where username= ? and password = ?";
//        User user = null;
//        try {
//            user =  jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    @Override
//    public User findById(Integer id) {
//        String sql = "select * from user where user_id= ? ";
//        User user = null;
//        try {
//            user =  jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//}
