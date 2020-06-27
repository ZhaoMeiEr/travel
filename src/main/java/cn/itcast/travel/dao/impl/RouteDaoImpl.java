package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @authon timber
 * @description 旅游路线 Dao 实现类
 * @date 2020/6/24 下午3:19
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /*
     * @Author: timber
     * @Description: 查询指定分类的旅游路线的总记录数
     * @Date: 2020/6/24 下午3:21
     * @param cid
     * @Return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
//        String sql = "select count(*) from tab_route where cid = ?";
        // 定义 sql 模版
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();
        // 判断参数是否有值
        if(cid != 0){
            sb.append(" and cid = ? ");
            params.add(cid); // 添加 ? 对应的值
        }
        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ?");
            params.add("%" + rname + "%");
        }
        sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /*
     * @Author: timber
     * @Description: 分页查询指定分类的旅游线路
     * @Date: 2020/6/24 下午3:24
     * @param cid
     * @param start
     * @param pageSize
     * @Return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//        String sql = "select * from tab_route where cid = ? limit ? , ?";
        String sql = " select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();
        // 判断参数是否有值
        if(cid != 0){
            sb.append(" and cid = ? ");
            params.add(cid); // 添加 ? 对应的值
        }
        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ?");
            params.add("%" + rname + "%");
        }
        sb.append(" limit ? , ? "); // 分页条件
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    /*
     * @Author: timber
     * @Description: 根据 rid 查询旅游线路
     * @Date: 2020/6/25 下午7:15
     * @param rid
     * @Return
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }
}
