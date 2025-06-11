package com.productpilothome.commodity.vo;

import com.productpilothome.commodity.entity.SkuInfoEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Heather
 * @version 1.0
 * 作为返回检索返回的结果 VO
 */

@Data
public class SearchResult {

    private List<SkuInfoEntity> commodity;
    private Integer pageNum;
    private Long total;
    private Integer totalPages;
    private List<Integer> pageNavs;
    private String keyword;

}
