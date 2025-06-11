package com.productpilothome.commodity.dao;

import com.productpilothome.commodity.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品 spu 信息
 * 
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-06-04 11:19:59
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    void updaSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
	
}
