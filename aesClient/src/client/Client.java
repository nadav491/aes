package client;

import java.io.*;
import java.util.ArrayList;

import org.omg.CosNaming.IstringHelper;

import client.ActionsType.ActionNumber;
import message.MessageType;
import question.Question;

public class Client implements ChatIF 
{
  private Object answer;
  private boolean gotAnswer;
  final public static int DEFAULT_PORT = 5555;
    
  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient client;

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */
  public Client(String host, int port) 
  {
    try 
    {
      client= new ChatClient(host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection! Terminating client.");
      System.exit(1);
    }
  }
  
  public void setAnswer(Object answer)
  {
	  this.answer = answer;
	  this.gotAnswer = true;
  }
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */
  public void accept() 
  {
	  System.out.println(getAllQuestion());
	  Question q = getQuestionById("33333");
	  q.setCorrect(2);
	  System.out.println(updateQuestion(q));
  }

  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void display(String message) 
  {
    System.out.println("> " + message);
  }
  
  private void waitForAnswer() 
  {
	  while(this.gotAnswer == false)
	  {
		  try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  this.gotAnswer = false;
  }
  
  public ArrayList<Question> getAllQuestion()
	{
	  MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESIOTN_GET_ALL),"");
	  this.client.handleMessageFromClientUI(msg);
	  waitForAnswer();
	  return (ArrayList<Question>) this.answer;
	}
  
	private Question getQuestionById(String code)
	{
		  MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESTION_GET_BY_ID),code);
		  this.client.handleMessageFromClientUI(msg);
		  waitForAnswer();
		  return (Question) this.answer;
	}
	
	private boolean updateQuestion(Question updatedQuestion)
	{
		  MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.QUESTION_UPDATE),updatedQuestion);
		  this.client.handleMessageFromClientUI(msg);
		  waitForAnswer();
		  return (boolean) this.answer;
	}
  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */
  public static void main(String[] args) 
  {
    String host = "";
    int port = 0;  //The port number
    try
    {
      host = args[0];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      host = "localhost";
    }
    Client chat= new Client(host, DEFAULT_PORT);
    chat.accept();  //Wait for console data
  }
}
//End of ConsoleChat class
