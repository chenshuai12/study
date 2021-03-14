package com.cs.redis.dao.impl;

import com.cs.redis.bean.po.ShopTypeInfoPO;
import com.cs.redis.config.PageRowBounds;
import com.cs.redis.dao.ShopTypeInfoDao;
import com.cs.redis.dao.mapper.ShopTypeInfoPOMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopTypeInfoDaoImplTest {
    @Resource
    private ShopTypeInfoDao shopTypeInfoDao;
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void inset(){
        ShopTypeInfoPO shopTypeInfoPo = new ShopTypeInfoPO();
        shopTypeInfoPo.setTypeId(1);
        shopTypeInfoPo.setTypeName("普通店铺");
        shopTypeInfoDao.insert(shopTypeInfoPo);
    }

    @Test
    public void batchInsert() {
        List<ShopTypeInfoPO> shopTypeInfoPOList =new ArrayList<>();
        for (int i = 20; i < 30; i++){
            ShopTypeInfoPO shopTypeInfoPO = new ShopTypeInfoPO();
            shopTypeInfoPO.setTypeId(i);
            shopTypeInfoPO.setTypeName("店铺类型" + i);
            shopTypeInfoPOList.add(shopTypeInfoPO);
        }
        shopTypeInfoDao.batchInsert(shopTypeInfoPOList);
    }
    @Test
    public void page(){
        PageRowBounds pageRowBounds = new PageRowBounds(0,11);
        List<ShopTypeInfoPO> shopTypeInfoPOS = shopTypeInfoDao.page(pageRowBounds);
        for (ShopTypeInfoPO shopTypeInfoPO : shopTypeInfoPOS){
            System.out.println(shopTypeInfoPO);
            System.out.println(pageRowBounds);
        }
    }
}
