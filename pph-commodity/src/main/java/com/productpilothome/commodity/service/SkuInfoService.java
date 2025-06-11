package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.commodity.vo.SearchResult;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku 信息
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-06-06 23:33:09
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    //返回家居网前台,购买用户检索结果
    SearchResult querySearchPageByCondition(Map<String, Object> params);


}

