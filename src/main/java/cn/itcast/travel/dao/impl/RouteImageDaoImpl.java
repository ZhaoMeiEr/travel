package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImageDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @authon timber
 * @description 旅游图片 Dao 实现类
 * @date 2020/6/25 下午7:19
 */
public class RouteImageDaoImpl implements RouteImageDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /*
     * @Author: timber
     * @Description: 根据rid 查询旅游线路图片集合
     * @Date: 2020/6/25 下午7:22
     * @param rid
     * @Return
     */
    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
    }
}
