package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku 图片
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-06-07 00:13:56
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

