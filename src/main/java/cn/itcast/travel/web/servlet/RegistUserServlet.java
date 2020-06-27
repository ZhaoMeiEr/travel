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
 * @description 用户注册 Servlet
 * @date 2020/6/21 下午6:43
 */
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 验证码校验
//        String check = req.getParameter("check");
//        // 从 session 中获取验证码
//        HttpSession session = req.getSession();
//        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
//        // 清除 session 中的验证码
//        session.removeAttribute("CHECKCODE_SERVER");
//        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
//            // 验证码错误
//            ResultInfo info = new ResultInfo();
//            // 注册失败
//            info.setFlag(false);
//            info.setErrorMsg("验证码错误");
//            JSONUtils.ObjectToJsonForResultInfo(info, resp);
//
//            // 将 info 对象序列化为 json
////            ObjectMapper mapper = new ObjectMapper();
////            String json = mapper.writeValueAsString(info);
////            // 将 json 数据写回客户端
////            // 设置 content-type
////            resp.setContentType("application/json;charset-utf-8");
////            resp.getWriter().write(json);
//            return;
//        }
//        // 1. 获取数据
//        Map<String, String[]> map = req.getParameterMap();
//        // 2. 封装对象
//        User user = new User();
//        try {
//            BeanUtils.populate(user, map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        // 3. 调用 service 完成注册
//        UserService userService = new UserServiceImpl();
//        boolean flag = userService.regist(user);
//        ResultInfo info = new ResultInfo();
//        // 4. 响应结果
//        if(flag){
//            // 注册成功
//            info.setFlag(true);
//        }else{
//            // 注册失败
//            info.setFlag(false);
//            info.setErrorMsg("注册失败");
//        }
//        JSONUtils.ObjectToJsonForResultInfo(info, resp);

        // 将 info 对象序列化为 json
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
//        // 将 json 数据写回客户端
//        // 设置 content-type
//        resp.setContentType("application/json;charset-utf-8");
//        resp.getWriter().write(json);
    }
}
