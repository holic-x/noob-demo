package com.noob.rpc.simple.transport;

import com.noob.rpc.simple.constant.Constants;
import com.noob.rpc.simple.protocol.Message;
import com.noob.rpc.simple.protocol.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcClientHandler extends SimpleChannelInboundHandler<Message<Response>> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message<Response> message) throws Exception {
        NettyResponseFuture responseFuture = Connection.IN_FLIGHT_REQUEST_MAP.remove(message.getHeader().getMessageId());
        Response response = message.getContent();
        // 心跳消息特殊处理
        if (response == null && Constants.isHeartBeat(message.getHeader().getExtraInfo())) {
            response = new Response();
            response.setCode(Constants.HEARTBEAT_CODE);
        }
        responseFuture.getPromise().setSuccess(response.getResult());
    }
}

