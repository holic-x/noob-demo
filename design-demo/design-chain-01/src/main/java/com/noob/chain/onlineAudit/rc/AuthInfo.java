package com.noob.chain.onlineAudit.rc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 审核信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo {
    private String uid;
    private String orderId;
    private Date authDate;
}
