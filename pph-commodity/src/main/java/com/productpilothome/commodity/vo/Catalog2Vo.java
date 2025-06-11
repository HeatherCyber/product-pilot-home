package com.productpilothome.commodity.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Heather
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog2Vo {

    //一级父分类的 id
    private String catalog1Id;
    //三级子分类
    private List<Catalog3Vo> catalog3List;
    private String id;
    private String name;

    //内部类：三级分类 vo
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Catalog3Vo {
        //父分类（二级分类） id
        private String catalog2Id;
        private String id;
        private String name;
    }
}
