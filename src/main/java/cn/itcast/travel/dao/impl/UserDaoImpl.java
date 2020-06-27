package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @authon timber
 * @description 用户 Dao 实现类
 * @date 2020/6/21 下午6:49
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        User user = null;
        try{
            String sql = "select * from tab_user where username = ?";
            /*
                template.queryForObject() 方法若没有查到数据
                不会返回 null
                会直接抛出异常
             */
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
        }catch (Exception e){

        }
        return user;
    }

    /**
     * 保存用户
     * @param user
     */
    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email, status, code)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try{
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception e){

        }
        return user;
    }

    /**
     * 修改指定用户激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql, user.getUid());
    }

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try{
            String sql = "select * from tab_user where username = ? and password = ?";
            /*
                template.queryForObject() 方法若没有查到数据
                不会返回 null
                会直接抛出异常
             */
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        }catch (Exception e){

        }
        return user;
    }
}
