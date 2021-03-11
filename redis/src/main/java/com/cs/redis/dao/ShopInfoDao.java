package com.cs.redis.dao;


import com.cs.redis.bean.bo.ShopNumBO;
import com.cs.redis.bean.po.ShopInfoPO;

import java.util.List;

public interface ShopInfoDao {
    public void insert(ShopInfoPO shopInfoPO);
    List<ShopNumBO> getShopNum();
}
