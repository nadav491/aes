package server;

import database.ActionsType.ActionNumber;

/**
 * This class is used to determine how to transfer messages to server and client.
 */
public class MessageType {
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
