package com.productpilothome.commodity.dao;

import com.productpilothome.commodity.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类表
 * 
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-04-26 16:09:34
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
