package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.template_method;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 电商场景模拟：模板方法应用（营销活动生成活动海报信息：模拟登陆、模拟爬取信息、生成海报信息）
 */
public class NetMallTemplateMethod {


    // ① 定义抽象策略
    abstract class NetMallTemplate {

        String account;
        String password;

        NetMallTemplate(String account, String password) {
            this.account = account;
            this.password = password;
        }


        // 定义一个核心流程方法(生成活动海报信息)
        public void generatePoster(String skuUrl) {
            login(account, password);// 1.模拟登陆
            // 2.模拟爬取信息
            Map<String, Object> map = crawl(skuUrl);
            // 3.生成海报信息
            String base64 = createBase64(map);
        }

        public abstract void login(String account, String password);

        public abstract Map<String, Object> crawl(String skuUrl);

        public abstract String createBase64(Map<String, Object> map);
    }

    // ② 定义具体策略
    class JingDongNetMall extends NetMallTemplate {

        public JingDongNetMall(String account, String password) {
            super(account, password);
        }

        @Override
        public void login(String account, String password) {
            // 验证账号信息
            System.out.println("账号密码验证通过");
        }

        @Override
        public Map<String, Object> crawl(String skuUrl) {
            // return Collections.emptyMap();
            Map<String, Object> map = new HashMap<>();
            // 模拟爬取数据
            map.put("prodName", "iphone 12");
            map.put("price", 100);
            return map;
        }

        @Override
        public String createBase64(Map<String, Object> map) {
            // 将爬取到的商品信息转成base64
            // BASE64Encoder encoder = new BASE64Encoder();
            System.out.println("淘宝商品base64海报生成");
            // return encoder.encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            return null;
        }
    }

    public void test() {
        JingDongNetMall jingDongNetMall = new JingDongNetMall("111", "222");
        jingDongNetMall.generatePoster("http://123.com");

    }

    public static void main(String[] args) {
        NetMallTemplateMethod test = new NetMallTemplateMethod();
        test.test();
    }

}
