package com.cs.redis.dao.impl;

import com.cs.redis.bean.bo.ShopNumBO;
import com.cs.redis.bean.po.ShopInfoPO;
import com.cs.redis.dao.ShopInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopInfoDaoImplTest {

    @Resource
    private ShopInfoDao shopInfoDao;

    @Test
    public void insert(){
        Random random = new Random();
        for (int i = 0;i < 10; i++){
            ShopInfoPO shopInfoPO = new ShopInfoPO();
//            shopInfoPO.setShopId((long) i);
            shopInfoPO.setShopName("店铺名称" + i);
            shopInfoPO.setShopDesc("店铺描述" + i);
            shopInfoPO.setShopType(random.nextInt(2) + 1);
            shopInfoDao.insert(shopInfoPO);
        }
    }
    @Test
    public void getShopNum() {
        List<ShopNumBO> shopNumBOS = shopInfoDao.getShopNum();
        System.out.println(shopNumBOS);
    }
}