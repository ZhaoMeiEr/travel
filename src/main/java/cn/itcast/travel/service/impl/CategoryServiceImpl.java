package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @authon timber
 * @description 分类 Service 实现类
 * @date 2020/6/23 下午7:04
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    /*
     * @Author: timber
     * @Description: 利用缓存查询所有分类
     * @Date: 2020/6/23 下午8:38
     * @param
     * @Return
     */
    @Override
    public List<Category> findAll() {
        // 从 redis 中查询
        // 1. 获取 jedis 客户端
        Jedis jedis = JedisUtil.getJedis();
        // 2. 使用 sortedset 排序查询
//        Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        // 3. 判断从 redis 中查询的数据是否为空
        if(categorys == null || categorys.size() == 0){
            System.out.println("从数据库中查询...");
            // redis 中的数据为空 需要从数据库中查询
            cs = categoryDao.findAll();
            // 将数据库查询到的数据存入 redis 的 category key中
            for(int i = 0; i < cs.size(); i++){
                // 将 cid 存为 sortedset 的'分数'
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        }else{
            System.out.println("从 redis 缓存中查询");
            cs = new ArrayList<>();
            // redis 中的数据不为空 但由于从 redis 中取出的数是 set 集合 而这里返回的是 list
            // 所有需要进行转换
            for(Tuple tuple : categorys){
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid( (int) tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
