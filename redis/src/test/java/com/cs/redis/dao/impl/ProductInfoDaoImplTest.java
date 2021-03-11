package com.cs.redis.dao.impl;

import com.cs.redis.bean.po.ProductInfoPO;
import com.cs.redis.dao.mapper.ProductInfoPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoImplTest {
    @Resource
    ProductInfoPOMapper productInfoPOMapper;

    @Test
    public void insert(){
        Random random = new Random();
        // 所有当前已经插入的店铺的id
        Long[] shopIds = new Long[]{
                576903652212473857l,
                576903652845813761l,
                576903652879368193l,
                576903652799676417l,
                576903652917116929l,
                576903652782899200l,
                576903652824842240l,
                576903652862590976l,
                576903652896145408l,
                576903652933894144l
        };

        for (int i=0;i<100;i++) {
            ProductInfoPO productInfoPO = new ProductInfoPO();
            //productInfoPO.setProductId(0L);
            productInfoPO.setProductTitle("商品名称");
            productInfoPO.setProductDesc("商品描述");
            productInfoPO.setProductPrice(new BigDecimal(random.nextInt(1000)));
            productInfoPO.setShopId(shopIds[random.nextInt(shopIds.length)]);
            productInfoPOMapper.insert(productInfoPO);
        }
    }
}