package demo1.Handler;

import IHandler.IMessageHandler;
import demo1.entity.MessageInput;
import io.netty.channel.ChannelHandlerContext;

public class DefaultHandler implements IMessageHandler<MessageInput> {
    @Override
    public void handle(ChannelHandlerContext ctx, String requestId, MessageInput message) {
        System.out.println("unrecognized message type="+message.getType()+" comes");
    }
}
