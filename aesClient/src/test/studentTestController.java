package test;

import client.ActionsType;
import client.ActionsType.ActionNumber;
import client.ChatClient;
import client.Client;
import message.MessageType;

public class studentTestController {

	public static void startTest(String id, String code, ChatClient client)
	{
		String st[] = new String[2];
		st[0]=id;
		st[1]=code;
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.START_TEST),st);
		client.handleMessageFromClientUI(msg);
	}
	
}
