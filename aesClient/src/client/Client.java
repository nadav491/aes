package client;

import java.io.*;
import java.util.ArrayList;

import Student.Student;
import question.Question;
import question.QuestionManager;
import test.Test;
import test.studentTest;
import test.studentTestController;
import test.testController;

public class Client implements ChatIF
{
	private Object answer;
	private boolean gotAnswer;
	final public static int DEFAULT_PORT = 5555;

	/**
	* The instance of the client that created this ConsoleChat.
	*/
	private ChatClient chatClient;


	/**
	* Constructs an instance of the ClientConsole UI.
	* @param host The host to connect to.
	* @param port The port to connect on.
	*/
	public Client(String host, int port)
	{
		try
		{
			chatClient = new ChatClient(host, port, this);
		}
		catch (IOException exception)
		{
			System.out.println("Error: Can't setup connection! Terminating client.");
		}
	}

	/**
	 * Use to store the answer given form the server.
	 * @param answer
	 */
	public void setAnswer(Object answer)
	{
		this.answer = answer;
		this.gotAnswer = true;
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

	/**
	 * Used because of the daily from the server.
	 * Wait until the server answers.
	 */
	public void waitForAnswer()
	{
		while (this.gotAnswer == false)
		{
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.gotAnswer = false;
	}

	/**
	 * Retrieve the answer from the server.
	 * @return
	 */
	public Object getAnswer() {
		return answer;
	}

	/**
	 * Get all the question from the database.
	 * @return arraylist of all the question from the database.
	 */
	public ArrayList<Question> getAllQuestion()
	{
		return QuestionManager.getAllQuestion(this);
	}

	/**
	 * Add new question to database.
	 * @param questionToAdd - the new question.
	 */
	public void addQuestion(Question questionToAdd)
	{
		QuestionManager.addQuestion(this, questionToAdd);
	}

	/**
	 * Modify a question in the database. 
	 * Update using a given question code.
	 * Update the question in the database to fit the corrent question.
	 * @param updatedQuestion - the question to update to.
	 */
	public void modifyQuestion(Question updatedQuestion)
	{
		QuestionManager.modifyQuestion(this, updatedQuestion);
	}
	
	/**
	 * Remove a question from the database using the given qeustion code.
	 * @param client - the client liked to the server.
	 */
	public void removeQuestion(String code)
	{
		QuestionManager.removeQuestion(this, code);
	}
	
	/**
	 * Get a question form the database by its code.
	 * @param client - the client liked to the server.
	 * @param code - the question code.
	 * @return the question. Return null if not found.
	 */
	public Question getQuestionByCode(String code)
	{
		return QuestionManager.getQuestionByCode(this, code);
	}

	/**
	 * Return an arraylist of all the questio in the database of a given owner.
	 * @param owner - the questions owner.
	 * @return an arraylist of all the question. Return null if not found.
	 */
	public ArrayList<Question> getQuestionListByOwner(Client client, String owner)
	{
		return QuestionManager.getQuestionListByOwner(this, owner);
	}
	
	public boolean createTest(Test test)
	  {
		  testController.createTest(test, this.chatClient);
		  waitForAnswer();
		  return (boolean)this.answer;
	  }
	  public boolean updateQuestionsForTest(Test test)
	  {
		  testController.updateQuestionsForTest(test, this.chatClient);
		  waitForAnswer();
		  return (boolean)this.answer;
	  }
	  public boolean updateTest(Test test)
	  {
		  testController.updateTest(test, this.chatClient);
		  waitForAnswer();
		  return (boolean)this.answer;
	  }
	  public boolean addCommentsForTeacher(Test test, String comments)
	  {
		  testController.addCommentsForTeacher(test, comments, this.chatClient);
		  waitForAnswer();
		  return (boolean)this.answer;
	  }
	  public boolean addCommentsForStudent(Test test, String comments)
	  {
		  testController.addCommentsForStudent(test, comments, this.chatClient);
		  waitForAnswer();
		  return (boolean)this.answer;
	  }
	  public ArrayList<Test> getAllTests()
	  {
		  testController.getAllTests(this.chatClient);
		  waitForAnswer();
		  return (ArrayList<Test>)this.answer;
	  }
	  public Test getTestById(String id)
	  {
		  testController.getTestById(id, this.chatClient);
		  waitForAnswer();
		  return (Test)this.answer;
	  }
	  public boolean deleteTest(String code)
	  {
		  testController.deleteTest(code, this.chatClient);
		  waitForAnswer();
		  return (boolean)this.answer;
	  }
	  public boolean submitStudentTest(studentTest st)
	  {
		  studentTestController.submitStudentTest(st, this.chatClient);
		  waitForAnswer();
		  return (boolean)this.answer;
	  }
	  public Student getAllTestsByStudentId(String stud_id)
	  {
		  studentTestController.getAllTestsByStudentId(stud_id, this.chatClient);
		  waitForAnswer();
		  return (Student)this.answer;
	  }
	  public studentTest[] getAllTestsByTeacherId(String teacher_id)
	  {
		  studentTestController.getAllTestsByTeacherId(teacher_id, this.chatClient);
		  waitForAnswer();
		  return (studentTest[])this.answer;
	  }
	  public studentTest[] getAllTestsByCourseId(String course_id)
	  {
		  studentTestController.getAllTestsByCourseId(course_id, this.chatClient);
		  waitForAnswer();
		  return (studentTest[])this.answer;
	  }
	  
	public ChatClient getChatClient() 
	{
		return chatClient;
	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

}
//End of ConsoleChat class
