package com.cs.redis.dao.mapper;

import com.cs.redis.bean.po.ProductInfoPO;
import com.cs.redis.bean.po.ProductInfoPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoPOMapper {
    int countByExample(ProductInfoPOExample example);

    int deleteByExample(ProductInfoPOExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(ProductInfoPO record);

    int insertSelective(ProductInfoPO record);

    List<ProductInfoPO> selectByExample(ProductInfoPOExample example);

    ProductInfoPO selectByPrimaryKey(Long productId);

    int updateByExampleSelective(@Param("record") ProductInfoPO record, @Param("example") ProductInfoPOExample example);

    int updateByExample(@Param("record") ProductInfoPO record, @Param("example") ProductInfoPOExample example);

    int updateByPrimaryKeySelective(ProductInfoPO record);

    int updateByPrimaryKey(ProductInfoPO record);
}