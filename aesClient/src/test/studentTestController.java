package test;

import client.ActionsType;
import client.ActionsType.ActionNumber;
import client.ChatClient;
import client.Client;
import message.MessageType;

public class studentTestController {

	public static void submitStudentTest(studentTest std_test, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_CREATE), std_test);
		client.handleMessageFromClientUI(msg);
	}
	public static void getAllTestsByStudentId(String std_id, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_STUDENT_ID), std_id);
		client.handleMessageFromClientUI(msg);
	}
	public static void getAllTestsByTeacherId(String teacher_id, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_TEACHER_ID), teacher_id);
		client.handleMessageFromClientUI(msg);
	}
	public static void getAllTestsByCourseId(String course_id, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_COURSE_ID), course_id);
		client.handleMessageFromClientUI(msg);
	}
}
