package com.cs.redis.controller;


import com.cs.redis.bean.po.ShopTypeInfoPO;
import com.cs.redis.consumer.Consumer;
import com.cs.redis.dao.ShopInfoDao;
import com.cs.redis.dao.ShopTypeInfoDao;
import com.cs.redis.producer.Producer;
import com.cs.redis.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/db")
public class TestDBController {
    @Autowired
    private ShopTypeInfoDao shopTypeInfoDao;
    @Autowired
    private Consumer consumer;
    @RequestMapping("/cache")
    public List<ShopTypeInfoPO> listShopTypeInfo(){
        return shopTypeInfoDao.list();
    }

}
