package com.noob.ec.template.group;

/**
 * 模拟客户端：测试营销海报生成
 */
public class NetMallClient {
    public static void main(String[] args) {
        // 京东海报生成
        JingDongNetMall jingDongNetMall = new JingDongNetMall("noob","123456");
        System.out.println(jingDongNetMall.generatePoster("http://jingdong.com/xxx"));

        // 淘宝海报生成
        TaobaoNetMall taobaoNetMall = new TaobaoNetMall("007","123456");
        System.out.println(taobaoNetMall.generatePoster("http://taobao.com/xxx"));
    }
}
