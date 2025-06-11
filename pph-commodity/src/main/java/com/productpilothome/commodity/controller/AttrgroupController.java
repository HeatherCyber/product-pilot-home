package com.productpilothome.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.productpilothome.commodity.entity.AttrAttrgroupRelationEntity;
import com.productpilothome.commodity.entity.AttrEntity;
import com.productpilothome.commodity.service.AttrAttrgroupRelationService;
import com.productpilothome.commodity.service.AttrService;
import com.productpilothome.commodity.service.CategoryService;
import com.productpilothome.commodity.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.productpilothome.commodity.entity.AttrgroupEntity;
import com.productpilothome.commodity.service.AttrgroupService;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.common.utils.R;

import javax.annotation.Resource;


/**
 * 家居商品属性分组
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-24 12:29:03
 */
@RestController
@RequestMapping("commodity/attrgroup")
public class AttrgroupController {

    @Resource
    private AttrgroupService attrgroupService;
    @Resource
    private CategoryService categoryService;
    @Resource
    AttrService attrService;
    @Resource
    AttrAttrgroupRelationService attrAttrgroupRelationService;

    //获取某个分类下的所有属性分组和基本属性
    @GetMapping("/{categoryId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("categoryId")Long categoryId){
        //1、查出当前分类下的所有属性分组，
        //2、查出每个属性分组的所有属性
        List<AttrGroupWithAttrsVo> vos =
                attrgroupService.getAttrGroupWithAttrsByCategoryId(categoryId);
        return R.ok().put("data",vos);
    }

    @PostMapping("/attr/relation")
    public R addRelation(
            @RequestBody List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities){
        //AttrAttrgroupRelationService 本身就提供批量保存的方法.
        attrAttrgroupRelationService. saveBatch(attrAttrgroupRelationEntities);
        return R.ok();
    }

    @GetMapping("/{attrgroupId}/attr/allowrelation")
    public R attrAllowRelation(@PathVariable("attrgroupId") Long attrgroupId, @RequestParam Map<String, Object> params){
        PageUtils page = attrService.getAllowRelationAttr(params,attrgroupId);
        return R.ok().put("page",page);
    }

    /**
     * 响应删除属性组合属性的关联关系
     */
    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrAttrgroupRelationEntity[]
                                    attrAttrgroupRelationEntities){
        attrService.deleteRelation(attrAttrgroupRelationEntities);
        return R.ok();
    }


    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> entities = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data",entities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrgroupService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 增加根据家居分类(第三级的)+查询条件+分页 API 接口
     */
    @RequestMapping("/list/{categoryId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("categoryId") Long categoryId) {
        PageUtils page = attrgroupService.queryPage(params, categoryId);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:attrgroup:info")
    public R info(@PathVariable("id") Long id){
		AttrgroupEntity attrgroup = attrgroupService.getById(id);

        //获取该属性分组对应的 categoryId
        Long categoryId = attrgroup.getCategoryId();
        Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(categoryId);
        attrgroup.setCascadedCategoryId(cascadedCategoryId);
        return R.ok().put("attrgroup", attrgroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:attrgroup:save")
    public R save(@RequestBody AttrgroupEntity attrgroup){
		attrgroupService.save(attrgroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:attrgroup:update")
    public R update(@RequestBody AttrgroupEntity attrgroup){
		attrgroupService.updateById(attrgroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("commodity:attrgroup:delete")
    public R delete(@RequestBody Long[] ids){
		attrgroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
