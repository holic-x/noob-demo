package com.noob.framework.exception;

import com.noob.framework.model.RspDTO;
import com.noob.framework.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

// 全局异常处理器
@RestControllerAdvice
public class GlobalException {

    /**
     * @ExceptionHandler:限定对何种异常进行处理
     * @ResponseBody:处理返回的格式（SpringMVC会响应一个json格式信息）
     */
    @ExceptionHandler(value = BusinessException.class)
    public String handler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("发生异常的处理器：" + handler + "- 具体异常信息：" + ex.getMessage());
        // 返回结果
        return "{\n" +
                "    \"code\":\"-1\",\n" +
                "    \"msg\":\"服务器出现异常，请联系管理员处理...\",\n" +
                "    \"data\":null\n" +
                "}";
    }

    // 异常处理器：针对参数校验设定相应的handler
    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RspDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new RspDTO(Constant.PARAM_FAIL_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public RspDTO handleValidationException(ValidationException e) {
        return new RspDTO(Constant.PARAM_FAIL_ERROR, e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RspDTO handleConstraintViolationException(ConstraintViolationException e) {
        return new RspDTO(Constant.PARAM_FAIL_ERROR, e.getMessage());
    }

    // ---------- 其他异常处理 ---------
    @ExceptionHandler(NoHandlerFoundException.class)
    public RspDTO handlerNoFoundException(Exception e) {
        return new RspDTO(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<String> handlerHttpMediaTypeNotAcceptableException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("客户端请求中的Accept字段不正确或不匹配服务器的响应类型");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RspDTO> handleException(Exception e) {
        return new ResponseEntity<>(new RspDTO(500, "系统繁忙,请稍后再试"), HttpStatus.OK);
    }
}
