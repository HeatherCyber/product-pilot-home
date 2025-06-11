package com.productpilothome.commodity.web;

import com.productpilothome.commodity.entity.CategoryEntity;
import com.productpilothome.commodity.service.CategoryService;
import com.productpilothome.commodity.vo.Catalog2Vo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Heather
 * @version 1.0
 * 配置访问首页的请求路径
 */

@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;

    @GetMapping(value = {"/", "index.html"})
    private String indexPage(Model model) {
        //1、查出所有的一级分类
        List<CategoryEntity> categoryEntities =
                categoryService.getLevel1Categories();
        model.addAttribute("categories",categoryEntities);
        //默认找的是 "classpath\templates\"+"index"+".html"
        return "index";
    }

    //返回 json 数据
    @GetMapping(value = "/index/catalog.json")
    @ResponseBody
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        Map<String, List<Catalog2Vo>> catalogJson =
                categoryService.getCatalogJson();
        return catalogJson;
    }
}

