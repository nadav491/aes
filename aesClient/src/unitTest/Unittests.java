package unitTest;
import org.junit.jupiter.api.Test;
import client.Client;
import org.junit.Assert;
import user.User;

/**
 * This Junit test the login and logout functions.
 * The class is used when the server is running and their is a user with id=1 and password=1
 */

class Unittests {
	public static String HOST_IP = "";
	public static final int HOST_PORT = 5555;
	public static final Client client = new Client(HOST_IP,HOST_PORT);
	
	/**
	 * This test check if the login is successfully.
	 */
	@Test
	void testLogin_seccessLogin() {
    	String actual = User.login(client,"1","1").toString();
    	User.logout(client,"1");
    	String expected = "[Teacher, roie]";
    	Assert.assertEquals(expected,actual);
	}
	
	/**
	 * This test check if a user can login more then once.
	 */
	@Test
	void testLogin_doubleLogin() {
    	User.login(client,"1","1");
    	String actual = User.login(client,"1","1").toString();
    	User.logout(client,"1");
    	String expected = "[]";
    	Assert.assertEquals(expected,actual);
	}

	/**
	 * This test check if a non user can login.
	 */
	@Test
	void testLogin_nonExistingUserLogin() {
    	String actual = User.login(client,"123456","1").toString();
    	String expected = "[]";
    	Assert.assertEquals(expected,actual);
	}
	
	/**
	 * This test check if a user can login without a password.
	 */
	@Test
	void testLogin_nonPasswordLogin() {
    	String actual = User.login(client,"1","").toString();
    	String expected = "[]";
    	Assert.assertEquals(expected,actual);
	}
	
	/**
	 * This test check if a user can login without a matching password.
	 */
	@Test
	void testLogin_WrongPasswordLogin() {
    	String actual = User.login(client,"1","1234").toString();
    	String expected = "[]";
    	Assert.assertEquals(expected,actual);
	}
	
	/**
	 * This test check if a user can login without a userName.
	 */
	@Test
	void testLogin_nonUserNameLogin() {
    	String actual = User.login(client,"","123").toString();
    	String expected = "[]";
    	Assert.assertEquals(expected,actual);
	}
	
	/**
	 * This test check if a user can login without any info.
	 */
	@Test
	void testLogin_loginWithoutInfo() {
		String actual = User.login(client,"","").toString();
    	String expected = "[]";
    	Assert.assertEquals(expected,actual);
	}
	
	/**
	 * This test check if a user can logout.
	 */
	@Test
	void testLogout() {
	   	User.login(client,"1","1").toString();
	   	User.logout(client,"1");
    	String actual = User.login(client,"1","1").toString();
    	String expected = "[Teacher, roie]";
    	Assert.assertEquals(expected,actual);
	}
}
