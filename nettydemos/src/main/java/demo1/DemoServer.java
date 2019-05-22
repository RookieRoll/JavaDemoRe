package demo1;

import demo1.Handler.ExpRequestHandler;
import demo1.Handler.FibRequestHandler;
import demo1.entity.ExpRequest;
import demo1.entity.RPCServer;

public class DemoServer {
	public static void main(String[] args) {
		RPCServer server = new RPCServer("localhost", 8888, 1, 16);
		server.service("fib", Integer.class, new FibRequestHandler())
				.service("exp", ExpRequest.class, new ExpRequestHandler());
		server.start();
	}
}
