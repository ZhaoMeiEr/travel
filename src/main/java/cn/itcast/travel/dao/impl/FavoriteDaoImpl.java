package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @authon timber
 * @description 收藏 Dao实现类
 * @date 2020/6/25 下午9:20
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /*
     * @Author: timber
     * @Description: 根据 rid和 uid查询是否收藏
     * @Date: 2020/6/25 下午9:22
     * @param rid
     * @param uid
     * @Return
     */
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        }catch (DataAccessException e){
            System.out.println("查询用户是否收藏旅游路线出现错误...");
        }
        return favorite;
    }

    /*
     * @Author: timber
     * @Description: 根据 rid查询收藏次数
     * @Date: 2020/6/26 下午4:58
     * @param rid
     * @Return
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    /*
     * @Author: timber
     * @Description: 添加收藏
     * @Date: 2020/6/26 下午10:35
     * @param rid
     * @param uid
     * @Return
     */
    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?, ?, ?)";
        template.update(sql, rid, new Date(), uid);
    }
}
