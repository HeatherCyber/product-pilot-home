package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku 的销售属性/值表
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-06-07 11:36:23
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

