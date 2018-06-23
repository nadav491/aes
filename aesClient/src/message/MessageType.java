package message;


import java.io.Serializable;
/**
 * This class is used to determine how to transfer messages to server and client.
 * The class contains the action and object to send to the server.
 */
public class MessageType implements Serializable{
	private static final long serialVersionUID = 4977398872945543277L;
	private int action;
	private Object obj;
	
	public MessageType(int action, Object obj) {
		super();
		this.action = action;
		this.obj = obj;
	}
		
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
