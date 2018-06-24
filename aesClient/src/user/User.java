package user;
import java.util.ArrayList;
import client.ActionsType;
import client.Client;
import client.ActionsType.ActionNumber;
import message.MessageType;

/**
 * This class implement the user type. 
 */
public abstract class User {
	private String id;
	private String type;
	private String name;

	public User(String id, String type, String name) {
		this.id = id;
		this.type = type;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	/**
	 * Login the user. Check the password and that the user is not login already. 
	 * @param client - the server to login from.
	 * @param id - the user id.
	 * @param password - the user password.
	 * @return The type and name of the user.
	 */
	public static ArrayList<String> login(Client client, String id, String password)
	{
		ArrayList<String> loginData = new ArrayList<String>();
		loginData.add(id);
		loginData.add(password);
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.USER_LOGIN),loginData);
		client.getChatClient().handleMessageFromClientUI(msg);
		client.waitForAnswer();
		return((ArrayList<String>)client.getAnswer());
	}

	/**
	 * Logout the user.
	 * @param client - the server to login from.
	 * @param id - the user id.
	 * @return true if the user was logout.
	 */
	public static void logout(Client client, String id)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.USER_LOGOUT),id);
		client.getChatClient().handleMessageFromClientUI(msg);
	}
}
