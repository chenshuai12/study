package com.cs.redis.dao.mapper;

import com.cs.redis.bean.bo.ShopNumBO;
import com.cs.redis.bean.po.ShopInfoPO;
import com.cs.redis.bean.po.ShopInfoPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShopInfoPOMapper {
    int countByExample(ShopInfoPOExample example);

    int deleteByExample(ShopInfoPOExample example);

    int deleteByPrimaryKey(Long shopId);

    int insert(ShopInfoPO record);

    int insertSelective(ShopInfoPO record);

    List<ShopInfoPO> selectByExample(ShopInfoPOExample example);

    ShopInfoPO selectByPrimaryKey(Long shopId);

    int updateByExampleSelective(@Param("record") ShopInfoPO record, @Param("example") ShopInfoPOExample example);

    int updateByExample(@Param("record") ShopInfoPO record, @Param("example") ShopInfoPOExample example);

    int updateByPrimaryKeySelective(ShopInfoPO record);

    int updateByPrimaryKey(ShopInfoPO record);

    List<ShopNumBO> getShopNum();
}