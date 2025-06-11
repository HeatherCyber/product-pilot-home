package com.productpilothome.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.productpilothome.common.valid.EnumValidate;
import com.productpilothome.common.valid.SaveGroup;
import com.productpilothome.common.valid.UpdateGroup;
import com.productpilothome.common.valid.UpdateIsShowGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * home product brand
 * 如果在controller层的API接口处指定了分组校验，那么对应entity的校验注解就必须指定校验组，否则该校验注解失效
 * @author heather
 * @email heatherwang0709@gmail.com
 * @date 2025-04-28 17:01:53
 */
@Data
@TableName("commodity_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Id cannot be empty when updating", groups = {UpdateGroup.class, UpdateIsShowGroup.class})
	@Null(message = "Id cannot be specified when adding", groups = {SaveGroup.class})
	@TableId
	private Long id;

	@NotBlank(message = "Brand name cannot be empty.",
			groups = {SaveGroup.class, UpdateGroup.class})
	private String name;

	@NotBlank(message = "Brand logo cannot be empty.", groups = {SaveGroup.class})
	@URL(message = "Invalid url.", groups = {SaveGroup.class, UpdateGroup.class})
	private String logo;

	private String description;

	@NotNull(groups = {SaveGroup.class, UpdateGroup.class, UpdateIsShowGroup.class})
	@EnumValidate(values = {0,1}, groups = {SaveGroup.class, UpdateGroup.class, UpdateIsShowGroup.class} )
	private Integer isshow;

	@NotBlank(message = "First character cannot be empty.", groups = {SaveGroup.class})
	@Pattern(regexp="^[a-zA-Z]$",message = "The first character must be within the range of a-z or A-Z.",
			groups = {SaveGroup.class, UpdateGroup.class})
	private String firstLetter;

	@NotNull(message = "Sort number cannot be empty.", groups = {SaveGroup.class})
	@Min(value = 0,message = "Sort number cannot be less than 0.",
			groups = {SaveGroup.class, UpdateGroup.class})
	private Integer sort;

}
