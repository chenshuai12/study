package com.cs.redis.dao;

import com.cs.redis.bean.po.ShopTypeInfoPO;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ShopTypeInfoDao {
    public void insert(ShopTypeInfoPO shopTypeInfoPO);
    public void batchInsert(List<ShopTypeInfoPO> shopTypeInfoPOList);
    List<ShopTypeInfoPO> page(RowBounds rowBounds);
    List<ShopTypeInfoPO> list();
}
