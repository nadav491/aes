

import java.io.Serializable;

import database.ActionsType.ActionNumber;

/**
 * This class is used to determine how to transfer messages to server and client.
 */
public class MessageType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4977398872945543277L;
	private ActionNumber action;
	private Object obj;
	
	public MessageType(ActionNumber action, Object obj) {
		super();
		this.action = action;
		this.obj = obj;
	}
		
	public ActionNumber getAction() {
		return action;
	}
	public void setAction(ActionNumber action) {
		this.action = action;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
