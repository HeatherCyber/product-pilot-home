package com.productpilothome.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Heather
 * @version 1.0
 * 1. EnumConstraintValidator 是真正校验数据的类, 实现 ConstraintValidator
 * 2. <EnumValidate,Integer> 表示该校验类是针对@EnumValidate 传入的 Integer 类型数据进行校验
 */
public class EnumConstraintValidator
        implements ConstraintValidator<EnumValidate,Integer> {

    private Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        //将注解指定的值取出放入到 set 集合
        int[] values = constraintAnnotation.values();
        for (int value : values) {
            set.add(value);
        }
    }
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //value就是从表单传递的数值
        //判断是否在注解填写的枚举值中
        return set.contains(value);
    }
}
