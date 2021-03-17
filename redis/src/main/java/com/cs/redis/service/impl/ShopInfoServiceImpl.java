package com.cs.redis.service.impl;

import com.cs.redis.bean.po.ProductInfoPO;
import com.cs.redis.bean.po.ShopInfoPO;
import com.cs.redis.dao.ProductInfoDao;
import com.cs.redis.dao.ShopInfoDao;
import com.cs.redis.service.ShopInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ShopInfoServiceImpl implements ShopInfoService {
    @Autowired
    private ShopInfoDao shopInfoDao;
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRES_NEW)
    public void insertShopInfo(ShopInfoPO shopInfoPO) {
        shopInfoDao.insert(shopInfoPO);
    }

}
