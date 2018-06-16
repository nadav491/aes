package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import database.ActionsType.ActionNumber;
import question.Question;

public class MySqlConnection
{
	private static Connection conn; 
	private final static String QUESTION_DATABASE_NAME = "question";
	private final static String USER_DATABASE_NAME = "user";

	//private final static int DATABASE_SIZE_QUESTION = 8;
	
	public MySqlConnection() 
	{
		MySqlConnection.connect();
	}
	/**
	 * Connect to the database.
	**/
	public static void connect() 
	{
		try 
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}
        
        try 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/aes","root","root");
            System.out.println("SQL connection succeed");
     	} catch (SQLException ex) 
     	    {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}
	
	/**
	 * This function execute the queries.
	 * @param action - the action to take.
	 * @param obj - the object to use in the action.
	 * @return if there is an object return it , else return null.
	 */
	public static Object action(ActionNumber action, Object obj)
	{		
		switch(action)
		{
		case QUESIOTN_GET_ALL: return(getAllQuestion()); 
		case QUESTION_GET_BY_CODE: return(getQuestionByCode((String)obj));
		case QUESTION_UPDATE: return(updateQuestion((Question)obj));
		case QUESTION_ADD: return(addQuestion((Question)obj));
		case QUESTION_REMOVE: return(removeQuestionByCode((String)obj));
		case QUESTION_GET_BY_OWNER: return(getQuestionByOwner((String)obj));
		case USER_LOGIN: return(userChecklogin((ArrayList<String>)obj));
		}
		return null;
	}
	
	/**
	 * This function get all the question from the database. 
	 * @return ArrayList<Question> of the questions.
	 */
	private static ArrayList<Question> getAllQuestion()
	{
		Statement stmt;
		ArrayList<Question> allQuestions = new ArrayList<Question>();
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+QUESTION_DATABASE_NAME+";");
	 		while(rs.next())
	 		{
	 			Question buildQuestion = new Question(rs.getString(1),rs.getString(2),rs.getString(3)
	 					,rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
 				allQuestions.add(buildQuestion);
			} 
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return allQuestions;
	}

	/**
	 * This function get a question by its code.
	 * @param code - the question code
	 * @return the question or null if its not found.
	 */
	private static Question getQuestionByCode(String code)
	{
		Statement stmt;
		if(code == null)
			return null;
		Question question = null;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+QUESTION_DATABASE_NAME+" WHERE code="+code+";");
			if(rs.next())
			{
				question = new Question(rs.getString(1),rs.getString(2),rs.getString(3)
 					,rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return question;
	}
	
	/**
	 * Update the question in the database to the question given.
	 * @param updatedQuestion - the new question. Find the old one using the code in the new.
	 * @return - True if the question was updated , false otherwise. 
	 */
	private static boolean updateQuestion(Question updatedQuestion)
	{
		if(updatedQuestion == null || !updatedQuestion.checkQuestion())
			return false;
		try 
		{
			PreparedStatement update = conn.prepareStatement("UPDATE question Set code=?, owner=?, body=?"
					+ ",answer1=? ,answer2=? ,answer3=? ,answer4=? ,correct=?, courseList=?, instruction=? WHERE code=?;");
			update.setString(1, updatedQuestion.getCode());
			update.setString(2, updatedQuestion.getOwner());
			update.setString(3, updatedQuestion.getBody());
			update.setString(4, updatedQuestion.getAnswer1());
			update.setString(5, updatedQuestion.getAnswer2());
			update.setString(6, updatedQuestion.getAnswer3());
			update.setString(7, updatedQuestion.getAnswer4());
			update.setInt(8, updatedQuestion.getCorrect());
			update.setString(9, updatedQuestion.courseCodeListToString());
			update.setString(10, updatedQuestion.getInstruction());
			update.setString(11, updatedQuestion.getCode());
			update.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return true;
	}

	private static boolean removeQuestionByCode(String code)
	{
		if(code == null)
			return false;
		try 
		{
			PreparedStatement update = conn.prepareStatement("DELETE  FROM "+QUESTION_DATABASE_NAME+" WHERE code=\""+code+"\";");
			update.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return true;
	}
	
	private static boolean addQuestion(Question question)
	{
		if(question == null || !question.checkQuestion())
			return false;
		try 
		{
			PreparedStatement update = conn.prepareStatement("INSERT into question value(?,?,?,?,?,?,?,?,?,?);");
			update.setString(1, question.getCode());
			update.setString(2, question.getOwner());
			update.setString(3, question.getBody());
			update.setString(4, question.getAnswer1());
			update.setString(5, question.getAnswer2());
			update.setString(6, question.getAnswer3());
			update.setString(7, question.getAnswer4());
			update.setInt(8, question.getCorrect());
			update.setString(9, question.getCode());
			update.setString(10, question.courseCodeListToString());
			update.setString(11, question.getInstruction());
			update.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return true;
	}

	private static ArrayList<Question> getQuestionByOwner(String owner)
	{
		Statement stmt;
		if(owner == null)
			return null;
		ArrayList<Question> questionList = new ArrayList<Question>();
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+QUESTION_DATABASE_NAME+" WHERE owner=\""+owner+"\";");
			while(rs.next())
			{
				Question question = new Question(rs.getString(1),rs.getString(2),rs.getString(3)
						,rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
				questionList.add(question);			
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		System.out.println(questionList);
		return questionList;
	}
	

	private static boolean userChecklogin(ArrayList<String> info)
	{
		if(info == null)
			return false;
		Statement stmt;
		boolean flag = false;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password FROM "+USER_DATABASE_NAME+" WHERE id=\""+info.get(0)+"\";");
			if(rs.next())
				if(rs.getString(1) == info.get(1))
					flag=true;
			System.out.print(rs.getString(1)+"  "+ info.get(1));
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return flag;
	}
}

