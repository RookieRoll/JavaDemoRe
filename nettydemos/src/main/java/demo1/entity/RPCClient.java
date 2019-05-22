package demo1.entity;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.json.JsonObjectDecoder;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

public class RPCClient {
	private String ip;
	private int port;
	private Socket sock;
	private DataInputStream inputStream;
	private OutputStream outputStream;

	public RPCClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void connect() throws IOException {
		SocketAddress address = new InetSocketAddress(ip, port);
		sock = new Socket();
		sock.connect(address, 5000);//5s超时
		inputStream = new DataInputStream(sock.getInputStream());
		outputStream = sock.getOutputStream();
	}

	public void close() {
		try {
			sock.close();
			sock = null;
			inputStream = null;
			outputStream = null;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object send(String type, Object playload) {
		try {
			return this.sendInternal(type, playload, false);
		} catch (IOException ex) {
			throw new RPCException(ex);
		}
	}

	public RPCClient rpc(String type, Class<?> clazz) {
		ResponseRegistry.register(type, clazz);
		return this;
	}

	public void cast(String type, Object payload) throws IOException {
		try {
			this.sendInternal(type, payload, true);
		} catch (IOException ex) {
			throw new RPCException(ex);
		}
	}

	private Object sendInternal(String type, Object payLoad, boolean cast) throws IOException {
		if (outputStream == null)
			connect();

		String requestId = RequestId.next();
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream buf = new DataOutputStream(bytes);
		writeStr(buf, requestId);
		writeStr(buf, type);
		writeStr(buf, JSON.toJSONString(payLoad));
		buf.flush();
		byte[] fullLoad = bytes.toByteArray();
		try {
			outputStream.write(fullLoad);

		} catch (Exception ex) {
			close();
			connect();
			outputStream.write(fullLoad);
		}
		if (!cast) {
			String reqId = readStr();
			if (!requestId.equals(reqId)) {
				close();
				throw new RPCException("request id mismatch");
			}
			String typ = readStr();
			Class<?> clazz = ResponseRegistry.get(typ);
			if (clazz == null) {
				throw new RPCException("unrecognized rpc response type=" + typ);
			}
			String payId = readStr();
			Object res = JSON.parseObject(payId, clazz);
			return res;
		}
		return null;

	}

	private String readStr() throws IOException {
		int len = inputStream.readInt();
		byte[] bytes = new byte[len];
		inputStream.readFully(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}

	private void writeStr(DataOutputStream buf, String requestId) throws IOException {
		buf.writeInt(requestId.length());
		buf.write(requestId.getBytes(StandardCharsets.UTF_8));
	}
}
