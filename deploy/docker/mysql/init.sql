-- ============================
-- 初始化数据库：pph_commodity
-- ============================

-- 可选：如果数据库已通过 MYSQL_DATABASE 创建，这一段可以省略
CREATE DATABASE IF NOT EXISTS pph_commodity DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE pph_commodity;

-- ============================
-- 创建商品分类表
-- ============================
CREATE TABLE `commodity_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` CHAR(50) NOT NULL COMMENT '名称',
  `parent_id` BIGINT NOT NULL COMMENT '父分类 id',
  `cat_level` INT NOT NULL COMMENT '层级',
  `is_show` TINYINT NOT NULL COMMENT '0 不显示，1 显示]',
  `sort` INT NOT NULL COMMENT '排序',
  `icon` CHAR(255) NOT NULL COMMENT '图标',
  `pro_unit` CHAR(50) NOT NULL COMMENT '统计单位',
  `pro_count` INT NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) CHARSET=utf8mb4 COMMENT='商品分类表';

SELECT * FROM commodity_category;

-- ============================
-- 创建商品品牌表
-- ============================
CREATE TABLE `commodity_brand` (
   id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `name` CHAR(50) COMMENT '品牌名',
   logo VARCHAR(1200) COMMENT 'logo',
   description LONGTEXT COMMENT '说明',
   isShow TINYINT COMMENT '显示',
   first_letter CHAR(1) COMMENT '检索首字母',
   sort INT COMMENT '排序',
   PRIMARY KEY (id)
)CHARSET=utf8mb4 COMMENT='家居品牌';

INSERT INTO
`commodity_brand` (id,`name`, logo,description,isShow,first_letter,sort)
VALUES(1, 'SONY','','',1,'',NULL);

SELECT * FROM `commodity_brand`;

-- ============================
-- 创建商品属性表
-- ============================
CREATE TABLE commodity_attr
(
    attr_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '属性 id',
    attr_name CHAR(30) COMMENT '属性名',
    search_type TINYINT COMMENT '是否需要检索[0-不需要，1-需要]',
    icon VARCHAR(255) COMMENT '图标',
    value_select CHAR(255) COMMENT '可选值列表[用逗号分隔]',
    attr_type TINYINT COMMENT '属性类型[0-销售属性，1-基本属性]',
    ENABLE BIGINT COMMENT '启用状态[0 - 禁用，1 - 启用]',
    category_id BIGINT COMMENT '所属分类',
    show_desc TINYINT COMMENT '快速展示【是否展示在介绍上；0-否 1-是】',
    PRIMARY KEY (attr_id)
)CHARSET=utf8mb4 COMMENT='商品属性表';


-- ============================
-- 创建商品属性分组表
-- ============================

CREATE TABLE `commodity_attrgroup` (
   id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `name` CHAR(20) COMMENT '组名',
   sort INT COMMENT '排序',
   description VARCHAR(255) COMMENT '说明',
   icon VARCHAR(255) COMMENT '组图标',
   category_id BIGINT COMMENT '所属分类 id',
   PRIMARY KEY (id)
)CHARSET=utf8mb4 COMMENT='家居商品属性分组';


SELECT * FROM `commodity_attrgroup`;

-- ============================
-- 创建商品属性和商品属性组的关联表
-- ============================

CREATE TABLE commodity_attr_attrgroup_relation
    (id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
     attr_id BIGINT COMMENT '属性 id',
     attr_group_id BIGINT COMMENT '属性分组 id',
     attr_sort INT COMMENT '属性组内排序',
     PRIMARY KEY (id)
)CHARSET=utf8mb4 COMMENT='商品属性和商品属性组的关联表';

SELECT * FROM `commodity_attr_attrgroup_relation`;

-- ============================
-- 创建商品spu 信息表
-- ============================

CREATE TABLE commodity_spu_info
(id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品 id',
 spu_name VARCHAR(200) COMMENT '商品名称',
 spu_description VARCHAR(1000) COMMENT '商品描述',
 category_id BIGINT COMMENT '所属分类 id',
 brand_id BIGINT COMMENT '品牌 id',
 weight DECIMAL(18,4),
 publish_status TINYINT COMMENT '上架状态[0 - 下架，1 - 上架]',
 create_time DATETIME,
 update_time DATETIME,
 PRIMARY KEY (id)
)CHARSET=utf8mb4 COMMENT='商品 spu 信息';

