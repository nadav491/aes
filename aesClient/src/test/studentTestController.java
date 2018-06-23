package test;
import client.ActionsType;
import client.ActionsType.ActionNumber;
import client.ChatClient;
import message.MessageType;
/**
 * This is the finished test (student test) controller.
 */
public class studentTestController {

	/**
	 * Submit the test to the database.
	 * @param std_test- the test to submit.
	 * @param client - the server to send.
	 */
	public static void submitStudentTest(studentTest std_test, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_CREATE), std_test);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Get all the tests by student id.
	 * @param std_id - the student id.
	 * @param client - the server to send.
	 */
	public static void getAllTestsByStudentId(String std_id, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_STUDENT_ID), std_id);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Get all the tests by teacher id.
	 * @param teacher_id - the teacher id.
	 * @param client - the server to send.
	 */
	public static void getAllTestsByTeacherId(String teacher_id, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_TEACHER_ID), teacher_id);
		client.handleMessageFromClientUI(msg);
	}
	
	/**
	 * Get all the tests by course id.
	 * @param course_id - the course id.
	 * @param client - the server to send.
	 */
	public static void getAllTestsByCourseId(String course_id, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_COURSE_ID), course_id);
		client.handleMessageFromClientUI(msg);
	}
}
