package com.productpilothome.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品属性表
 * 
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-31 11:47:12
 */
@Data
@TableName("commodity_attr")
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性 id
	 */
	@TableId
	private Long attrId;
	/**
	 * 属性名
	 */
	private String attrName;
	/**
	 * 是否需要检索[0-不需要，1-需要]
	 */
	private Integer searchType;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 可选值列表[用逗号分隔]
	 */
	private String valueSelect;
	/**
	 * 属性类型[0-销售属性，1-基本属性]
	 */
	private Integer attrType;
	/**
	 * 启用状态[0 - 禁用，1 - 启用]
	 */
	private Long enable;
	/**
	 * 所属分类
	 */
	private Long categoryId;
	/**
	 * 快速展示【是否展示在介绍上；0-否 1-是】
	 */
	private Integer showDesc;
	/**
	 * 这是该商品属性所关联的商品属性组 id
	 * 但是不在表中
	 */
	@TableField(exist = false)
	private Long attrGroupId;

}
