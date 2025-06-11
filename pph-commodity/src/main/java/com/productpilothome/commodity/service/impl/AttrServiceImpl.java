package com.productpilothome.commodity.service.impl;

import com.productpilothome.commodity.dao.AttrAttrgroupRelationDao;
import com.productpilothome.commodity.dao.AttrgroupDao;
import com.productpilothome.commodity.entity.AttrAttrgroupRelationEntity;
import com.productpilothome.commodity.entity.AttrgroupEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.common.utils.Query;

import com.productpilothome.commodity.dao.AttrDao;
import com.productpilothome.commodity.entity.AttrEntity;
import com.productpilothome.commodity.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    AttrAttrgroupRelationDao relationDao;
    @Resource
    AttrgroupDao attrgroupDao;

    @Override
    public PageUtils getAllowRelationAttr(Map<String, Object> params, Long attrgroupId) {
        //1. 当前分组只能关联自己所属的分类里面的所有属性
        AttrgroupEntity attrgroupEntity = attrgroupDao.selectById(attrgroupId);
        Long categoryId = attrgroupEntity.getCategoryId();
        //2.当前分组只能关联别的分组没有引用的属性
        //2.1 当前分类下的所有分组
        List<AttrgroupEntity> group = attrgroupDao.selectList(new
                QueryWrapper<AttrgroupEntity>().eq("category_id", categoryId));
        List<Long> collect = group.stream().map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        //2.2这些分组关联的属性
        List<AttrAttrgroupRelationEntity> groupId = relationDao.selectList(new
                QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", collect));
        List<Long> attrIds = groupId.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());
        //2.3从当前分类的所有属性中移除这些属性；
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>(). eq("category_id", categoryId).eq("attr_type",1);
        if(attrIds!=null && attrIds.size()>0){
            wrapper.notIn("attr_id", attrIds);
        }
        //处理查询的条件
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                w.eq("attr_id",key).or().like("attr_name",key);
            });
        }
        IPage<AttrEntity> page =
                this.page(new Query<AttrEntity>().getPage(params), wrapper);
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }


    /**
     * 删除属性组和属性的关联关系
     */
    @Override
    public void deleteRelation(AttrAttrgroupRelationEntity[] attrAttrgroupRelationEntities) {
        relationDao.deleteBatchRelation
                (Arrays.asList(attrAttrgroupRelationEntities));
    }

    /**
     * 获取 attrgroupId 关联的商品属性(基本属性)
     * @param attrgroupId
     * @return
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> entities = relationDao.selectList(new
                QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        //得到所有的属性 id
        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());
        //如果没有关联的属性，返回 null
        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }
        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);
        return (List<AttrEntity>) attrEntities;
    }

    // 查询商品基本属性信息, 根据 categoryId+分页+查询添加
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrEntity> queryWrapper =
                new QueryWrapper<AttrEntity>().eq("attr_type", 1);
        if (categoryId != 0) {//如果为 0, 表示不将 categoryId 作为查询条件
            queryWrapper.eq("category_id", categoryId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params), queryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    // 查询商品销售属性信息, 根据 categoryId+分页+查询添加
    public PageUtils querySaleAttrPage(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrEntity> queryWrapper =
                new QueryWrapper<AttrEntity>().eq("attr_type", 0);
        if (categoryId != 0) {//如果为 0, 表示不将 categoryId 作为查询条件
            queryWrapper.eq("category_id", categoryId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params), queryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    /**
     * 保存商品属性信息，同时保存商品属性和他的商品属性组关联关系
     * 1 @Transactional 开启事务
     * 2 @param attr
     */
    @Transactional
    @Override
    public void saveAttrAndRelation(AttrEntity attr) {
        //1、保存基本数据
        this.save(attr);
        //2、保存关联关系, 如果是基本属性，并且属性组 id 不为空
        if (attr.getAttrType() == 1 && attr.getAttrGroupId() != null) {
            AttrAttrgroupRelationEntity relationEntity = new
                    AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            relationDao.insert(relationEntity);
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

}