package com.cs.redis.dao.impl;

import com.cs.redis.bean.bo.ShopNumBO;
import com.cs.redis.bean.po.ShopInfoPO;
import com.cs.redis.dao.ShopInfoDao;
import com.cs.redis.dao.mapper.ShopInfoPOMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ShopInfoDaoImpl implements ShopInfoDao {
    @Resource
    private ShopInfoPOMapper shopInfoPOMapper;

    @Override
    public void insert(ShopInfoPO shopInfoPO) {
        shopInfoPOMapper.insert(shopInfoPO);
    }

    @Override
    public List<ShopNumBO> getShopNum() {
        return shopInfoPOMapper.getShopNum();
    }
}
