package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.commodity.entity.BrandEntity;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联表
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-24 16:20:01
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 1.增加一个保存 commodity_category_brand_relation 表全部字段的方法
     * 2.包括这个表的 brand_name 和 category_name
     */
    void saveAll(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 增加方法, 返回指定 categoryId 的所有品牌
     */
    List<BrandEntity> getBrandsByCatId(Long catId);
}

