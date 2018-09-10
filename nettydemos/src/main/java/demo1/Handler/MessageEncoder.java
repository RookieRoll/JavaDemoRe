package demo1.Handler;

import com.alibaba.fastjson.JSON;
import demo1.entity.MessageOutput;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageEncoder extends MessageToMessageEncoder<MessageOutput> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageOutput messageOutput, List<Object> list) throws Exception {
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer();
        writeStr(buf, messageOutput.getRequestId());
        writeStr(buf, messageOutput.getType());
        writeStr(buf, JSON.toJSONString(messageOutput.getPlayLoad()));
        list.add(buf);
    }

    private void writeStr(ByteBuf buf, String s) {
        buf.writeInt(s.length());
        buf.writeBytes(s.getBytes(StandardCharsets.UTF_8));
    }
}