SELECT * FROM commodity_spu_info;

-- ============================
-- 创建商品spu 图片集表
-- ============================

CREATE TABLE commodity_spu_images
(
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    spu_id BIGINT COMMENT 'spu_id',
    img_name VARCHAR(200) COMMENT '图片名',
    img_url VARCHAR(255) COMMENT '图片地址',
    img_sort INT COMMENT '顺序',
    default_img TINYINT COMMENT '是否默认图',
    PRIMARY KEY (id)
)CHARSET=utf8mb4 COMMENT='spu 图片集';


SELECT * FROM commodity_spu_images;

-- ============================
-- 创建商品spu 图片描述表
-- ============================
CREATE TABLE commodity_spu_info_desc
    (spu_id BIGINT NOT NULL COMMENT '商品 id',
     descript LONGTEXT COMMENT '商品介绍图片',
     PRIMARY KEY (spu_id)
)CHARSET=utf8mb4 COMMENT='商品 spu 信息介绍';

SELECT * FROM commodity_spu_info_desc;

-- ============================
-- 创建商品spu 基本属性表
-- ============================
CREATE TABLE commodity_product_attr_value
(
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    spu_id BIGINT COMMENT '商品 id',
    attr_id BIGINT COMMENT '属性 id',
    attr_name VARCHAR(200) COMMENT '属性名',
    attr_value VARCHAR(200) COMMENT '属性值',
    attr_sort INT COMMENT '顺序',
    quick_show TINYINT COMMENT '快速展示【是否展示在介绍上；0-否 1-是】',
    PRIMARY KEY (id)
)CHARSET=utf8mb4 COMMENT='spu 基本属性值'

SELECT * FROM commodity_product_attr_value;


-- ============================
-- 创建商品sku 信息表
-- ============================

CREATE TABLE commodity_sku_info
(
    sku_id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'skuId',
    spu_id BIGINT COMMENT 'spuId',
    sku_name VARCHAR(255) COMMENT 'sku 名称',
    sku_desc VARCHAR(2000) COMMENT 'sku 介绍描述',
    category_id BIGINT COMMENT '所属分类 id',
    brand_id BIGINT COMMENT '品牌 id',
    sku_default_img VARCHAR(255) COMMENT '默认图片',
    sku_title VARCHAR(255) COMMENT '标题',
    sku_subtitle VARCHAR(2000) COMMENT '副标题',
    price DECIMAL(18,4) COMMENT '价格',
    sale_count BIGINT COMMENT '销量',
    PRIMARY KEY (sku_id)
)CHARSET=utf8mb4 COMMENT='sku 信息';

SELECT * FROM commodity_sku_info;


-- ============================
-- 创建商品sku 图片集表
-- ============================
CREATE TABLE commodity_sku_images
(
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    sku_id BIGINT COMMENT 'sku_id',
    img_url VARCHAR(255) COMMENT '图片地址',
    img_sort INT COMMENT '排序',
    default_img INT COMMENT '默认图[0 - 不是默认图，1 - 是默认图]',
    PRIMARY KEY (id)
)CHARSET=utf8mb4 COMMENT='sku 图片';

SELECT * FROM commodity_sku_images;

-- ============================
-- 创建商品sku 销售属性表
-- 1.保存 sku 的销售属性/值, 一个 sku 可以有多个销售属性/值
-- 2.比如 1 个 sku 有颜色(黑色)和尺寸(100*300)两个销售属性
-- ============================

CREATE TABLE commodity_sku_sale_attr_value
    (id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    sku_id BIGINT COMMENT 'sku_id',
    attr_id BIGINT COMMENT 'attr_id',
    attr_name VARCHAR(200) COMMENT '销售属性名',
    attr_value VARCHAR(200) COMMENT '销售属性值',
    attr_sort INT COMMENT '顺序',
    PRIMARY KEY (id)
)CHARSET=utf8mb4 COMMENT='sku 的销售属性/值表';

SELECT * FROM commodity_sku_sale_attr_value;