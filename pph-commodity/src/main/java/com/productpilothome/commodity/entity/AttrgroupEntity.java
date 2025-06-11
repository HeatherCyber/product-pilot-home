package com.productpilothome.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 家居商品属性分组
 * 
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-05-24 12:29:03
 */
@Data
@TableName("commodity_attrgroup")
public class AttrgroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 组名
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 组图标
	 */
	private String icon;
	/**
	 * 所属分类 id
	 */
	private Long categoryId;
	/**
	 * 增加 cascadedCategoryId字段， 返回的是某一个三级分类的所有父分类，比如[1,4,231]
	 */
	@TableField(exist = false)
	private Long[] cascadedCategoryId;

}
