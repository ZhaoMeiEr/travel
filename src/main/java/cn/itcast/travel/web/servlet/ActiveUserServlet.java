package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @authon timber
 * @description 邮件激活 Servlet
 * @date 2020/6/21 下午8:57
 */
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       String code = req.getParameter("code");
//       if(code != null){
//           UserService service = new UserServiceImpl();
//           boolean flag = service.active(code);
//           String msg = null;
//           if(flag){
//               // 激活成功
//               msg = "激活成功，请<a href='login.html'>登录</a>";
//           }else{
//               // 激活失败
//               msg = "激活失败";
//           }
//           resp.setContentType("text/html;charset=utf-8");
//           resp.getWriter().write(msg);
//       }
    }
}
