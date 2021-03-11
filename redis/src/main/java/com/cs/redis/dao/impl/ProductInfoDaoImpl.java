package com.cs.redis.dao.impl;

import com.cs.redis.bean.po.ProductInfoPO;
import com.cs.redis.dao.ProductInfoDao;
import com.cs.redis.dao.mapper.ProductInfoPOMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ProductInfoDaoImpl implements ProductInfoDao {
    @Resource
    private ProductInfoPOMapper productInfoPOMapper;
    @Override
    public void insert(ProductInfoPO productInfoPO) {
        productInfoPOMapper.insert(productInfoPO);
    }
}
