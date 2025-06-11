package com.productpilothome.commodity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品 spu 信息介绍
 * 
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-06-06 15:38:13
 */
@Data
@TableName("commodity_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品 id
	 * commodity_spu_info_desc 表的 id 不是自增长的，而是我们指定的
	 * 因此，在这里给 spuId 标识上 @TableId(type = IdType.INPUT)
	 * 否则底层的 sql 语句时不会生成添加 supId 的 sql 语句
	 */
	@TableId(type = IdType.INPUT)
	private Long spuId;

	/**
	 * 商品介绍图片
	 */
	private String descript;

}
