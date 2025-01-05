package com.noob.rpc.simple.transport;

import com.noob.rpc.simple.constant.Constants;
import com.noob.rpc.simple.protocol.Message;
import com.noob.rpc.simple.protocol.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RpcServerHandler extends SimpleChannelInboundHandler<Message<Request>> {

    // 业务线程池
    private static Executor executor = Executors.newCachedThreadPool();

    @Override
    protected void channelRead0(final ChannelHandlerContext channelHandlerContext, Message<Request> message) throws Exception {
        byte extraInfo = message.getHeader().getExtraInfo();
        if (Constants.isHeartBeat(extraInfo)) { // 心跳消息，直接返回即可
            channelHandlerContext.writeAndFlush(message);
            return;
        }
        // 非心跳消息，直接封装成Runnable提交到业务线程池
        executor.execute(new InvokeRunnable(message, channelHandlerContext));
    }
}