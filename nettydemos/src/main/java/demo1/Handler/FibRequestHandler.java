package demo1.Handler;

import IHandler.IMessageHandler;
import demo1.entity.MessageOutput;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

public class FibRequestHandler implements IMessageHandler<Integer> {
	private List<Long> fibs = new ArrayList<Long>();

	{
		fibs.add(1L);
		fibs.add(1L);
	}

	public void handle(ChannelHandlerContext ctx, String requestId, Integer n) {
		for (int i = fibs.size(); i < n + 1; i++) {
			long value = fibs.get(i - 2) + fibs.get(i - 1);
			fibs.add(value);
		}
		//输出响应
		ctx.writeAndFlush(new MessageOutput(requestId, "fib_res", fibs.get(n)));
	}
}
