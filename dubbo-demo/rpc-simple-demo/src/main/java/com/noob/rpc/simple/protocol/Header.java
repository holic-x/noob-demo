package com.noob.rpc.simple.protocol;

import lombok.Data;

/**
 * 自定义协议：消息头
 */
@Data
public class Header {

    private short magic; // 魔数
    private byte version; // 版本号
    private byte extraInfo; // 附加信息
    private Long messageId; // 消息ID
    private Integer size; // 消息体长度

    public Header(short magic, byte version) {
        this.magic = magic;
        this.version = version;
        this.extraInfo = 0;
    }

    public Header(short magic, byte version, byte extraInfo, Long messageId, Integer size) {
        this.magic = magic;
        this.version = version;
        this.extraInfo = extraInfo;
        this.messageId = messageId;
        this.size = size;
    }
}

