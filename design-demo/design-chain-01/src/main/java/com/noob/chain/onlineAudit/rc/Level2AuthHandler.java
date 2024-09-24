package com.noob.chain.onlineAudit.rc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Level2AuthHandler extends AbstractAuthHandler{
    // 定义时间阈值
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void handle(AuthInfo authInfo) {
        // 设定时间阈值
        Date level2BeginDate = null;
        Date level2EndDate = null;
        try{
            level2BeginDate = sdf.parse("2020-06-01 00:00:00");
            level2EndDate = sdf.parse("2020-06-25 00:00:00");
        }catch (Exception e){
            e.printStackTrace();
        }
        // 校验时间阈值
        Date authDate = authInfo.getAuthDate();
        if(authDate.after(level2BeginDate) && authDate.before(level2EndDate)){
            System.out.println("模拟二级审核，审核通过");
            if(this.nextHandler!=null){
                this.nextHandler.handle(authInfo);
            }
        }
    }
}
