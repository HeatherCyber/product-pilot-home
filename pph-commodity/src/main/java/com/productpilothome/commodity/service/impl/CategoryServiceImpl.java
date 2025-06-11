package com.productpilothome.commodity.service.impl;

import com.productpilothome.commodity.vo.Catalog2Vo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.common.utils.Query;

import com.productpilothome.commodity.dao.CategoryDao;
import com.productpilothome.commodity.entity.CategoryEntity;
import com.productpilothome.commodity.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    private List<CategoryEntity>
    getParent_cid(List<CategoryEntity> selectList, Long parentCid) {
        List<CategoryEntity> categoryEntities = selectList.stream().filter(item ->
                item.getParentId().equals(parentCid)).collect(Collectors.toList());
        return categoryEntities;
        // return this.baseMapper.selectList(
        // new QueryWrapper<CategoryEntity>().eq("parent_cid", parentCid));
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        //将数据库的多次查询变为一次
        List<CategoryEntity> selectList = this.baseMapper.selectList(null);
        //1、查出所有分类
        //1.1 查出所有一级分类
        List<CategoryEntity> level1Categories = getParent_cid(selectList, 0L);
        //封装数据
        Map<String, List<Catalog2Vo>> parentCid =
            level1Categories.stream().collect(Collectors.toMap(k -> k.getId().toString(), v ->
            {
                //1、每一个的一级分类,查到这个一级分类的二级分类
                List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getId());
                //2、封装上面的结果
                List<Catalog2Vo> catalog2Vos = null;
                if (categoryEntities != null) {
                    catalog2Vos = categoryEntities.stream().map(l2 -> {
                        Catalog2Vo catalog2Vo =
                            new Catalog2Vo(v.getId().toString(), null, l2.getId().toString(),
                                    l2.getName().toString());
                        //1、找当前二级分类的三级分类封装成 vo
                        List<CategoryEntity> level3Catalog = getParent_cid(selectList, l2.getId());
                        if (level3Catalog != null) {
                            List<Catalog2Vo.Catalog3Vo> catalog3Vos =
                                level3Catalog.stream().map(l3 -> {
                                    //2、封装成指定格式
                                    Catalog2Vo.Catalog3Vo catalog3Vo =
                                            new Catalog2Vo.Catalog3Vo(l2.getId().toString(),
                                                    l3.getId().toString(), l3.getName());
                                    return catalog3Vo;
                                }).collect(Collectors.toList());
                            catalog2Vo.setCatalog3List(catalog3Vos);
                        }
                        return catalog2Vo;
                    }).collect(Collectors.toList());
                }
                return catalog2Vos;
            }));
        return parentCid;
    }

    @Override
    public List<CategoryEntity> getLevel1Categories() {
        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(
                new QueryWrapper<CategoryEntity>().eq("parent_id", 0));
        return categoryEntities;
    }


    @Override
    public Long[] getCascadedCategoryId(Long categoryId) {
        List<Long> cascadedCategoryId = new ArrayList<>();
        getParentCategoryId(categoryId, cascadedCategoryId);
        //将顺序翻转
        Collections.reverse(cascadedCategoryId);
        return cascadedCategoryId.toArray(new Long[cascadedCategoryId.size()]);
    }

    /**
     * 该方法递归的找到 categoryId 的所有父分类 id , 并存放到 categories
     * 注意得到的所有分类 id 是一个逆序的
     *
     * @param categoryId
     * @param categories
     * @return
     */
    private List<Long> getParentCategoryId(Long categoryId, List<Long> categories) {
        //1、将当前分类 id 放入 categories 数组
        categories.add(categoryId);
        CategoryEntity categoryEntity = this.getById(categoryId);
        if (categoryEntity.getParentId() != 0) {
            getParentCategoryId(categoryEntity.getParentId(), categories);
        }
        return categories;
    }

    @Override
    public List<CategoryEntity> listTree() {
        //1、查出所有分类数据
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //2、组装成层级树形结构
        List<CategoryEntity> categoryTree = entities.stream().filter(categoryEntity ->
                categoryEntity.getParentId() == 0 //过滤，返回 1 级分类
        ).map((category) -> {
            //进行 map 映射操作, 给每一个一级分类设置对应的子分类,
            //这个 getChildrenCategories 是递归操作
            category.setChildrenCategories(getChildrenCategories(category, entities));
            return category;
        }).sorted((category1, category2) -> { //排序
            return (category1.getSort() == null ? 0 : category1.getSort()) -
                    (category2.getSort() == null ? 0 : category2.getSort());
        }).collect(Collectors.toList()); //返回集合
        return categoryTree;
    }

    //递归查找所有分类的分类
    private List<CategoryEntity> getChildrenCategories
    (CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId() == root.getId();
        }).map(categoryEntity -> {
            //1、找到子菜单, 并设置
            categoryEntity.setChildrenCategories(getChildrenCategories(categoryEntity, all));
            return categoryEntity;
        }).sorted((category1, category2) -> {
            //2、菜单的排序
            return (category1.getSort() == null ? 0 : category1.getSort()) -
                    (category2.getSort() == null ? 0 : category2.getSort());
        }).collect(Collectors.toList());
        return children;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

}