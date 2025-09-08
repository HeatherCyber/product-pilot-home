package com.productpilothome.commodity.exception;

/**
 * @author Heather
 * @version 1.0
 */

import com.productpilothome.common.exception.PphCodeEnume;
import com.productpilothome.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. @ResponseBody returns data in json format
 * 2. @ControllerAdvice (basePackages = "") uniformly handles exceptions thrown under the specified package
 */

@Slf4j
@ResponseBody
@ControllerAdvice(basePackages = "com.productpilothome.commodity.controller")
public class PphExceptionControllerAdvice {
    /**
     * 1. Data validation errors belong to MethodArgumentNotValidException
     * 2. The rule of exception matching is to match precisely first, then match exception types with larger scope
     * 3. After writing the precise matching exceptions clearly, we can more accurately customize exception information
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e) {
        log.error("Validation Exception{}，Error Type：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError) -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
//        return R.error(400, "Parameter validation exception").put("data", errorMap);
        return R.error(PphCodeEnume.INVALID_EXCEPTION.getCode(),
                PphCodeEnume.INVALID_EXCEPTION.getMsg()).put("data", errorMap);
    }
    /**
     * 这里再写一个处理 Throwable 类型的异常的方法, * 没有精确匹配到的异常, 走这里
     */
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
//        return R.error(40000,"Unknown error");
        return R.error(PphCodeEnume.UNKNOWN_EXCEPTION.getCode(),
                PphCodeEnume.UNKNOWN_EXCEPTION.getMsg());
    }

}
