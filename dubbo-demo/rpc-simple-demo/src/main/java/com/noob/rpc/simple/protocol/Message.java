package com.noob.rpc.simple.protocol;

import lombok.Data;

@Data
public class Message<T> {

    private Header header;

    private T content;

    public Message(Header header, T content) {
        this.content = content;
        this.header = header;
    }

}