package user;

/**
 * This class implement the user type. 
 */
public abstract class User {
	private String id;
	private String type;
	private boolean login;
	
	public User(String id, String type, boolean login) {
		this.id = id;
		this.type = type;
		this.login = login;
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
	public void setLogin(boolean login) {
		this.login = login;
	}

}
