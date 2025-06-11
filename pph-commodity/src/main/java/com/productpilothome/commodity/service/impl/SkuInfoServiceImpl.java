package com.productpilothome.commodity.service.impl;

import com.productpilothome.commodity.dao.SpuInfoDao;
import com.productpilothome.commodity.entity.SpuInfoEntity;
import com.productpilothome.commodity.vo.SearchResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.common.utils.Query;

import com.productpilothome.commodity.dao.SkuInfoDao;
import com.productpilothome.commodity.entity.SkuInfoEntity;
import com.productpilothome.commodity.service.SkuInfoService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Resource
    private SpuInfoDao spuInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
        this.baseMapper.insert(skuInfoEntity);
    }

    @Override
    public SearchResult querySearchPageByCondition
            (Map<String, Object> params) {
        //先查出已经上架的 SpuInfoEntity
        List<SpuInfoEntity> spuInfoEntities = spuInfoDao.selectList(new
                QueryWrapper<SpuInfoEntity>().eq("publish_status", 1));
        //收集上架的 SpuInfoEntity id
        List<Long> spuInfoEntityIdCollect = spuInfoEntities.stream().map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();
        //将 spuInfoEntityIdCollect 加入分页查询条件
        if (spuInfoEntityIdCollect != null && spuInfoEntityIdCollect.size() > 0) {
            queryWrapper.in("spu_id", spuInfoEntityIdCollect);
        } else {
        //没有任何上架的家居商品, 构造一个结果返回，注意不能直接返回 null
        //因为前台要解析的.
            SearchResult searchResult = new SearchResult();
            searchResult.setCommodity(new ArrayList<>());
            searchResult.setPageNum(1);
            searchResult.setTotal(0L);
            searchResult.setTotalPages(0);
            searchResult.setPageNavs(new ArrayList<>());
            searchResult.setKeyword
                    (params.get("keyword") == null ? "" : params.get("keyword").toString());
            return searchResult;
        }
        //带上查询条件
        String key = (String) params.get("keyword");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((wrapper)->{
                wrapper.eq("sku_id",key).or().like("sku_name",key);
            });
        }
        //带上分类[第三级分类]
        String catelogId = (String) params.get("catalog3Id");
        if(!StringUtils.isEmpty(catelogId)&&!"0".equalsIgnoreCase(catelogId)){
            queryWrapper.eq("category_id",catelogId);
        }
        //带上品牌
        String brandId = (String) params.get("brandId");
        if(!StringUtils.isEmpty(brandId)&&!"0".equalsIgnoreCase(brandId)){
            queryWrapper.eq("brand_id",brandId);
        }
        params.put("limit", "2");
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        //将 page 转成前端需要的数据格式[注意，不同前端页面要的结果可能不一样]
        SearchResult searchResult = new SearchResult();
        searchResult.setTotal((long) pageUtils.getTotalCount());
        int totalPage = pageUtils.getTotalPage();
        searchResult.setTotalPages(pageUtils.getTotalPage());
        searchResult.setPageNum(pageUtils.getCurrPage());
        searchResult.setCommodity((List<SkuInfoEntity>) pageUtils.getList());
        List<Integer> pageNavs = new ArrayList<>();
        for (int i = 1; i <= totalPage; i++) {
            pageNavs.add(i);
        }
        searchResult.setPageNavs(pageNavs);
        //把 keyword 保存
        searchResult.setKeyword
                (params.get("keyword") == null ? "" : params.get("keyword").toString());
        return searchResult;
    }


    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();
        //带上查询条件
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((wrapper)->{
                wrapper.eq("sku_id",key).or().like("sku_name",key);
            });
        }
        //带上分类
        String categoryId = (String) params.get("categoryId");
        if(!StringUtils.isEmpty(categoryId)&&!"0".equalsIgnoreCase(categoryId)){
            queryWrapper.eq("category_id",categoryId);
        }
        //带上品牌
        String brandId = (String) params.get("brandId");
        if(!StringUtils.isEmpty(brandId)&&!"0".equalsIgnoreCase(brandId)){
            queryWrapper.eq("brand_id",brandId);
        }
        //价格范围
        String min = (String) params.get("min");
        if(!StringUtils.isEmpty(min)){
            queryWrapper.ge("price",min);
        }
        String max = (String) params.get("max");
        //校验传递的价格范围合理性, 如果 max 有值,并且大于 0，
        //才有必要封装到查询条件
        if(!StringUtils.isEmpty(max) ){
            try{
                BigDecimal bigDecimal = new BigDecimal(max);
                if(bigDecimal.compareTo(new BigDecimal("0"))==1){
                    queryWrapper.le("price",max);
                }
            }catch (Exception e){
            }
        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper);
        return new PageUtils(page);
    }
}