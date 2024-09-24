package com.noob.chain.onlineAudit.rc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Level1AuthHandler extends AbstractAuthHandler{
    // 定义时间阈值
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void handle(AuthInfo authInfo) {
        // 设定时间阈值
        Date level1BeginDate = null;
        Date level1EndDate = null;
        try{
            level1BeginDate = sdf.parse("2020-06-11 00:00:00");
            level1EndDate = sdf.parse("2020-06-20 00:00:00");
        }catch (Exception e){
            e.printStackTrace();
        }
        // 校验时间阈值
        Date authDate = authInfo.getAuthDate();
        if(authDate.after(level1BeginDate) && authDate.before(level1EndDate)){
            System.out.println("模拟一级审核，审核通过");
            if(this.nextHandler!=null){
                this.nextHandler.handle(authInfo);
            }
        }
    }
}
