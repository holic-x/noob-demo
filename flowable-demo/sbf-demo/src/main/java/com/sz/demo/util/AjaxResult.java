package com.sz.demo.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AjaxResult
 * @Description 后台数据返回结果
 * @Author
 * @Date 2020/5/3 18:18
 * @Version
 **/
public class AjaxResult {

    @JsonProperty(index = 1)
    private Integer errCode;

    @JsonProperty(index = 2)
    private String errMsg;

    /**
     * 在引用的时候需要初始化变量,避免空指针异常
     **/
    @JsonProperty(index = 3)
    private Map<String,Object> extend;

    public AjaxResult(Integer errCode, String errMsg, Map<String, Object> extend) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.extend = extend;
    }

    public AjaxResult(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.extend = new HashMap<String, Object>();
    }

    public AjaxResult(ResultEnum codeEnum) {
        this.errCode = codeEnum.getCode();
        this.errMsg = codeEnum.getMsg();
        this.extend = new HashMap<String, Object>();
    }

    public AjaxResult(ResultEnum codeEnum, Map<String, Object> extend) {
        this.errCode = codeEnum.getCode();
        this.errMsg = codeEnum.getMsg();
        this.extend = extend;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
