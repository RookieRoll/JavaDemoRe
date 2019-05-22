package demo1.Handler;

import IHandler.IMessageHandler;
import demo1.entity.ExpRequest;
import demo1.entity.ExpResponse;
import demo1.entity.MessageOutput;
import io.netty.channel.ChannelHandlerContext;

public class ExpRequestHandler implements IMessageHandler<ExpRequest> {


	public void handle(ChannelHandlerContext ctx, String requestId, ExpRequest message) {
		int base = message.getBase();
		int exp = message.getExp();
		long start = System.nanoTime();
		long res = 1;
		for (int i = 0; i < exp; i++) {
			res *= base;
		}
		long cost = System.nanoTime() - start;

		ctx.writeAndFlush(new MessageOutput(requestId, "exp_res", new ExpResponse(res, cost)));
	}
}
