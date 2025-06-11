package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.BrandEntity;

import java.util.Map;

/**
 * 家居品牌
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-04-28 17:01:53
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

