package demo1.Server;

import IHandler.IMessageHandler;
import demo1.Handler.MessageHandlers;
import demo1.entity.MessageInput;
import demo1.entity.MessageRegistry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageController extends ChannelInboundHandlerAdapter {
    private ThreadPoolExecutor executor;

    public MessageController(int workThreads) {
        //业务队列最大1000，避免堆积
        //如果子线程处理不过来，io线程也会加入处理业务逻辑
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000);
        ThreadFactory factory = new ThreadFactory() {
            AtomicInteger seq = new AtomicInteger();

            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("rpc-" + seq.getAndIncrement());
                return t;
            }
        };
        // 闲置时间超过30秒的线程自动销毁
        this.executor = new ThreadPoolExecutor(1, workThreads, 30, TimeUnit.SECONDS, queue, factory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void closeGracefully() {
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.executor.shutdownNow();
    }

    public void channelActive(ChannelHandlerContext ctx) {
        //客户端来了一个新连接
        System.out.println("connect comes");
    }

    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("Connection leaves");
        ctx.close();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof MessageInput) {
            System.out.println("read a message");
            this.executor.execute(() -> {
                this.handleMessage(ctx, (MessageInput) msg);
            });
        }
    }

    private void handleMessage(ChannelHandlerContext ctx, MessageInput input) {
        Class<?> clazz = MessageRegistry.get(input.getType());
        if (clazz == null) {
            MessageHandlers.defaultHandler.handle(ctx, input.getRequestId(), input);
            return;
        }
        Object obj = input.getPayload(clazz);

        IMessageHandler<Object> handler = (IMessageHandler<Object>) MessageHandlers.get(input.getType());
        if (handler != null)
            handler.handle(ctx, input.getRequestId(), obj);
        else
            MessageHandlers.defaultHandler.handle(ctx, input.getRequestId(),input);
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        System.out.println("connection error");
        cause.printStackTrace();
        ctx.close();
    }
}
