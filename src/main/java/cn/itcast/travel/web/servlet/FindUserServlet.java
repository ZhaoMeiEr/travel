package cn.itcast.travel.web.servlet;

import cn.itcast.travel.util.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @authon timber
 * @description 获取用户姓名返回给浏览器 Servlet
 * @date 2020/6/22 下午9:00
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 从 session 中获取登录用户信息
//        Object user = req.getSession().getAttribute("user");
//        // 将 user 写回浏览器
//        JSONUtils.ObjectToJson(user, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
