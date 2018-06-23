package question;
import java.util.ArrayList;
import client.ActionsType;
import client.ActionsType.ActionNumber;
import client.Client;
import message.MessageType;

/**
 * This is the question manager with all the database and question commends.
 */
public class QuestionManager {

	/**
	* Get all question in the database.
	* @param client - the client liked to the server.
	* @return The question arraylist.
	*/
	public static ArrayList<Question> getAllQuestion(Client client)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESIOTN_GET_ALL), "");
		client.getChatClient().handleMessageFromClientUI(msg);
		client.waitForAnswer();
		return (ArrayList<Question>)client.getAnswer();
	}

	/**
	* Add new question to database.
	* @param client - the client liked to the server.
	* @param questionToAdd - the new question.
	*/
	public static void addQuestion(Client client, Question questionToAdd)
	{
		if (questionToAdd.checkQuestion())
		{
			MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESTION_ADD), questionToAdd);
			client.getChatClient().handleMessageFromClientUI(msg);
			client.waitForAnswer();
		}
	}

	/**
	* Modify a question in the database.
	* Update using a given question code.
	* Update the question in the database to fit the corrent question.
	* @param client - the client liked to the server.
	* @param updatedQuestion - the question to update to.
	*/
	public static void modifyQuestion(Client client, Question updatedQuestion)
	{
		if (updatedQuestion.checkQuestion())
		{
			MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESTION_UPDATE), updatedQuestion);
			client.getChatClient().handleMessageFromClientUI(msg);
			client.waitForAnswer();
		}
	}

	/**
	* Remove a question from the database using the given qeustion code.
	* @param client - the client liked to the server.
	* @param code - the question code.
	*/
	public static void removeQuestion(Client client, String code)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESTION_REMOVE), code);
		client.getChatClient().handleMessageFromClientUI(msg);
		client.waitForAnswer();
	}

	/**
	* Get a question form the database by its code.
	* @param client - the client liked to the server.
	* @param code - the question code.
	* @return the question. Return null if not found.
	*/
	public static Question getQuestionByCode(Client client, String code)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESTION_GET_BY_CODE), code);
		client.getChatClient().handleMessageFromClientUI(msg);
		client.waitForAnswer();
		return (Question)client.getAnswer();
	}

	/**
	* Return an arraylist of all the questio in the database of a given owner.
	* @param client - the client liked to the server.
	* @param owner - the questions owner.
	* @return an arraylist of all the question. Return null if not found.
	*/
	public static ArrayList<Question> getQuestionListByOwner(Client client, String owner)
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESTION_GET_BY_OWNER), owner);
		client.getChatClient().handleMessageFromClientUI(msg);
		client.waitForAnswer();
		return (ArrayList<Question>)client.getAnswer();
	}


}
