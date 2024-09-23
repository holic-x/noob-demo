package com.noob.ec.template.group;

import com.alibaba.fastjson2.JSON;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 淘宝平台
 */
public class TaobaoNetMall extends NetMallTemplate{

    TaobaoNetMall(String userAccount, String password) {
        super(userAccount, password);
    }

    @Override
    public boolean login(String userAccount, String password) {
        System.out.println("模拟淘宝网站登录：" + userAccount);
        return true;
    }

    @Override
    public Map<String, String> crawl(String skuUrl) {
        // 模拟根据URL爬取商品信息、解析，随后返回商品信息（参考实现：根据指定URL获取响应信息，然后解析相关的参数信息）
        System.out.println("【淘宝】模拟爬虫，根据url爬取商品信息");
        Map<String, String> map = new HashMap<>();
        map.put("name", "acer 电脑");
        map.put("skuPrice", "39999");
        map.put("skuUrl", skuUrl);
        return map;
    }

    @Override
    public String createBase64(Map<String, String> goodsInfo) {
        // 根据商品信息生成相应的营销海报
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println("淘宝商品base64海报生成");
        return encoder.encode(JSON.toJSONString(goodsInfo).getBytes(StandardCharsets.UTF_8));
    }
}
