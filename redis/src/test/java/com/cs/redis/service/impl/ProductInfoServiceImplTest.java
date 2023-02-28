package com.cs.redis.service.impl;

import com.cs.redis.bean.po.ProductInfoPO;
import com.cs.redis.bean.po.ShopInfoPO;
import com.cs.redis.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void insertProductInfo(){
        ShopInfoPO shopInfoPO = new ShopInfoPO();
        shopInfoPO.setShopId(1L);
        shopInfoPO.setShopType(1);
        shopInfoPO.setShopDesc("测试店铺");
        shopInfoPO.setShopName("店铺1");
        ProductInfoPO productInfoPO = new ProductInfoPO();
        productInfoPO.setProductId(1L);
        productInfoPO.setProductDesc("测试商品");
        productInfoPO.setProductPrice(new BigDecimal(1));
        productInfoPO.setProductTitle("商品1");
        productInfoPO.setShopId(1L);
        productInfoService.insertProductInfo(productInfoPO,shopInfoPO);
    }
}