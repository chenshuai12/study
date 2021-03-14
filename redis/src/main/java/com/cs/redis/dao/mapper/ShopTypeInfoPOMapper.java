package com.cs.redis.dao.mapper;

import com.cs.redis.bean.po.ShopTypeInfoPO;
import com.cs.redis.bean.po.ShopTypeInfoPOExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ShopTypeInfoPOMapper {
    int countByExample(ShopTypeInfoPOExample example);

    int deleteByExample(ShopTypeInfoPOExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(ShopTypeInfoPO record);

    int insertSelective(ShopTypeInfoPO record);

    List<ShopTypeInfoPO> selectByExample(ShopTypeInfoPOExample example);

    ShopTypeInfoPO selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") ShopTypeInfoPO record, @Param("example") ShopTypeInfoPOExample example);

    int updateByExample(@Param("record") ShopTypeInfoPO record, @Param("example") ShopTypeInfoPOExample example);

    int updateByPrimaryKeySelective(ShopTypeInfoPO record);

    int updateByPrimaryKey(ShopTypeInfoPO record);
    List<ShopTypeInfoPO> page(RowBounds rowBounds);
}