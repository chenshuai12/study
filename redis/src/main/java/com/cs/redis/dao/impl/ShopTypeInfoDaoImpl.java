package com.cs.redis.dao.impl;

import com.cs.redis.bean.po.ShopTypeInfoPO;
import com.cs.redis.dao.ShopTypeInfoDao;

import com.cs.redis.dao.mapper.ShopTypeInfoPOMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ShopTypeInfoDaoImpl implements ShopTypeInfoDao {
    @Resource
    private ShopTypeInfoPOMapper shopTypeInfoPOMapper;
    @Override
    public void insert(ShopTypeInfoPO shopTypeInfoPo) {
        shopTypeInfoPOMapper.insert(shopTypeInfoPo);
    }
}
