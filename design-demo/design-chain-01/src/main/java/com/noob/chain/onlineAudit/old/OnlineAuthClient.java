package com.noob.chain.onlineAudit.old;

import java.text.SimpleDateFormat;

/**
 * 系统上线流程审批客户端测试demo
 */
public class OnlineAuthClient {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        // 测试1：日常上线
        OnlineAuth.doAuth("1001","1",sdf.parse("2020-05-21 00:00:00"));

        // 测试2：临近大促
        OnlineAuth.doAuth("1002","2",sdf.parse("2020-06-03 00:00:00"));

        // 测试3：大促期间
        OnlineAuth.doAuth("1003","3",sdf.parse("2020-06-18 00:00:00"));
    }
}
