package com.productpilothome.commodity.dao;

import com.productpilothome.commodity.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性和商品属性组的关联表
 * 
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-31 12:57:38
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    /**
     * 增加一个批量上传属性组和属性关联关系的方法
     * @param entities
     */
    void deleteBatchRelation(@Param("entities")
                             List<AttrAttrgroupRelationEntity> entities);
	
}
