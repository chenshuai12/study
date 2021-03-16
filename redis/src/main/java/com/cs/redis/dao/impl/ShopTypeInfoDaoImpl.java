package com.cs.redis.dao.impl;

import com.cs.redis.bean.po.ShopTypeInfoPO;
import com.cs.redis.bean.po.ShopTypeInfoPOExample;
import com.cs.redis.dao.ShopTypeInfoDao;

import com.cs.redis.dao.mapper.ShopTypeInfoPOMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ShopTypeInfoDaoImpl implements ShopTypeInfoDao {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    private ShopTypeInfoPOMapper shopTypeInfoPOMapper;
    @Override
    public void insert(ShopTypeInfoPO shopTypeInfoPo) {
        shopTypeInfoPOMapper.insert(shopTypeInfoPo);
    }

    @Override
    public void batchInsert(List<ShopTypeInfoPO> shopTypeInfoPOList) {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        ShopTypeInfoPOMapper batchShopTypeInfoPOMapper = session.getMapper(ShopTypeInfoPOMapper.class);
        shopTypeInfoPOList.forEach(v -> {
            batchShopTypeInfoPOMapper.insert(v);
        });
        session.commit();
        session.close();
    }

    @Override
    public List<ShopTypeInfoPO> page(RowBounds rowBounds) {
        return shopTypeInfoPOMapper.page(rowBounds);
    }

    @Override
    @Cacheable(value = {"bankId"},key = "1")
    public List<ShopTypeInfoPO> list(){
        System.out.println("执行了sql");
        ShopTypeInfoPOExample example = new ShopTypeInfoPOExample();
        ShopTypeInfoPOExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdIsNotNull();
        return shopTypeInfoPOMapper.selectByExample(example);
    }
}
