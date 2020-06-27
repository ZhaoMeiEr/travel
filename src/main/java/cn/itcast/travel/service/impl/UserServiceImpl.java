package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @authon timber
 * @description 用户 Service 实现类
 * @date 2020/6/21 下午6:48
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        // 1. 根据用户名查询用户对象
        User u = userDao.findByUserName(user.getUsername());
        // 判断 u 是否为 null
        if(u != null){
            // 用户名存在 注册失败
            return false;
        }
        // 2. 保存用户信息
        // 2.1 设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        // 2.2 设置激活状态
        user.setStatus("N");
        userDao.save(user);

        // 3. 激活邮件发送，邮件正文
        String content = "<a href='http://localhost:8080/travel/user/active?code="+ user.getCode() +"'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    /**
     * 修改激活状态
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        // 1. 根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user != null){
            // 2. 调用 dao 的修改激活码状态的方法
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }



    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
