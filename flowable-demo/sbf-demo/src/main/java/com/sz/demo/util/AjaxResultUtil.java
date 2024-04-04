package com.sz.demo.util;

import java.util.Map;

/**
 * @ClassName AjaxResultUtil
 * @Description TODO
 * @Author
 * @Date 2020/5/4 10:51
 * @Version
 **/
public class AjaxResultUtil {

    //    public static ResponseEntity<AjaxResult> success() {
//        AjaxResult result = new AjaxResult(ResultEnum.RESPONSE_FAIL);
//        return new ResponseEntity<AjaxResult>(result, HttpStatus.NOT_FOUND);
//    }

    // 数据响应成功

    public static AjaxResult success() {
        return new AjaxResult(ResultEnum.RESPONSE_SUCCESS);
    }

    public static AjaxResult success(String msg) {
        return new AjaxResult(ResultEnum.RESPONSE_SUCCESS.getCode(),msg);
    }

    public static AjaxResult success(Map<String,Object> data) {
        return new AjaxResult(ResultEnum.RESPONSE_SUCCESS, data);
    }

    public static AjaxResult success(String key,Object data) {
        AjaxResult ajaxResult = new AjaxResult(ResultEnum.RESPONSE_SUCCESS);
        ajaxResult.getExtend().put(key,data);
        return ajaxResult;
    }

    // 数据响应失败

    public static AjaxResult fail() {
        return new AjaxResult(ResultEnum.RESPONSE_FAIL);
    }

    public static AjaxResult fail(ResultEnum codeEnum) {
        return new AjaxResult(codeEnum);
    }

    public static AjaxResult fail(Map<String,Object> data) {
        return new AjaxResult(ResultEnum.RESPONSE_FAIL, data);
    }

    public static AjaxResult fail(String errMsg) {
        return new AjaxResult(ResultEnum.RESPONSE_FAIL.getCode(), errMsg);
    }


    // 异常处理
    public static AjaxResult error(ResultEnum codeEnum){
        return new AjaxResult(codeEnum);
    }

    public static AjaxResult error(Integer errCode,String errMsg){
        return new AjaxResult(errCode, errMsg);
    }

    public static AjaxResult error(String errMsg){
        return new AjaxResult(ResultEnum.ERROR.getCode(), errMsg);
    }

    // 数据交互失败
    public static AjaxResult notFound(){
        return new AjaxResult(ResultEnum.NOT_FOUND);
    }

    // 参数异常
    public static AjaxResult paramError(){
        return new AjaxResult(ResultEnum.PARAM_ERROR);
    }

    // 系统异常
    public static AjaxResult systemError(){
        return new AjaxResult(ResultEnum.SYSTEM_ERROR);
    }


}
