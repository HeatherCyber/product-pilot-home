package com.productpilothome.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.productpilothome.commodity.vo.AttrGroupWithAttrsVo;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.commodity.entity.AttrgroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 家居商品属性分组
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-24 12:29:03
 */
public interface AttrgroupService extends IService<AttrgroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //增加根据家居分类(第三级的)+查询条件+分页 API 接口
    PageUtils queryPage(Map<String, Object> params, Long categoryId);

    /**
     * getAttrGroupWithAttrsByCategoryId
     * 根据分类得到该分类下的所有属性分组和分组下的基本属性
     * 1、查出当前分类下的所有属性分组，
     * 2、查出每个属性分组的所有属性
     */
    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCategoryId(Long categoryId);
}

