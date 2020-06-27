package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.util.JSONUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @authon timber
 * @description 分类 Servlet
 * @date 2020/6/23 下午7:06
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{
    private CategoryService categoryService = new CategoryServiceImpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // 查询所有分类
        List<Category> list = categoryService.findAll();
        JSONUtils.writeValue(list, resp);
    }

}
