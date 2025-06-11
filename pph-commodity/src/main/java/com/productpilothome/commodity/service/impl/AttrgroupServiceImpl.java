package com.productpilothome.commodity.service.impl;

import com.productpilothome.commodity.entity.AttrEntity;
import com.productpilothome.commodity.service.AttrService;
import com.productpilothome.commodity.vo.AttrGroupWithAttrsVo;
import com.productpilothome.common.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.productpilothome.common.utils.PageUtils;

import com.productpilothome.commodity.dao.AttrgroupDao;
import com.productpilothome.commodity.entity.AttrgroupEntity;
import com.productpilothome.commodity.service.AttrgroupService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("attrgroupService")
public class AttrgroupServiceImpl extends ServiceImpl<AttrgroupDao, AttrgroupEntity> implements AttrgroupService {

    @Resource
    AttrService attrService;

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCategoryId(Long categoryId)
    {
        //1、查询分组信息
        List<AttrgroupEntity> attrGroupEntities = this.list(new
                QueryWrapper<AttrgroupEntity>().eq("category_id", categoryId));
        //2、查询所有属性
        List<AttrGroupWithAttrsVo> collect = attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group,attrsVo);
            List<AttrEntity> attrs = attrService.getRelationAttr(attrsVo.getId());
            attrsVo.setAttrs(attrs);
            return attrsVo;
        }).collect(Collectors.toList());
        return collect;
    }




    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrgroupEntity> page = this.page(
                new Query<AttrgroupEntity>().getPage(params),
                new QueryWrapper<AttrgroupEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 增加根据家居分类(第三级的)+查询条件+分页 API 接口
     * @param categoryId
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        //获取用户进行查询时的关键字
        String key = (String) params.get("key");
        //QueryWrapper 是 renren 相关提供的，是用于封装查询条件/参数
        QueryWrapper<AttrgroupEntity> wrapper =
                new QueryWrapper<AttrgroupEntity>();
        //如果是带条件查询, 将条件分组到 wrapper,
        //这里的 id 和 name 是指的 commodity_attrgroup 表的字段
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("id", key).or().like("name", key);
            });
        }
        //IPage 是 renren 提供的工具类,用于分页查询
        //categoryId 为 0 表示, 查询分类属性组时,不加入 categoryId
        if (categoryId == 0) {
            IPage<AttrgroupEntity> page =
            this.page(new Query<AttrgroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        } else {//如果 categoryId 不为 0 表示, 查询分类属性组时，需要加入 categoryId 条件
            wrapper.eq("category_id", categoryId);
            IPage<AttrgroupEntity> page =
                    this.page(new Query<AttrgroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        }
    }

}