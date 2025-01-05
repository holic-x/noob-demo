package com.noob.rpc.simple.transport;

import com.noob.rpc.simple.factory.BeanManager;
import com.noob.rpc.simple.protocol.Header;
import com.noob.rpc.simple.protocol.Message;
import com.noob.rpc.simple.protocol.Request;
import com.noob.rpc.simple.protocol.Response;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;

class InvokeRunnable implements Runnable {

    private ChannelHandlerContext ctx;
    private Message<Request> message;

    public InvokeRunnable(Message<Request> message, ChannelHandlerContext ctx) {
        this.message = message;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        Response response = new Response();
        Object result = null;
        try {
            Request request = message.getContent();
            String serviceName = request.getServiceName();
            // 提供BeanManager对所有业务Bean进行管理，其底层在内存中维护了一个业务Bean实例的集合(也可以尝试接入Spring等容器管理业务Bean)
            Object bean = BeanManager.getBean(serviceName);
            // 下面通过反射调用Bean中的相应方法
            Method method = bean.getClass().getMethod(request.getMethodName(), request.getArgTypes());
            result = method.invoke(bean, request.getArgs());
        } catch (Exception e) {
            // 省略异常处理
        } finally {
        }
        Header header = message.getHeader();
        header.setExtraInfo((byte) 1);
        response.setResult(result); // 设置响应结果
        // 将响应消息返回给客户端
        ctx.writeAndFlush(new Message(header, response));
    }

}
