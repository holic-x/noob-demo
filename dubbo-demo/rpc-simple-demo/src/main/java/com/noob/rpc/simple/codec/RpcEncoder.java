package com.noob.rpc.simple.codec;

import com.noob.rpc.simple.compress.Compressor;
import com.noob.rpc.simple.compress.CompressorFactory;
import com.noob.rpc.simple.constant.Constants;
import com.noob.rpc.simple.protocol.Header;
import com.noob.rpc.simple.protocol.Message;
import com.noob.rpc.simple.serialization.Serialization;
import com.noob.rpc.simple.serialization.SerializationFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class RpcEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          Message message, ByteBuf byteBuf) throws Exception {
        Header header = message.getHeader();
        // 依次序列化消息头中的魔数、版本、附加信息以及消息ID
        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getVersion());
        byteBuf.writeByte(header.getExtraInfo());
        byteBuf.writeLong(header.getMessageId());
        Object content = message.getContent();
        if (Constants.isHeartBeat(header.getExtraInfo())) {
            byteBuf.writeInt(0); // 心跳消息，没有消息体，这里写入0
            return;
        }
        // 按照extraInfo部分指定的序列化方式和压缩方式进行处理
        Serialization serialization = SerializationFactory.get(header.getExtraInfo());
        Compressor compressor = CompressorFactory.get(header.getExtraInfo());
        byte[] payload = compressor.compress(serialization.serialize(content));
        byteBuf.writeInt(payload.length); // 写入消息体长度
        byteBuf.writeBytes(payload); // 写入消息体
    }
}
