package test;
import client.ActionsType;
import client.ActionsType.ActionNumber;
import client.ChatClient;
import message.MessageType;
/**
 * This class is the test controller with all the test commends including the database commends.
 */
public class testController {
	
	/**
	 * Create a new test in the database.
	 * @param test - the test to create.
	 * @param client - the client to send to.
	 */
	public static void createTest(Test test, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_CREATE),test);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Update the question of a test in the database.
	 * @param test - the test to update with the updated question.
	 * @param client - the client to send to.
	 */
	public static void updateQuestionsForTest(Test test, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_SET_QUESTIONS),test);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Update a test in the database by id to the given test.
	 * @param test - the test to update.
	 * @param client - the client to send to.
	 */
	public static void updateTest(Test test, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_UPDATE),test);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Add comments to the teacher in the test database by the given test id.
	 * @param comments - the comments to add.
	 * @param test - the test to update.
	 * @param client - the client to send to.
	 */
	public static void addCommentsForTeacher(Test test, String comments, ChatClient client)
	{
		Test t = new Test();
		t.setCode(test.getCode());
		t.setCommentsForTeacher(comments);
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_UPDATE_COMMENTS_TEACHER),t);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Add comments to the student in the test database by the given test id.
	 * @param comments - the comments to add.
	 * @param test - the test to update.
	 * @param client - the client to send to.
	 */
	public static void addCommentsForStudent(Test test, String comments, ChatClient client)
	{
		Test t = new Test();
		t.setCode(test.getCode());
		t.setCommentsForStudent(comments);
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_UPDATE_COMMENTS_STUDENT),t);
		client.handleMessageFromClientUI(msg);
	}

	/**
	 * Get all test in the database.
	 * @param client - the client to send to.
	 */
	public static void getAllTests(ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_GET_ALL), null);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Get a test by code id from the database.
	 * @param code - the test code.
	 * @param client - the client to send to.
	 */
	public static void getTestById(String code, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_GET_BY_ID), code);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Delete a test by its code.
	 * @param code - the test code.
	 * @param client - the client to send to.
	 */
	public static void deleteTest(String code, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_DELETE), code);
		client.handleMessageFromClientUI(msg);
	}
}
