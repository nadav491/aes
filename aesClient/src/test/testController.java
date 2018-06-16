package test;

import java.util.ArrayList;

import client.ActionsType;
import client.ActionsType.ActionNumber;
import client.ChatClient;
import message.MessageType;
public class testController {
	
	
	public static void createTest(Test test, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_CREATE),test);
		client.handleMessageFromClientUI(msg);
	}
	public static void updateQuestionsForTest(Test test, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_SET_QUESTIONS),test);
		client.handleMessageFromClientUI(msg);
	}
	public static void updateTest(Test test, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_UPDATE),test);
		client.handleMessageFromClientUI(msg);
	}
	public static void addCommentsForTeacher(Test test, String comments, ChatClient client)
	{
		Test t = new Test();
		t.setCode(test.getCode());
		t.setCommentsForTeacher(comments);
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_UPDATE_COMMENTS_TEACHER),t);
		client.handleMessageFromClientUI(msg);
	}
	public static void addCommentsForStudent(Test test, String comments, ChatClient client)
	{
		Test t = new Test();
		t.setCode(test.getCode());
		t.setCommentsForStudent(comments);
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_UPDATE_COMMENTS_STUDENT),t);
		client.handleMessageFromClientUI(msg);
	}

	public static void getAllTests(ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_GET_ALL), null);
		client.handleMessageFromClientUI(msg);
	}
	public static void getTestById(String code, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_GET_BY_ID), code);
		client.handleMessageFromClientUI(msg);
	}
	public static void deleteTest(String code, ChatClient client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.TEST_DELETE), code);
		client.handleMessageFromClientUI(msg);
	}
}
