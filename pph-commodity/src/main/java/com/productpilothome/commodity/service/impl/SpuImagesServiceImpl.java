package com.productpilothome.commodity.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.productpilothome.common.utils.PageUtils;
import com.productpilothome.common.utils.Query;

import com.productpilothome.commodity.dao.SpuImagesDao;
import com.productpilothome.commodity.entity.SpuImagesEntity;
import com.productpilothome.commodity.service.SpuImagesService;

import javax.annotation.Resource;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Resource
    SpuImagesService imagesService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<SpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 保存某个 spu 的图片集，就是商品最前面的按一组图片来展示图片的集合
     * @param id
     * @param images
     */
    @Override
    public void saveImages(Long id, List<String> images) {
        System.out.println("saveImage...");
        if(images == null || images.size() == 0){//设置默认图片集
            SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
            spuImagesEntity.setSpuId(id);
            spuImagesEntity.setImgUrl("default1.jpg");
            spuImagesEntity.setDefaultImg(1);
            this.save(spuImagesEntity);
        } else { //如果有，就遍历，批量添加即可
            List<SpuImagesEntity> collect = images.stream().map(img -> {
                SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                spuImagesEntity.setSpuId(id);
                spuImagesEntity.setImgUrl(img);
                return spuImagesEntity;
            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }
}