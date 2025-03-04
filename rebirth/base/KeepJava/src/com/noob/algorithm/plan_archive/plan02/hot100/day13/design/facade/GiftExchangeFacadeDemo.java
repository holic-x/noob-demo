package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.facade;

/**
 * 礼品兑换：门面模式
 * 礼品兑换涉及到一系列流程：校验、支付、物流等
 */
public class GiftExchangeFacadeDemo {
    // 定义礼品兑换交互核心参数
    static class Gift {
        int id;
        String name;
    }

    // ① 模拟多个子系统服务（校验、支付、物流）
    // 校验服务
    static class QualifyService {
        public boolean isAvailable(Gift gift) {
            // todo 礼品兑换校验
            System.out.println("校验" + gift.name + "礼品兑换（积分和库存）");
            return true;
        }
    }

    // 支付服务
    static class PayService {
        public boolean pay(Gift gift) {
            // todo 礼品节分扣减
            System.out.println(gift.name + "礼品积分扣减成功");
            return true;
        }
    }

    // 物流服务
    static class ShippingService {
        public String delivery(Gift gift) {
            // todo 物流处理
            String shippingNo = "123456";
            System.out.println(gift.name + "发货成功，物流编号为" + shippingNo);
            return shippingNo;
        }
    }

    // ② 门面类：整合一组子系统的服务
    static class GiftFacade {

        // 定义一组子系统服务
        QualifyService qualifyService = new QualifyService();
        PayService payService = new PayService();
        ShippingService shippingService = new ShippingService();

        public void exchange(Gift gift) {
            // a.校验
            if (qualifyService.isAvailable(gift)) {
                // b.积分扣减
                if (payService.pay(gift)) {
                    // c.发货
                    String shippingNo = shippingService.delivery(gift);
                    System.out.println("商品兑换成功，商品已发货，物流单号为：" + shippingNo);
                }
            }
        }

    }

    public static void main(String[] args) {
        // 定义要兑换的礼品信息
        Gift gift = new Gift();
        gift.name = "iphone 12";
        gift.id = 1;

        GiftFacade giftFacade = new GiftFacade();
        giftFacade.exchange(gift);
    }

}
