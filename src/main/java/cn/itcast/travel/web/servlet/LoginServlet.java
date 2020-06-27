package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.JSONUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @authon timber
 * @description 用户登录 Servlet
 * @date 2020/6/22 下午5:51
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 验证码校验
//        String check = req.getParameter("check");
//        // 从 session 中获取验证码
//        HttpSession session = req.getSession();
//        String check_server = (String) session.getAttribute("CHECKCODE_SERVER");
//        // 清除 session 中的验证码
//        session.removeAttribute("CHECKCODE_SERVER");
//        if(check_server == null || !check_server.equalsIgnoreCase(check)){
//            // 验证码错误
//            ResultInfo info = new ResultInfo();
//            // 注册失败
//            info.setFlag(false);
//            info.setErrorMsg("验证码错误");
//            JSONUtils.ObjectToJsonForResultInfo(info, resp);
//            return;
//        }
//        // 获取用户名和密码数据
//        Map<String, String[]> map = req.getParameterMap();
//        // 封装 User 对象
//        User user = new User();
//        try {
//            BeanUtils.populate(user, map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        // 调用 Service 查询
//        UserService service = new UserServiceImpl();
//        User u = service.login(user);
//        ResultInfo info = new ResultInfo();
//        // 判断用户对象是否为 null
//        if(u == null){
//            // 用户名或密码错误
//            info.setFlag(false);
//            info.setErrorMsg("用户名或密码错误");
//        }
//        // 判断用户是否激活
//        if(u != null && !"Y".equals(u.getStatus())){
//            // 用户未激活
//            info.setFlag(false);
//            info.setErrorMsg("您尚未激活，请激活");
//        }
//        // 判断登录成功
//        if(u != null && "Y".equals(u.getStatus())){
//            // 登录成功
//            info.setFlag(true);
//            // 将用户信息存入 session
//            req.getSession().setAttribute("user", u);
//        }
//        // 响应数据
//        JSONUtils.ObjectToJsonForResultInfo(info, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
