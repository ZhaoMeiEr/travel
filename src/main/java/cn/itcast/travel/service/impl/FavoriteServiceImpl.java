package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @authon timber
 * @description 收藏 Service 实现类
 * @date 2020/6/25 下午9:18
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /*
     * @Author: timber
     * @Description: 判断用户是否收藏旅游路线
     * @Date: 2020/6/25 下午9:27
     * @param rid
     * @param uid
     * @Return
     */
    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);
        return favorite != null;
    }

    /*
     * @Author: timber
     * @Description: 添加收藏
     * @Date: 2020/6/26 下午10:36
     * @param rid
     * @param uid
     * @Return
     */
    @Override
    public void add(int rid, int uid) {
        favoriteDao.add(rid, uid);
    }
}
