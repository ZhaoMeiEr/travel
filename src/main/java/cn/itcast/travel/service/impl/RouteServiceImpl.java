package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImageDao;
import cn.itcast.travel.dao.impl.*;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @authon timber
 * @description 旅游线路 Service 实现类
 * @date 2020/6/24 下午3:27
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImageDao routeImageDao = new RouteImageDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /*
     * @Author: timber
     * @Description: 分页查询指定分类的旅游线路
     * @Date: 2020/6/24 下午3:44
     * @param cid
     * @param currentPage
     * @param pageSize
     * @Return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        // 封装 PageBean
        PageBean<Route> pageBean = new PageBean<>();
        // 封装当前页码
        pageBean.setCurrentPage(currentPage);
        // 封装每页显示条数
        pageBean.setPageSize(pageSize);
        // 封装旅游路线总记录数
        int totalCount = routeDao.findTotalCount(cid, rname);
        pageBean.setTotalCount(totalCount);

        // 封装当前页显示的数据集合

        // 计算开始的记录数 算法 -> (当前页 - 1) * 每页的显示条数
//        int start = (currentPage - 1) * pageSize;
        int start = pageBean.handleStart(currentPage, pageSize);
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pageBean.setList(list);

        // 封装总页数

        // 计算总页数 算法 -> 总记录数 % 每页显示条数 == 0 ? 总记录数 / 每页显示条数 : (总记录数 / 每页显示条数) + 1
//        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        int totalPage = pageBean.handlePage(totalCount, pageSize);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    /*
     * @Author: timber
     * @Description: 根据 rid查询旅游线路详情
     * @Date: 2020/6/25 下午7:36
     * @param rid
     * @Return
     */
    @Override
    public Route findOne(int rid) {
        // 1. 根据id去route表中查询route对象
        Route route = routeDao.findOne(rid);
        // 2. 根据route的id 查询图片的集合信息
        List<RouteImg> routeImgList = routeImageDao.findByRid(rid);
        // 2.2 将集合设置到route对象中
        route.setRouteImgList(routeImgList);
        // 3. 根据 route的 sid(商家 id) 查询商家
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        // 4. 查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
