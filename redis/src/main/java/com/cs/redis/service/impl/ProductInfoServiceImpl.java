package com.cs.redis.service.impl;

import com.cs.redis.bean.po.ProductInfoPO;
import com.cs.redis.bean.po.ShopInfoPO;
import com.cs.redis.bean.po.ShopTypeInfoPO;
import com.cs.redis.dao.ProductInfoDao;
import com.cs.redis.dao.ShopInfoDao;
import com.cs.redis.service.ProductInfoService;
import com.cs.redis.service.ShopInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ShopInfoDao shopInfoDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ShopInfoService shopInfoService;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRES_NEW)
    public void insertShopInfo(ShopInfoPO shopInfoPO) {
        shopInfoDao.insert(shopInfoPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRES_NEW)
    public void insertProductInfo(ProductInfoPO productInfoPO,ShopInfoPO shopInfoPO) {
        shopInfoService.insertShopInfo(shopInfoPO);
        productInfoDao.insert(productInfoPO);
    }
}
