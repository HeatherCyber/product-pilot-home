package com.productpilothome.commodity.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.productpilothome.commodity.entity.AttrEntity;
import com.productpilothome.commodity.service.AttrService;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.common.utils.R;



/**
 * 商品属性表
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-31 11:47:12
 */
@RestController
@RequestMapping("commodity/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    //新增API：返回商品基本属性信息, 根据 categoryId+查询条件，返回分页查询结果
    @GetMapping("/base/list/{categoryId}")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("categoryId") Long categoryId){
        PageUtils page = attrService.queryBaseAttrPage(params,categoryId);
        return R.ok().put("page", page);
    }

    //新增API: 返回商品销售属性信息, 根据 categoryId+查询条件，返回分页查询结果
    @GetMapping("/sale/list/{categoryId}")
    public R saleAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("categoryId") Long categoryId){
        PageUtils page = attrService.querySaleAttrPage(params,categoryId);
        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
//    @RequiresPermissions("commodity:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:attr:save")
    public R save(@RequestBody AttrEntity attr){
//		attrService.save(attr);
        attrService.saveAttrAndRelation(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:attr:update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
