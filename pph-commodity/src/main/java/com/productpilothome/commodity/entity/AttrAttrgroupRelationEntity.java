package com.productpilothome.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品属性和商品属性组的关联表
 * 
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-31 12:57:38
 */
@Data
@TableName("commodity_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 属性 id
	 */
	private Long attrId;
	/**
	 * 属性分组 id
	 */
	private Long attrGroupId;
	/**
	 * 属性组内排序
	 */
	private Integer attrSort;

}
