package com.xiaoxi.study.interceptor;

import com.xiaoxi.study.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author liuteng
 * 全局异常处理
 */
@SuppressWarnings("rawtypes")
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    private static final String VALIDATION_CODE = "1001";

    private static final String BASE_EXCEPTION_CODE = "9999";

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Result handleException(Exception e) {

        Result result = new Result();

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ce = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = ce.getBindingResult();
            StringBuilder sb = new StringBuilder();
            for(FieldError fieldError :bindingResult.getFieldErrors()){
                sb.append(fieldError.getDefaultMessage()).append("/");
            }
            result.setMessage(sb.toString());
            result.setCode(VALIDATION_CODE);
        } else if (e instanceof ConstraintViolationException) {
            StringBuilder sb = new StringBuilder();
            ConstraintViolationException exs = (ConstraintViolationException)e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for(ConstraintViolation<?> item:violations){
                sb.append(item.getMessage()).append("/");
            }
            result.setMessage(sb.toString());
            result.setCode(VALIDATION_CODE);
        } else {
            result.setCode(BASE_EXCEPTION_CODE);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
