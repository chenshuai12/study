package com.cs.redis.service;

import com.cs.redis.bean.po.ProductInfoPO;
import com.cs.redis.bean.po.ShopInfoPO;
import com.cs.redis.bean.po.ShopTypeInfoPO;

public interface ProductInfoService {

    public void insertShopInfo(ShopInfoPO shopInfoPO);

    public void insertProductInfo(ProductInfoPO productInfoPO,ShopInfoPO shopInfoPO);
}
