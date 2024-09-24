package com.noob.chain.onlineAudit.old;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 传统实现方式：系统上线审批
 * 根据上线时间节点，分级校验
 */
public class OnlineAuth {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 上线审核方法
     * @param uid
     * @param orderId
     * @param authDate
     */
    public static void doAuth(String uid, String orderId, Date authDate) throws Exception{
        System.out.println("审批人：" + uid + " 审核单号：" + orderId + " 审批时间：" + sdf.format(authDate));
        // 进行三级审批
        System.out.println("模拟三级审批，审批通过");

        // 判断是否需要二级审批(根据当前审批时间与二级审批节点阈值进行比较)
        Date level2BeginDate = sdf.parse("2020-06-01 00:00:00");
        Date level2EndDate = sdf.parse("2020-06-25 00:00:00");
        if(authDate.after(level2BeginDate)&&authDate.before(level2EndDate)){
            System.out.println("模拟二级审批，审批通过");
        }

        // 判断是否需要一级审批
        Date level1BeginDate = sdf.parse("2020-06-11 00:00:00");
        Date level1EndDate = sdf.parse("2020-06-20 00:00:00");
        if(authDate.after(level1BeginDate)&&authDate.before(level1EndDate)){
            System.out.println("模拟一级审批，审批通过");
        }
    }
}
