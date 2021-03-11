package com.cs.redis.dao.impl;

import com.cs.redis.bean.po.ShopTypeInfoPO;
import com.cs.redis.dao.ShopTypeInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopTypeInfoDaoImplTest {
    @Resource
    private ShopTypeInfoDao shopTypeInfoDao;

    @Test
    public void inset(){
        ShopTypeInfoPO shopTypeInfoPo = new ShopTypeInfoPO();
        shopTypeInfoPo.setTypeId(1);
        shopTypeInfoPo.setTypeName("普通店铺");
        shopTypeInfoDao.insert(shopTypeInfoPo);
    }
}