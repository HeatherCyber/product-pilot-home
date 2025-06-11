package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.commodity.entity.AttrAttrgroupRelationEntity;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性表
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-31 11:47:12
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryBaseAttrPage
            (Map<String, Object> params, Long categoryId);

    PageUtils querySaleAttrPage
            (Map<String, Object> params, Long categoryId);

    // 保存 商品属性，并同时保存商品属性和所在属性组的关联关系
    void saveAttrAndRelation(AttrEntity attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    //删除属性组和属性的关联
    void deleteRelation(AttrAttrgroupRelationEntity[]
                                attrAttrgroupRelationEntities);

    //获取某个分组可以关联的属性
    PageUtils getAllowRelationAttr(Map<String, Object> params, Long attrgroupId);
}

