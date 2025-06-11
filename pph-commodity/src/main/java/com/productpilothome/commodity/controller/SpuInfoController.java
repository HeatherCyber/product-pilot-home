package com.productpilothome.commodity.controller;

import java.util.Arrays;
import java.util.Map;

import com.productpilothome.commodity.vo.SpuSaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.productpilothome.commodity.entity.SpuInfoEntity;
import com.productpilothome.commodity.service.SpuInfoService;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.common.utils.R;



/**
 * 商品 spu 信息
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-06-04 11:19:59
 */
@RestController
@RequestMapping("commodity/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    //商品上架接口
    @PostMapping(value = "/{spuId}/up")
    public R spuUp(@PathVariable("spuId") Long spuId) {
        spuInfoService.up(spuId);
        return R.ok();
    }
    //商品下架接口
    @PostMapping(value = "/{spuId}/down")
    public R spuDown(@PathVariable("spuId") Long spuId) {
        spuInfoService.down(spuId);
        return R.ok();
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:spuinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        //PageUtils page = spuInfoService.queryPage(params);
        //带条件分页查询
        PageUtils page = spuInfoService.queryPageByCondition(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:spuinfo:info")
    public R info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:spuinfo:save")
    public R save(@RequestBody SpuSaveVO spuSaveVO){
        spuInfoService.saveSpuInfo(spuSaveVO);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:spuinfo:update")
    public R update(@RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:spuinfo:delete")
    public R delete(@RequestBody Long[] ids){
		spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
