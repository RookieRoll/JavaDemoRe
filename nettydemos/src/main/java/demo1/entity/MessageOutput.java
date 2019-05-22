package demo1.entity;

public class MessageOutput {
	private String requestId;
	private String type;
	private Object playLoad;


	public MessageOutput(String requestId, String type, Object playLoad) {
		this.requestId = requestId;
		this.type = type;
		this.playLoad = playLoad;
	}

	public String getType() {
		return type;
	}

	public Object getPlayLoad() {
		return playLoad;
	}

	public String getRequestId() {
		return requestId;
	}
}
