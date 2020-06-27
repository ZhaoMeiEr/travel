package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @authon timber
 * @description 分类 Dao 实现类
 * @date 2020/6/23 下午7:00
 */
public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /*
     * @Author: timber
     * @Description: 查询所有分类
     * @Date: 2020/6/23 下午7:01
     * @param
     * @Return
     */
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        List<Category> list = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return list;
    }
}
