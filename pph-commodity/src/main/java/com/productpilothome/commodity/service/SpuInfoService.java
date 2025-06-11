package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.commodity.vo.SpuSaveVO;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.SpuInfoEntity;

import java.util.Map;

/**
 * 商品 spu 信息
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-06-04 11:19:59
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVO spuSaveVO);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void up(Long spuId);

    void down(Long spuId);
}

