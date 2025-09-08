package com.productpilothome.commodity.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.productpilothome.common.valid.SaveGroup;
import com.productpilothome.common.valid.UpdateGroup;
import com.productpilothome.common.valid.UpdateIsShowGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productpilothome.commodity.entity.BrandEntity;
import com.productpilothome.commodity.service.BrandService;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.common.utils.R;



/**
 * Home product brand
 *
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-04-28 17:01:53
 */
@RestController
@RequestMapping("commodity/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("commodity:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("commodity:brand:info")
    public R info(@PathVariable("id") Long id){
		BrandEntity brand = brandService.getById(id);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     * 1. 如果使用@ControllerAdvice 统一接管异常, 就不用单独封装返回异常信息了,将其注销
     * 2. 异常会统一抛出给@ControllerAdvice 类
     * 3. @Validated({SaveGroup.class}) 表示在执行save API接口时，走的是SaveGroup.class 校验规则
     */
    @RequestMapping("/save")
//    @RequiresPermissions("commodity:brand:save")
    public R save(@Validated({SaveGroup.class}) @RequestBody BrandEntity brand/*, BindingResult result*/){

//        if (result.hasErrors()) {//if errors exist
//            Map<String, String> map = new HashMap<>();
//            //1、get error information
//            result.getFieldErrors().forEach((item) -> {
//                //get error message
//                String message = item.getDefaultMessage();
//                //get error field name
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return R.error(400, "Brand data is invalid ").put("data", map);
//        } else {//if no error, than save
//            brandService.save(brand);
//            return R.ok();
//        }

        brandService.save(brand);
        return R.ok();

    }

    /**
     * 修改
     * 1. @Validated({UpdateGroup.class})
     * 指定在执行update API接口时，走的是UpdateGroup.class 校验规则
     *
     */
    @RequestMapping("/update")
//    @RequiresPermissions("commodity:brand:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 单独验证修改显示状态的 switch 控件： 只需要验证两个参数： id, isshow
     * 指定在执行以下API接口时，走的是UpdateIsShowGroup.class 校验规则
     */
    @RequestMapping("/update/isshow")
    public R updateIsShow(@Validated({UpdateIsShowGroup.class}) @RequestBody
                          BrandEntity brand) {
        brandService.updateById(brand);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //    @RequiresPermissions("commodity:brand:delete")
    public R delete(@RequestBody Long[] ids){
		brandService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
