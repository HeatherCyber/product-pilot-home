package com.productpilothome.commodity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.productpilothome.commodity.entity.AttrEntity;
import lombok.Data;
import java.util.List;

/**
 * @author Heather
 * @version 1.0
 * 如果返回的 json 数据的当前实体类，不能满足需求，则定义一个 VO(View Object)对象
 * 在该对象，可以根据需求组合实体类字段,或者增加，或者删除一些字段
 */

@Data
public class AttrGroupWithAttrsVo {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String name;

    private Integer sort;

    private String description;

    private String icon;

    private Long categoryId;

    private List<AttrEntity> attrs;

}
