package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {
    Seller findById(int sid);
}
