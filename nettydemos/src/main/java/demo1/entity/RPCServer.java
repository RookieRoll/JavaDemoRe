package demo1.entity;

import IHandler.IMessageHandler;
import demo1.Handler.MessageDecoder;
import demo1.Handler.MessageEncoder;
import demo1.Handler.MessageHandlers;
import demo1.Server.MessageController;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class RPCServer {
	private String ip;
	private int port;
	private int ioThreads; // 用来处理网络流的读写线程
	private int workerThreads; // 用于业务处理的计算线程

	public RPCServer(String ip, int port, int ioThreads, int workerThreads) {
		this.ip = ip;
		this.port = port;
		this.ioThreads = ioThreads;
		this.workerThreads = workerThreads;
	}

	private ServerBootstrap bootstrap;
	private EventLoopGroup group;
	private MessageController collector;
	private Channel serverChannel;

	// 注册服务的快捷方式
	public RPCServer service(String type, Class<?> reqClass, IMessageHandler<?> handler) {
		MessageRegistry.register(type, reqClass);
		MessageHandlers.register(type, handler);
		return this;
	}

	public void start() {
		bootstrap = new ServerBootstrap();
		group = new NioEventLoopGroup(ioThreads);
		bootstrap.group(group);
		collector = new MessageController(workerThreads);
		MessageEncoder encoder = new MessageEncoder();
		bootstrap.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipe = ch.pipeline();
				//如果客户端在60秒内没有相应，那么就关闭客户端连接
				pipe.addLast(new ReadTimeoutHandler(60));
				//挂上解码器
				pipe.addLast(new MessageDecoder());
				//挂上编码器
				pipe.addLast(encoder);
				//将业务处理器放置在最后
				pipe.addLast(collector);
			}
		});
		bootstrap.option(ChannelOption.SO_BACKLOG, 100) //客户端套接字接受队列大小
				.option(ChannelOption.SO_REUSEADDR, true)//reuse addr避免端口冲突
				.option(ChannelOption.TCP_NODELAY, true)//关闭小流合并，保证消息的及时到达
				.option(ChannelOption.SO_KEEPALIVE, true);//长时间没动静的连接自动关闭
		serverChannel = bootstrap.bind(this.ip, this.port).channel();
		System.out.printf("server started @ %s:%d\n", ip, port);


	}

	public void stop() {
		//先关闭服务端套接字
		serverChannel.close();
		//在斩断消息来源，停止io线程池
		group.shutdownGracefully();
		//最后停止业务线程
		collector.closeGracefully();
	}
}
