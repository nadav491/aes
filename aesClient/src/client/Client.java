package client;

import java.io.*;
import java.util.ArrayList;

import Student.Student;
import client.ActionsType.ActionNumber;
import message.MessageType;
import question.Question;
import question.QuestionManager;
import test.ExecutedTest;
import test.MyFile;
import test.Test;
import test.studentTest;
import test.studentTestController;
import test.testController;

public class Client implements ChatIF {
	private Object answer;
	private boolean gotAnswer;
	final public static int DEFAULT_PORT = 5555;

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	private ChatClient chatClient;

	/**
	 * Constructs an instance of the ClientConsole UI.
	 * 
	 * @param host
	 *            The host to connect to.
	 * @param port
	 *            The port to connect on.
	 */
	public Client(String host, int port) {
		try {
			chatClient = new ChatClient(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection! Terminating client.");
		}
	}

	/**
	 * Use to store the answer given form the server.
	 * 
	 * @param answer
	 */
	public void setAnswer(Object answer) {
		this.answer = answer;
		this.gotAnswer = true;
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message
	 *            The string to be displayed.
	 */
	public void display(String message) {
		System.out.println("> " + message);
	}

	/**
	 * Used because of the daily from the server. Wait until the server answers.
	 */
	public void waitForAnswer() {
		while (this.gotAnswer == false) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.gotAnswer = false;
	}

	/**
	 * Retrieve the answer from the server.
	 * 
	 * @return
	 */
	public Object getAnswer() {
		return answer;
	}

	/**
	 * Get all the question from the database.
	 * 
	 * @return arraylist of all the question from the database.
	 */
	public ArrayList<Question> getAllQuestion() {
		return QuestionManager.getAllQuestion(this);
	}

	/**
	 * Add new question to database.
	 * 
	 * @param questionToAdd
	 *            - the new question.
	 */
	public void addQuestion(Question questionToAdd) {
		QuestionManager.addQuestion(this, questionToAdd);
	}

	/**
	 * Modify a question in the database. Update using a given question code. Update
	 * the question in the database to fit the corrent question.
	 * 
	 * @param updatedQuestion
	 *            - the question to update to.
	 */
	public void modifyQuestion(Question updatedQuestion) {
		QuestionManager.modifyQuestion(this, updatedQuestion);
	}

	/**
	 * Remove a question from the database using the given qeustion code.
	 * 
	 * @param client
	 *            - the client liked to the server.
	 */
	public void removeQuestion(String code) {
		QuestionManager.removeQuestion(this, code);
	}

	/**
	 * Get a question form the database by its code.
	 * 
	 * @param client
	 *            - the client liked to the server.
	 * @param code
	 *            - the question code.
	 * @return the question. Return null if not found.
	 */
	public Question getQuestionByCode(String code) {
		return QuestionManager.getQuestionByCode(this, code);
	}

	/**
	 * Return an arraylist of all the questio in the database of a given owner.
	 * 
	 * @param owner
	 *            - the questions owner.
	 * @return an arraylist of all the question. Return null if not found.
	 */
	public ArrayList<Question> getQuestionListByOwner(Client client, String owner) {
		return QuestionManager.getQuestionListByOwner(this, owner);
	}

	public boolean createTest(Test test) {
		testController.createTest(test, this.chatClient);
		waitForAnswer();
		return (boolean) this.answer;
	}

	public boolean updateQuestionsForTest(Test test) {
		testController.updateQuestionsForTest(test, this.chatClient);
		waitForAnswer();
		return (boolean) this.answer;
	}

	public boolean updateTest(Test test) {
		testController.updateTest(test, this.chatClient);
		waitForAnswer();
		return (boolean) this.answer;
	}

	public boolean addCommentsForTeacher(Test test, String comments) {
		testController.addCommentsForTeacher(test, comments, this.chatClient);
		waitForAnswer();
		return (boolean) this.answer;
	}

	public boolean addCommentsForStudent(Test test, String comments) {
		testController.addCommentsForStudent(test, comments, this.chatClient);
		waitForAnswer();
		return (boolean) this.answer;
	}

	public ArrayList<Test> getAllTests() {
		testController.getAllTests(this.chatClient);
		waitForAnswer();
		return (ArrayList<Test>) this.answer;
	}

	public Test getTestById(String id) {
		testController.getTestById(id, this.chatClient);
		waitForAnswer();
		return (Test) this.answer;
	}

	public boolean deleteTest(String code) {
		testController.deleteTest(code, this.chatClient);
		waitForAnswer();
		return (boolean) this.answer;
	}

	public boolean submitStudentTest(studentTest st) {
		studentTestController.submitStudentTest(st, this.chatClient);
		waitForAnswer();
		return (boolean) this.answer;
	}

	public Student getAllTestsByStudentId(String stud_id) {
		studentTestController.getAllTestsByStudentId(stud_id, this.chatClient);
		waitForAnswer();
		return (Student) this.answer;
	}

	public studentTest[] getAllTestsByTeacherId(String teacher_id) {
		studentTestController.getAllTestsByTeacherId(teacher_id, this.chatClient);
		waitForAnswer();
		return (studentTest[]) this.answer;
	}

	public studentTest[] getAllTestsByCourseId(String course_id) {
		studentTestController.getAllTestsByCourseId(course_id, this.chatClient);
		waitForAnswer();
		return (studentTest[]) this.answer;
	}

	public void AddToExecutreTest(ExecutedTest test) {
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.EXECUTED_TEST_ADD), test);
		this.chatClient.handleMessageFromClientUI(msg);
	}

