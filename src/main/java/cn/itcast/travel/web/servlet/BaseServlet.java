package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @authon timber
 * @description 公共 Servlet 用来分发请求
 * 其它继承了 BaseServlet 的 Servlet 类(UserServlet)一旦被访问 就会去执行 BaseServlet 中的 service() 方法
 * service() 方法是访问 Servlet 的入口方法
 * 因此可以在 BaseServlet 的 service() 方法中完成其他 Servlet 的方法的分发
 * @date 2020/6/23 下午4:04
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 完成方法分发
        // 1. 获取请求路径
        String uri = req.getRequestURI(); // /travel/user/login
        // 2. 获取方法名称
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        // 3. 获取方法对象 Method
        // this -> 当前的 service() 方法被哪个对象调用 this 就代表哪个对象
        // 换句话说就是 当前哪个 Servlet 被访问，this 就代表哪个 Servlet
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 4. 执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
