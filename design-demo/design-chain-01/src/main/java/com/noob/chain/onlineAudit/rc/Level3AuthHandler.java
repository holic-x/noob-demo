package com.noob.chain.onlineAudit.rc;

public class Level3AuthHandler extends AbstractAuthHandler{
    @Override
    public void handle(AuthInfo authInfo) {
        System.out.println("模拟三级审核，审核通过");
        if(this.nextHandler!=null){
            this.nextHandler.handle(authInfo);
        }
    }
}
