package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.JSONUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @authon timber
 * @description 用户相关操作 Servlet
 * @date 2020/6/23 下午4:04
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    /**
     * @Author: timber
     * @Description: 用户注册
     * @Date: 2020/6/23 下午4:48
     * @param req
     * @param resp
     * @Return
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // 验证码校验
        String check = req.getParameter("check");
        // 从 session 中获取验证码
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        // 清除 session 中的验证码
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            // 验证码错误
            ResultInfo info = new ResultInfo();
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            JSONUtils.writeValue(info, resp);
            return;
        }
        // 1. 获取数据
        Map<String, String[]> map = req.getParameterMap();
        // 2. 封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = userService.regist(user);
        ResultInfo info = new ResultInfo();
        // 4. 响应结果
        if(flag){
            // 注册成功
            info.setFlag(true);
        }else{
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        JSONUtils.writeValue(info, resp);
    }

    /**
     * @Author: timber
     * @Description: 邮件激活
     * @Date: 2020/6/23 下午4:50
     * @param req
     * @param resp
     * @Return
     */
    public void active(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("code");
        if(code != null){
            boolean flag = userService.active(code);
            String msg = null;
            if(flag){
                // 激活成功
//                msg = "激活成功，请<a href=' " + req.getContextPath() + "/" + "login.html'>登录</a>";
                msg = "激活成功，请<a href=' " + req.getContextPath() + "/login.html'>登录</a>";
            }else{
                // 激活失败
                msg = "激活失败";
            }
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(msg);
        }
    }

    /**
     * @Author: timber
     * @Description: 用户登录
     * @Date: 2020/6/23 下午4:47
     * @param req
     * @param resp
     * @Return
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 验证码校验
        String check = req.getParameter("check");
        // 从 session 中获取验证码
        HttpSession session = req.getSession();
        String check_server = (String) session.getAttribute("CHECKCODE_SERVER");
        // 清除 session 中的验证码
        session.removeAttribute("CHECKCODE_SERVER");
        if(check_server == null || !check_server.equalsIgnoreCase(check)){
            // 验证码错误
            ResultInfo info = new ResultInfo();
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            JSONUtils.writeValue(info, resp);
            return;
        }
        // 获取用户名和密码数据
        Map<String, String[]> map = req.getParameterMap();
        // 封装 User 对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User u = userService.login(user);
        ResultInfo info = new ResultInfo();
        // 判断用户对象是否为 null
        if(u == null){
            // 用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        // 判断用户是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            // 用户未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }
        // 判断登录成功
        if(u != null && "Y".equals(u.getStatus())){
            // 登录成功
            info.setFlag(true);
            // 将用户信息存入 session
            req.getSession().setAttribute("user", u);
        }
        // 响应数据
        JSONUtils.writeValue(info, resp);
    }

    /**
     * @Author: timber
     * @Description: 从 session 中获取 user 对象
     * @Date: 2020/6/23 下午5:00
     * @param req
     * @param resp
     * @Return
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // 从 session 中获取登录用户信息
        Object user = req.getSession().getAttribute("user");
        // 将 user 写回浏览器
        JSONUtils.writeValue(user, resp);
    }

    /**
     * @Author: timber
     * @Description: 退出登录
     * @Date: 2020/6/23 下午5:00
     * @param req
     * @param resp
     * @Return
     */
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // 销毁 session 中的 user 对象
        req.getSession().invalidate();
        // 重新跳转到登录页面
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
