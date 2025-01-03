package com.noob.rpc.simple.transport;

import com.noob.rpc.simple.protocol.Message;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Promise;
import lombok.Data;

@Data
public class NettyResponseFuture<T> {
    private long createTime;
    private long timeOut;
    private Message request;
    private Channel channel;
    private Promise<T> promise;

    public NettyResponseFuture(long createTime, long timeOut, Message request, Channel channel, Promise<T> promise) {
        this.createTime = createTime;
        this.timeOut = timeOut;
        this.request = request;
        this.channel = channel;
        this.promise = promise;
    }
}
