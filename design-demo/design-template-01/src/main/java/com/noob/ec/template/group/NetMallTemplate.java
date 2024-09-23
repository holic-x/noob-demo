package com.noob.ec.template.group;

import lombok.Data;

import java.util.Map;

/**
 * 模板模式应用
 * [电商场景]：爬虫爬取商品信息，模拟生成营销海报信息
 * - 模拟登录
 * - 模拟爬取
 * - 生成营销海报
 */
@Data
public abstract class NetMallTemplate {

    // 登录用户名、密码设定
    private String userAccount;
    private String password;

    // 构造器定义
    NetMallTemplate(String userAccount, String password) {
        this.userAccount = userAccount;
        this.password = password;
    }

    /**
     * 模板方法定义：约定执行流程和基本策略(可以理解为定义业务执行的程序入口)
     * 1.模拟登录
     * 2.模拟爬取
     * 3.生成营销海报
     */
    public String generatePoster(String skuUrl){
        // 模拟登录
        boolean loginFlag = login(userAccount,password);
        // 登录成功，进行数据爬取
        if(loginFlag){
            // 爬取数据
            Map<String,String> goodsInfo = crawl(skuUrl);
            // 生成海报信息
            return createBase64(goodsInfo);
        }else{
            return null;
        }
    }

    // 模拟登录
    public abstract boolean login(String userAccount, String password);

    // 模拟爬取提取商品信息
    public abstract Map<String,String> crawl(String skuUrl);

    // 生成营销海报
    public abstract String createBase64(Map<String,String> goodsInfo) ;

}
