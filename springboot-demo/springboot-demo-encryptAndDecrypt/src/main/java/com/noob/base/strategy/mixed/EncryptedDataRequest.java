package com.noob.base.strategy.mixed;

import lombok.Data;

/**
 * 加密数据请求体
 */
@Data
public class EncryptedDataRequest {
    private String encryptedKey;
    private String encryptedData;
    private String iv;
}
