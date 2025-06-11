package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.commodity.vo.Catalog2Vo;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-04-26 16:09:34
 */
public interface CategoryService extends IService<CategoryEntity> {

    //返回所有分类及其子分类(层级关系-即树形)
    List<CategoryEntity> listTree();

    //找到 cascadedCategoryId 的[第 1 级分类 id, 第 2 级分类 id, 第 3 级分类 id]
    Long[] getCascadedCategoryId(Long categoryId);

    List<CategoryEntity> getLevel1Categories();

    Map<String, List<Catalog2Vo>> getCatalogJson();

    PageUtils queryPage(Map<String, Object> params);



}

