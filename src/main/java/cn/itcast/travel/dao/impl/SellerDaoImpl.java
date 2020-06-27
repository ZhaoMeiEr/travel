package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @authon timber
 * @description 商家 Dao实现类
 * @date 2020/6/25 下午7:30
 */
public class SellerDaoImpl implements SellerDao{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /*
     * @Author: timber
     * @Description: 根据 sid查询商家
     * @Date: 2020/6/25 下午7:32
     * @param sid
     * @Return
     */
    @Override
    public Seller findById(int sid) {
        String sql = "select * from tab_seller where sid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
    }
}
