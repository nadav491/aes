package user;

import java.util.ArrayList;

import client.ActionsType;
import client.Client;
import client.ActionsType.ActionNumber;
import message.MessageType;
import question.Question;

/**
 * This class implement the user type. 
 */
public abstract class User {
	private String id;
	private String password;
	private String type;
	private boolean login;
	
	public User(String id, String type, boolean login) {
		this.id = id;
		this.type = type;
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isLogin() {
		return login;
	}
	public static boolean login(Client client, String id, String password)
	{
		ArrayList<String> loginData = new ArrayList<String>();
		loginData.add(id);
		loginData.add(password);
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.USER_LOGIN),loginData);
		client.getChatClient().handleMessageFromClientUI(msg);
		client.waitForAnswer();
		return (boolean)client.getAnswer();
	}

}
