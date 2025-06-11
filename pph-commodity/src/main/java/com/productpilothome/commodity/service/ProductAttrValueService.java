package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu 基本属性值
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-06-06 17:25:23
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void saveProductAttr(List<ProductAttrValueEntity> collect);
}