	public ArrayList<ExecutedTest> GetAllExecutreTest() {
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.EXECUTED_TEST_GET_ALL), " ");
		this.chatClient.handleMessageFromClientUI(msg);
		waitForAnswer();
		System.out.println(this.answer);
		if(this.answer.getClass() != ArrayList.class)
			return(new ArrayList<ExecutedTest>());
		return (ArrayList<ExecutedTest>) this.answer;
	}

	public ArrayList<ExecutedTest> UpdateExecutreTest(ExecutedTest test) {
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.EXECUTED_TEST_UPDATE), test);
		this.chatClient.handleMessageFromClientUI(msg);
		waitForAnswer();
		return (ArrayList<ExecutedTest>) this.answer;
	}

	public void UpdateStudentTest(studentTest test) {
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.STUDENT_TEST_UPDATE), test);
		this.chatClient.handleMessageFromClientUI(msg);
	}

	public ChatClient getChatClient() {
		return chatClient;
	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	/**
	 * This function sends a file to the server.
	 * @param LocalfilePath - the file full path.
	 */
	public void uploadFile(String LocalfilePath) {
		MyFile msg = new MyFile(LocalfilePath);
		try {

			File newFile = new File(LocalfilePath);

			byte[] mybytearray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);

			msg.initArray(mybytearray.length);
			msg.setSize(mybytearray.length);

			bis.read(msg.getMybytearray(), 0, mybytearray.length);
			MessageType fileMsg = new MessageType(ActionsType.getValue(ActionNumber.UPLOAD_FILE), msg);
			this.chatClient.handleMessageFromClientUI(fileMsg);
			bis.close();
		} catch (Exception e) {
			System.out.println("Error send (Files)msg) to Server");
		}
	}
	
	/**
	 * This function downloads a file from the server.
	 * @param file - The files path.
	 */
	public MyFile downloadFile(String LocalfilePath) {
		MessageType fileMsg = new MessageType(ActionsType.getValue(ActionNumber.DOWNLOAD_FILE), LocalfilePath);
		this.chatClient.handleMessageFromClientUI(fileMsg);
		waitForAnswer();
		MyFile file = (MyFile)this.answer;
		try {
			
			File newFile = new File(file.getFileName());
			FileOutputStream fos = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(file.getMybytearray());
			bos.close();
		} catch (Exception e) {
			System.out.println("Error send (Files)msg) to Server");
		} 
		return file;
	}

	/**
	 * This function get all the courses id from the server.
	 * @return ArrayList<String> of the courses id.
	 */
	public ArrayList<String> getCoursesId()
	{
		MessageType msg = new MessageType(ActionsType.getValue(ActionNumber.COURSE_GET_ID_LIST), " ");
		this.chatClient.handleMessageFromClientUI(msg);
		waitForAnswer();
		return (ArrayList<String>) this.answer;	
	}
}
// End of ConsoleChat class
