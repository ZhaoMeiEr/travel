package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import cn.itcast.travel.util.JSONUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @authon timber
 * @description 旅游线路 Servlet
 * @date 2020/6/24 下午3:44
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /*
     * @Author: timber
     * @Description: 根据指定分类分页查询旅游路线
     * @Date: 2020/6/24 下午4:02
     * @param req
     * @param resp
     * @Return
     */
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // 接收参数
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");
        // 接收 rname 线路名称
        String rname = req.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        ResultInfo resultInfo = new ResultInfo();
        // 处理参数
        int cid = 0;
        if(cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        // 如果未指定 currentPage 默认currentPage 为1
        int currentPage = 1;
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        // 如果未指定 pageSize 默认pageSize 为5
        int pageSize = 5;
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);
        resultInfo.setFlag(true);
        resultInfo.setData(pageBean);
//        else{
//            resultInfo.setFlag(false);
//            resultInfo.setErrorMsg("没有查询到数据");
//
//        }
        JSONUtils.writeValue(resultInfo, resp);
    }

    /*
     * @Author: timber
     * @Description: 根据 rid 查询一个旅游线路的详细信息
     * @Date: 2020/6/25 下午7:09
     * @param req
     * @param resp
     * @Return
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String ridStr = req.getParameter("rid");
        int rid = 0;
        if(ridStr != null && ridStr.length() > 0 && !"null".equals(ridStr)){
            rid = Integer.parseInt(ridStr);
        }
        Route route = routeService.findOne(rid);
        JSONUtils.writeValue(route, resp);
    }

    /*
     * @Author: timber
     * @Description: 判断当前用户是否收藏当前旅游线路
     * @Date: 2020/6/25 下午9:30
     * @param req
     * @param resp
     * @Return
     */
    public void isFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // 1. 获取线路id
        String ridStr = req.getParameter("rid");
        int rid = 0;
        if(ridStr != null && ridStr.length() > 0 && !"null".equals(ridStr)){
            rid = Integer.parseInt(ridStr);
        }
        // 2. 获取当前登录的 user
        User user = (User) req.getSession().getAttribute("user");
        int uid; // 用户id
        if(user == null){
            // 用户尚未登录
            uid = 0;
        }else{
            // 用户已经登录
            uid = user.getUid();
        }
        // 3. 调用 FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        JSONUtils.writeValue(flag, resp);
    }

    public void addFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // 1. 获取线路 rid
        String ridStr = req.getParameter("rid");
        int rid = 0;
        if(ridStr != null && ridStr.length() > 0 && !"null".equals(ridStr)){
            rid = Integer.parseInt(ridStr);
        }
        // 2. 获取当前登录的 user
        User user = (User) req.getSession().getAttribute("user");
        int uid; // 用户id
        if(user == null){
            // 用户尚未登录
            return ;
        }else{
            // 用户已经登录
            uid = user.getUid();
        }
        // 3. 调用 service 添加
        favoriteService.add(rid, uid);
    }
}
