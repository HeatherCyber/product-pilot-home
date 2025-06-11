package com.productpilothome.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 商品分类表
 * 
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-04-26 16:09:34
 */
@Data
@TableName("commodity_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父分类 id
	 */
	private Long parentId;
	/**
	 * 层级
	 */
	private Integer catLevel;
	/**
	 * 0 不显示，1 显示
	 * 1. @TableLogic(value = "1", delval = "0")
	 * 2. 后面也可以指定哪个值表示逻辑删除
	 * 3. 如果没有指定，就以 application.yml 配置的为准
	 */
	@TableLogic
	private Integer isShow;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 统计单位
	 */
	private String proUnit;
	/**
	 * 商品数量
	 */
	private Integer proCount;
	/**
	 * 1. childrenCategories 表示某个分类的子分类集合
	 * 2. @TableField(exist = false) 表示 childrenCategories 不对应表的字段
	 * 3.注解@JsonInclude(JsonInclude.Include.NON_EMPTY 表示如果
	 * childrenCategories 的值是空数组，就不返回
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@TableField(exist = false)
	private List<CategoryEntity> childrenCategories;


}
