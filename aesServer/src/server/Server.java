package server;

import java.io.IOException;
import database.*;
import message.MessageType;

/**
 * This class represent the server which work with the database.
 */
public class Server extends AbstractServer 
{
  final public static int DEFAULT_PORT = 5555;
  MySqlConnection database;
  
  public Server(int port) 
  {
    super(port);
    this.database = new MySqlConnection();
  }

    
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
  {
	  	MessageType msgType = (MessageType)msg;
	  	System.out.println("Message recived from: "+client.getId()+ "  "+msgType.getAction());
	  	Object answer = MySqlConnection.action(ActionsType.getAction(msgType.getAction()), msgType.getObj());
	  	try {
	  		client.sendToClient(answer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println("Server has stopped listening for connections.");
  }
    
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    Server sv = new Server(port);
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
