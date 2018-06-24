package database;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Student.Student;
import java.sql.PreparedStatement;
import database.ActionsType.ActionNumber;
import question.Question;
import test.ExecutedTest;
import test.MyFile;
import test.Test;
import test.studentTest;

/**
* This class is used to connect to the database.
* In this class we have all the commends used for the database including the sql.
*/
public class MySqlConnection
{
	private static Connection conn;
	private final static String QUESTION_DATABASE_NAME = "question";
	private final static String USER_DATABASE_NAME = "user";
	private final static String TEST_DATABASE_NAME = "test";
	private final static String STUDENT_DATABASE_NAME = "student";
	private final static String STUDENT_TEST_DATABASE_NAME = "studentTest";
	private final static String COURSES_DATABASE_NAME = "course";
	private final static String Subjects_DATABASE_NAME = "subject";
	private final static String TEST_FILE_LOCATION = "saveTestFiles\\";
	private final static String DATABASE_PASSWORD = "root";
	private static ArrayList<ExecutedTest> executedTests;
	
	public MySqlConnection()
	{
		executedTests = new ArrayList<ExecutedTest>();
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
		}
		catch (Exception ex) {/* handle the error*/ }

		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/aes", "root", DATABASE_PASSWORD);
			System.out.println("SQL connection succeed");
		}
		catch (SQLException ex)
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
		switch (action)
		{
		case QUESIOTN_GET_ALL: return(getAllQuestion());
		case QUESTION_GET_BY_CODE: return(getQuestionByCode((String)obj));
		case QUESTION_UPDATE: return(updateQuestion((Question)obj));
		case QUESTION_ADD: return(addQuestion((Question)obj));
		case QUESTION_GET_BY_OWNER: return(getQuestionByOwner((String)obj));
		case USER_LOGIN: return(userChecklogin((ArrayList<String>)obj));
		case USER_LOGOUT: return(userLogout((String)obj));


		case TEST_CREATE: return createTest((Test)obj);
		case TEST_SET_QUESTIONS: return setQuestionsForTest((Test)obj);
		case TEST_UPDATE: return (deleteTest(((Test)obj).getCode()) && createTest((Test)obj));
		case TEST_UPDATE_COMMENTS_TEACHER: return addCommentsForTeacher((Test)obj);
		case TEST_UPDATE_COMMENTS_STUDENT: return addCommentsForStudent((Test)obj);
		case TEST_GET_ALL: return getAllTests();
		case TEST_GET_BY_ID: return getTestById((String)obj);
		case TEST_DELETE: return deleteTest((String)obj);

		case STUDENT_TEST_CREATE: return createStudentTest((studentTest)obj);
		case STUDENT_TEST_GET_ALL_TESTS_BY_STUDENT_ID: return getAllTestsByStudentId((String)obj);
		case STUDENT_TEST_GET_ALL_TESTS_BY_TEACHER_ID: return getAllTestsByTeacherId((String)obj);
		case STUDENT_TEST_GET_ALL_TESTS_BY_COURSE_ID: return getAllTestsByCourseId((String)obj);
		case STUDENT_TEST_UPDATE: return createStudentTest((studentTest)obj);

		case EXECUTED_TEST_ADD: executedTests.add((ExecutedTest)obj); return " ";
		case EXECUTED_TEST_GET_ALL: return(getExecutedTests());
		case EXECUTED_TEST_UPDATE: return(UpdateExecutedTests((ExecutedTest)obj));
		case EXECUTED_TEST_CHECK_LOCK_TEST: return(executedTestsCheckLock((ExecutedTest)obj));

		case UPLOAD_FILE: uploadFile((MyFile)obj); return " ";
		case DOWNLOAD_FILE: return downloadFile((ExecutedTest)obj);
		case COURSE_GET_ID_LIST: return getCoursesId();
		case Subject_GET_ID_LIST: return getSubjectsId();
		case STUDENT_GET_ALL_NAME: return getAllUsersNamesByType((String)obj);
		case TEACHER_GET_ALL_NAME: return getAllUsersNamesByType((String)obj);
		default: return null;
		}
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + QUESTION_DATABASE_NAME + ";");
			while (rs.next())
			{
				Question buildQuestion = new Question(rs.getString(1), rs.getString(2), rs.getString(3)
					, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
				allQuestions.add(buildQuestion);
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
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
		if (code == null)
			return null;
		Question question = null;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + QUESTION_DATABASE_NAME + " WHERE code=" + code + ";");
			if (rs.next())
			{
				question = new Question(rs.getString(1), rs.getString(2), rs.getString(3)
					, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return question;
	}

	/**
	* Update the question in the database to the question given.
	* @param updatedQuestion - the new question. Find the old one using the code in the new.
	* @return - True if the question was updated , false otherwise.
	*/
	private static boolean updateQuestion(Question updatedQuestion)
	{
		if (updatedQuestion == null || !updatedQuestion.checkQuestion())
			return false;
		try
		{
			PreparedStatement update = conn.prepareStatement("UPDATE " + QUESTION_DATABASE_NAME + " Set code=?, owner=?, body=?"
				+ ",answer1=? ,answer2=? ,answer3=? ,answer4=? ,correct=?, courseList=?, Sinstructions=?, Tinstructions=? WHERE code=?;");
			update.setString(1, updatedQuestion.getCode());
			update.setString(2, updatedQuestion.getOwner());
			update.setString(3, updatedQuestion.getBody());
			update.setString(4, updatedQuestion.getAnswer1());
			update.setString(5, updatedQuestion.getAnswer2());
			update.setString(6, updatedQuestion.getAnswer3());
			update.setString(7, updatedQuestion.getAnswer4());
			update.setInt(8, updatedQuestion.getCorrect());
			update.setString(9, updatedQuestion.getCourseList());
			update.setString(10, updatedQuestion.getSInstruction());
			update.setString(11, updatedQuestion.getTInstruction());
			update.setString(12, updatedQuestion.getCode());
			update.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}

	/**
	* Add a given question to the database.
	* @param question - the given question
	* @return true if successes , else false.
	*/
	private static boolean addQuestion(Question question)
	{
		if (question == null || !question.checkQuestion())
			return false;
		ArrayList<Question> questions = getAllQuestion();
		String questionNumber = String.format("%3d", questions.size()).replace(' ', '0');
		try
		{
			PreparedStatement update = conn.prepareStatement("INSERT into " + QUESTION_DATABASE_NAME + " value(?,?,?,?,?,?,?,?,?,?,?);");
			update.setString(1, question.getCode().substring(0, 2) + questionNumber);
			update.setString(2, question.getOwner());
			update.setString(3, question.getBody());
			update.setString(4, question.getAnswer1());
			update.setString(5, question.getAnswer2());
			update.setString(6, question.getAnswer3());
			update.setString(7, question.getAnswer4());
			update.setInt(8, question.getCorrect());
			update.setString(9, question.getCode());
			update.setString(10, question.getCourseList());
			update.setString(11, question.getSInstruction());
			update.setString(11, question.getTInstruction());
			update.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}

	/**
	* Get a arrayList of all the question created by the given owner.
	* @param owner - the owner.
	* @return the arrayList
	*/
	private static ArrayList<Question> getQuestionByOwner(String owner)
	{
		Statement stmt;
		if (owner == null)
			return null;
		ArrayList<Question> questionList = new ArrayList<Question>();
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + QUESTION_DATABASE_NAME + " WHERE owner=\"" + owner + "\";");
			while (rs.next())
			{
				Question question = new Question(rs.getString(1), rs.getString(2), rs.getString(3)
					, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
				questionList.add(question);
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return questionList;
	}

	/**
	* Update the login field of the user.
	* @param userName - the user name.
	* @param login - the new status.
	* @return true if success.
	*/
	private static void userUpdateLogin(String userName, boolean login)
	{
		if (userName == "" || userName == null)
			return;
		try
		{
			PreparedStatement update = conn.prepareStatement("UPDATE " + USER_DATABASE_NAME + " Set login=? WHERE id=?;");
			update.setBoolean(1, login);
			update.setString(2, userName);
			update.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
	}

	/**
	* Check if the user password and check if the user hasn't login yet.
	* @param info - ArrayList of user name and password.
	* @return The type and name of the user.
	*/
	private static ArrayList<String> userChecklogin(ArrayList<String> info)
	{
		if (info == null || info.get(0) == "" || info.get(1) == "")
			return null;
		Statement stmt;
		ArrayList<String> typeName = new ArrayList<String>();
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + USER_DATABASE_NAME + " WHERE id=\"" + info.get(0) + "\";");
			if (rs.next())
				if (rs.getString(2).compareTo(info.get(1)) == 0 && !rs.getBoolean(4))
				{
					typeName.add(rs.getString(3));
					typeName.add(rs.getString(5));
					userUpdateLogin(info.get(0),true);
				}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return typeName;
	}

	/**
	* Logout the user.
	* @param id - the user id.
	* @return true if success.
	*/
	private static boolean userLogout(String id)
	{
		if (id == null)
			return false;
		userUpdateLogin(id, false);
		return true;
	}

	/**
	 * Create a new test in the database.
	 * @param test - the test to create.
	 * @return return true is successfully.
	 */
	public static boolean createTest(Test test)
	{
		if (test.getCode() == null)
			return false;
		try
		{
			String state = "insert into " + TEST_DATABASE_NAME + " values (?,?,?,?,?,?,?);";
			String questions_id = "";
			String questions_grades = "";
			String zero_prefix = "0";
			PreparedStatement add_test = conn.prepareStatement(state);
			if (test.getQuestions() != null)
				for (int i = 0; i<test.getQuestions().size(); i++)
					questions_id = questions_id.concat(test.getQuestions().get(i).getCode());
			if (test.getQuestionGrade() != null)
				for (int i = 0; i<test.getQuestionGrade().size(); i++)
					if (test.getQuestionGrade().get(i).length()<3)
					{
						zero_prefix = zero_prefix.concat(test.getQuestionGrade().get(i));
						questions_grades = questions_grades.concat(zero_prefix);
						zero_prefix = "0";
					}
					else
						questions_grades = questions_grades.concat(test.getQuestionGrade().get(i));
			add_test.setString(1, test.getCode());
			add_test.setString(2, questions_id);
			add_test.setString(3, questions_grades);
			add_test.setString(4, test.getCommentsForTeacher());
			add_test.setString(5, test.getCommentsForStudent());
			add_test.setString(6, test.getOwner());
			add_test.setString(7, test.getTime());
			add_test.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * Update a test question using the given already updated test by the given test id.
	 * @param test - the test with the id an questions.
	 * @return return true is successfully.
	 */
	public static boolean setQuestionsForTest(Test test)
	{
		String questions_id = "";
		String questions_grades = "";
		String zero_prefix = "0";
		if (test.getCode() == null)
			return false;
		if (test.getQuestions() != null)
			for (int i = 0; i<test.getQuestions().size(); i++)
				questions_id = questions_id.concat(test.getQuestions().get(i).getCode());
		if (test.getQuestionGrade() != null)
			for (int i = 0; i<test.getQuestionGrade().size(); i++)
				if (test.getQuestionGrade().get(i).length()<3)
				{
					zero_prefix = zero_prefix.concat(test.getQuestionGrade().get(i));
					questions_grades = questions_grades.concat(zero_prefix);
					zero_prefix = "0";
				}
				else
					questions_grades = questions_grades.concat(test.getQuestionGrade().get(i));
		try {
			String state = "update " + TEST_DATABASE_NAME +
				" set " +
				" Qs_id = \'" + questions_id + "\'" +
				" , " +
				" Qs_grades = \'" + questions_grades + "\'" +
				" where code = " + "\'" + test.getCode() + "\'" + ";";
			PreparedStatement add_comment = conn.prepareStatement(state);
			add_comment.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * Update a test in the database to the new test using the new test id.
	 * The function deletes the old one and creats a new one to update.
	 * @param test - the test to update.
	 * @return return true is successfully.
	 */
	public static boolean updateTest(Test test)
	{
		if (test.getCode() == null)
			return false;
		deleteTest(test.getCode());
		createTest(test);
		return true;
	}
	
	/**
	 * Update the test comments for the teacher using the given test.
	 * @param test - the updated test.
	 * @return return true is successfully.
	 */
	public static boolean addCommentsForTeacher(Test test)
	{
		if (test.getCode() == null)
			return false;
		try {
			String state = "update " + TEST_DATABASE_NAME + " set CommentsForTeacher = \'" + test.getCommentsForTeacher()
				+ "\' where code = \'" + test.getCode() + "\'";
			PreparedStatement add_comment = conn.prepareStatement(state);
			add_comment.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * Update the test comments for the students using the given test.
	 * @param test - the updated test.
	 * @return return true is successfully.
	 */
	public static boolean addCommentsForStudent(Test test)
	{
		if (test.getCode() == null)
			return false;
		try {
			String state = "update " + TEST_DATABASE_NAME + " set CommentsForStudent = \'" + test.getCommentsForStudent()
				+ "\' where code = \'" + test.getCode() + "\'";
			PreparedStatement add_comment = conn.prepareStatement(state);
			add_comment.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * Get all tests in the database.
	 * @return array list of the tests.
	 */
	public static ArrayList<Test> getAllTests()
	{
		ArrayList<Test> allTests = new ArrayList<Test>();
		Test t = new Test();
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + TEST_DATABASE_NAME + ";");
			while (rs.next())
			{
				ArrayList<Question> qs = fromStringToQuestionArray((rs.getString(2)));
				ArrayList<String> grades = fromStringToStringArray(rs.getString(3));
				t = new Test(rs.getString(1), qs, grades, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				allTests.add(t);
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return allTests;
	}
	
	/**
	 * Get a test by its code.
	 * @param code - the test code.
	 * @return the test.
	 */
	public static Test getTestById(String code)
	{
		Test test = new Test();
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + TEST_DATABASE_NAME + " where code = \'" + code + "\';");
			if (rs.next())
			{
				ArrayList<Question> qs = fromStringToQuestionArray(rs.getString(2));
				ArrayList<String> grades = fromStringToStringArray(rs.getString(3));
				test = new Test(rs.getString(1), qs, grades, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return test;
	}
	
	/**
	 * Build a question array list from a string of codes.
	 * The question is loaded form the database.
	 * @param qs_id - the string of codes.
	 * @return the qeustion array list.
	 */
	public static ArrayList<Question> fromStringToQuestionArray(String qs_id)
	{
		ArrayList<Question> questions = new ArrayList<Question>();
		for (int i = 0; i <= qs_id.length() - 5; i += 5)
			questions.add(getQuestionByCode((qs_id.substring(i, i + 5))));
		return questions;
	}

	/**
	 * Build an array list of string from the given string.
	 * @param g the string.
	 * @return the array list.
	 */
	public static ArrayList<String> fromStringToStringArray(String g)
	{
		ArrayList<String> grades = new ArrayList<String>();
		for (int i = 0; i <= g.length() - 3; i += 3)
			grades.add(g.substring(i, i + 3));
		return grades;
	}

	/**
	 * Create a new student test in the database with the given test.
 	 * @param std_test - the student test to create in the database.
	 * @return return true is successfully.
	 */
	public static boolean createStudentTest(studentTest std_test)
	{
		if (std_test.getTest().getCode() == null || std_test.getStudent() == null)
			return false;
		try
		{
			studentTest [] testToSave = getAllTestsByStudentId(std_test.getStudent()).getTests();
			String saveTestCode = "";
			String saveGrades = "";
			for(int i=0; i<testToSave.length ; i++)
			{
				if(testToSave[i].getTest().getCode() != std_test.getTest().getCode())
				{
					saveTestCode = saveTestCode.concat(testToSave[i].getTest().getCode());
					saveGrades = saveTestCode.concat(String.format("%3d", testToSave[i].getGrade()).replace(' ', '0'));
				}
			}
			deleteStudentTest(std_test.getStudent() + (std_test.getTest().getCode()), std_test.getStudent(),saveTestCode,saveGrades);
			String state = "insert into " + STUDENT_TEST_DATABASE_NAME + " values (?,?,?,?,?,?,?,?);";
			String answers = "";
			PreparedStatement add_student_test = conn.prepareStatement(state);
			if (std_test.getAnswers() != null)
				for (int i = 0; i<std_test.getAnswers().size(); i++)
					answers = answers.concat(std_test.getAnswers().get(i));
			add_student_test.setString(1, std_test.getStudent() + std_test.getTest().getCode());
			add_student_test.setString(2, std_test.getStudent());
			add_student_test.setString(3, std_test.getTest().getCode());
			add_student_test.setString(4, answers);
			add_student_test.setString(5, Integer.toString(std_test.getGrade()));
			add_student_test.setBoolean(6, std_test.isCheat());
			add_student_test.setBoolean(7, std_test.isCheck());
			add_student_test.setString(8, std_test.getReason());
			add_student_test.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return updateStudentTable(std_test.getStudent(), std_test.getTest().getCode(), std_test.fromIntToString(std_test.getGrade()));
	}
	
	/**
	 * Update the student table with the new test.
	 * @param id - the student id.
	 * @param code - the test code.
	 * @param grade - the final grade.
	 * @return return true is successfully.
	 */
	public static boolean updateStudentTable(String id, String code, String grade)
	{
		Statement stmt;
		String tests = "";
		String grades = "";
		String state;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + STUDENT_DATABASE_NAME + " where id = \'" + id + "\';");
			if (rs.next())
			{
				if (rs.getString(2) != null)
					tests = rs.getString(2);
				tests = tests.concat(code);
				if (rs.getString(3) != null)
					grades = rs.getString(3);
				grades = grades.concat(grade);
			}
			rs.close();
			state = " update " + STUDENT_DATABASE_NAME +
				" set " +
				" tests_id = \'" + tests + "\' " +
				" , " +
				" tests_grades = \'" + grades + "\' " +
				" where id = \'" + id + "\' " +
				";";
			PreparedStatement add_comment = conn.prepareStatement(state);
			add_comment.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }

		return true;
	}
	
	/**
	 * Create a student in the database (not a user).
	 * @param std - the student to create.
	 * @return return true is successfully.
	 */
	public static boolean createStudent(Student std)
	{
		if (std.getId() == null)
			return false;
		try
		{
			String state = "insert into " + STUDENT_DATABASE_NAME + " values (?,?,?);";
			PreparedStatement add_student = conn.prepareStatement(state);
			add_student.setString(1, std.getId());
			add_student.setString(2, std.fromStudentTestsArrayToString(std.getTests()));
			add_student.setString(3, std.fromIntArrayToString(std.getGrades()));
			add_student.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * Delete a student from the database ( not a user).
	 * @param std_id - the student .
	 * @return return true is successfully.
	 */
	public static boolean deleteStudent(String std_id)
	{
		if (std_id == null)
			return false;
		try
		{
			String state = "delete from " + STUDENT_DATABASE_NAME + " where id = \'" + std_id + "\';";
			PreparedStatement del_student = conn.prepareStatement(state);
			del_student.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}
	
	/**
	 * Build a studentTest array from the given student id and test codes.
	 * The studentTest is loaded from the database.
	 * @param stud_id the given student id.
	 * @param test_code the given codes.
	 * @return return true is successfully.
	 */
	public static studentTest[] fromStringToStudentTestArray(String stud_id, String test_code)
	{
		studentTest tests[] = new studentTest[test_code.length() / 6];
		for (int i = 0, j = 0; i <= test_code.length() - 6; i += 6)
			tests[j++] = getStudentTestByIdAndCode(stud_id, test_code.substring(i, i + 6));
		return tests;
	}
	
	/**
	 * Get a studentTest by its id and test code.
	 * @param id the student id.
	 * @param code the test code.
	 * @return the student test. 
	 */
	public static studentTest getStudentTestByIdAndCode(String id, String code)
	{
		studentTest std_test = new studentTest();
		Statement state;
		try
		{
			state = conn.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM " + STUDENT_TEST_DATABASE_NAME +
				" where id = \'" + id + "\'" +
				" and test_code = \'" + code + "\'" + ";");
			if (rs.next())
			{
				std_test.setStudent(rs.getString(2));
				std_test.setTest(getTestById(code));
				char g[];
				g = rs.getString(4).toCharArray();
				ArrayList<String> answers = new ArrayList<String>();
				for (int i = 0; i<g.length; i++)
					answers.add(g[i] + "");
				std_test.setAnswers(answers);
				std_test.setGrade(Integer.parseInt(rs.getString(5)));
				std_test.setCheat(rs.getBoolean(6));
				std_test.setCheck(rs.getBoolean(7));
				std_test.setReason(rs.getString(8));
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return std_test;
	}
	
	/**
	 * Build a int array from string to load from the database.
	 * @param str - the string.
	 * @return a int array.s
	 */
	public static int[] fromStringToIntArray(String str)
	{
		int grades[] = new int[str.length() / 3];
		for (int i = 0, j = 0; i <= str.length() - 3; i += 3)
			grades[j++] = Integer.parseInt(str.substring(i, i + 3));
		return grades;
	}
	
	/**
	 * Get all tests by the student id.
	 * @param std_id the student id.
	 * @return the student with all the tests in it.
	 */
	public static Student getAllTestsByStudentId(String std_id)
	{
		Student s = new Student();
		s.setId(std_id);
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + STUDENT_DATABASE_NAME + " where id = \'" + std_id + "\';");
			if (rs.next())
			{
				s.setTests(fromStringToStudentTestArray(s.getId(), rs.getString(2)));
				s.setGrades(fromStringToIntArray(rs.getString(3)));
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	
	/**
	 * Get all the tests by teacher id.
	 * @param teacher_id - the teacher id.
	 * @return an array of all the studentTest.
	 */
	public static studentTest[] getAllTestsByTeacherId(String teacher_id)
	{
		ArrayList<studentTest> st_arr = new ArrayList<studentTest>();
		studentTest st[];
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + STUDENT_TEST_DATABASE_NAME +
				" left join " + TEST_DATABASE_NAME + " on test_code = code where TestOwner = \'" + teacher_id + "\';");
			while (rs.next())
				st_arr.add(getStudentTestByIdAndCode(rs.getString(2), rs.getString(3)));
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		st = new studentTest[st_arr.size()];
		for (int i = 0; i<st.length; i++)
			st[i] = st_arr.get(i);
		return st;
	}
	
	/**
	 * Get all the tests by course id.
	 * @param course_id The course id.
	 * @return an array of all the studentTest.
	 */
	public static studentTest[] getAllTestsByCourseId(String course_id)
	{
		ArrayList<studentTest> st_arr = new ArrayList<studentTest>();
		studentTest st[];
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + STUDENT_TEST_DATABASE_NAME +
				" where test_code like \'" + course_id + "%\';");
			while (rs.next())
				st_arr.add(getStudentTestByIdAndCode(rs.getString(2), rs.getString(3)));
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		st = new studentTest[st_arr.size()];
		for (int i = 0; i<st.length; i++)
			st[i] = st_arr.get(i);
		return st;
	}
	
	/**
	 * Delete a test using the given code.
	 * @param code the test code.
	 * @return return true is successfully.
	 */
	public static boolean deleteTest(String code)
	{
		if (code == null)
			return false;
		try
		{
			String state = "delete from " + TEST_DATABASE_NAME + " where code = \'" + code + "\';";
			PreparedStatement del_test = conn.prepareStatement(state);
			del_test.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;

	}

	/**
	 * Delete a student test using the test code and student id.
	 * @param code - the test code
	 * @param id - the student id.
	 * @return return - true is successfully.
	 */
	private static boolean deleteStudentTest(String code, String id, String testCodes,String TestGrades)
	{
		if (code == null)
			return false;
		try
		{
			String state = "delete from " + STUDENT_TEST_DATABASE_NAME + " where id_code = \'" + code + "\';";
			PreparedStatement del_test = conn.prepareStatement(state);
			del_test.executeUpdate();
			String stateS = "delete from " + STUDENT_DATABASE_NAME + " where id = \'" + id + "\';";
			PreparedStatement del_testS = conn.prepareStatement(stateS);
			del_testS.executeUpdate();
			String stateA = "insert into " + STUDENT_DATABASE_NAME + " value(?,?,?)";
			PreparedStatement insert = conn.prepareStatement(stateA);
			insert.setString(1, id);
			insert.setString(2, testCodes);
			insert.setString(3, TestGrades);
			insert.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return true;
	}

	/**
	* Update a given ExecutedTest in the array list.
	* @param updatedTest the ExecutedTest to update.
	* @return the new ExecutedTest.
	*/
	public static ArrayList<ExecutedTest> UpdateExecutedTests(ExecutedTest updatedTest)
	{
		for(int i=0; i<executedTests.size();i++)
		{
			if(executedTests.get(i).getTest().getCode().equals(updatedTest.getTest().getCode()))
			{
				executedTests.get(i).setSignUpList(updatedTest.getSignUpList());
				executedTests.get(i).setFInishedNum(updatedTest.getFInishedNum());
				executedTests.get(i).setFFInishedNum(updatedTest.getFFInishedNum());
				executedTests.get(i).setSign(updatedTest.getSign());
				executedTests.get(i).setrSign(updatedTest.getrSign());
				executedTests.get(i).setStudentNumber(updatedTest.getSignUpList().size());
				executedTests.get(i).setR(updatedTest.returnR());
				executedTests.get(i).setT(updatedTest.returnT());
				executedTests.get(i).setGList(updatedTest.getGradeList());
				executedTests.get(i).setCurrentTime(updatedTest.getCurrentTime());

			}
		}
		return(new ArrayList<>(executedTests));
	}

	/**
	* Return a copy of the executedTests.
	* @return a copy of the executedTests.
	*/
	public static ArrayList<ExecutedTest> getExecutedTests() {
		return new ArrayList<>(executedTests);
	}

	/**
	* Check if a test was locked.
	* @param test - the test to check.
	* @return the sign of the test (2 for locked).
	*/
	public static int executedTestsCheckLock(ExecutedTest test) {
		for (int i = 0; i<executedTests.size(); i++)
			if (executedTests.get(i).getTest().getCode().equals(test.getTest().getCode()))
				return executedTests.get(i).getSign();
		return -1;
	}

	/**
	* This function uploads and create a file from the client.
	* @param file - the file to upload.
	*/
	public static void uploadFile(MyFile file) {
		try {

			File newFile = new File(file.getFileName());
			FileOutputStream fos = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(file.getMybytearray());
			bos.close();
		}
		catch (Exception e) {
			System.out.println("Error send (Files)msg) to Server");
		}
	}

	/**
	 * Create and send a manual test using the given executed test.
	 * @param test - the executed test.
	 * @return - the test file.
	 */
	public static MyFile downloadFile(ExecutedTest test) 
	{
		createMainualTest(test);
		MyFile file = new MyFile(TEST_FILE_LOCATION + test.getTest().getCode() + ".txt");
		try {

			File newFile = new File(TEST_FILE_LOCATION + test.getTest().getCode() + ".txt");

			byte[] mybytearray = new byte[(int)newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);

			file.initArray(mybytearray.length);
			file.setSize(mybytearray.length);

			bis.read(file.getMybytearray(), 0, mybytearray.length);
			bis.close();
		}
		catch (Exception e) {
			System.out.println("Error send (Files)msg) to Server");
		}
		return file;
	}
	
	/**
	 * Create a test file for manual testing user ExecutedTest.
	 * @param EcxTest - the executed test.
	 */
	private static void createMainualTest(ExecutedTest EcxTest)
	{
		Test test = EcxTest.getTest();
		try {
			BufferedWriter newFile = new BufferedWriter(new FileWriter(TEST_FILE_LOCATION + test.getCode() + ".txt"));
			if(test.getCommentsForStudent() != null) 
			{
				newFile.write("Teacher comments: "+test.getCommentsForStudent());
				newFile.newLine();
			}
			newFile.write("The test time is: "+test.getTime());
			newFile.newLine();
			for(int i=0; i<test.getQuestions().size(); i++)
			{
				test.getQuestions().get(i).writeQuestionToFile(newFile);
			}
			newFile.close();
		} catch (Exception e) {e.printStackTrace();	}

	}
	
	/**
	* This function get all the courses id from the server.
	* @return ArrayList<String> of the courses id.
	*/
	public static ArrayList<String> getCoursesId()
	{
		ArrayList<String> coursesID = new ArrayList<String>();
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + COURSES_DATABASE_NAME + ";");
			while (rs.next())
			{
				coursesID.add(rs.getString(1));
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return coursesID;
	}
	/**
	* This function get all the courses id from the server.
	* @return ArrayList<String> of the courses id.
	*/
	public static ArrayList<String> getSubjectsId()
	{
		ArrayList<String> coursesID = new ArrayList<String>();
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + Subjects_DATABASE_NAME + ";");
			while (rs.next())
			{
				coursesID.add(rs.getString(1));
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return coursesID;
	}

	/**
	* This function return all the names of a user by type
	* @param type - the type
	* @return Array list of all the names.
	*/
	public static ArrayList<String> getAllUsersNamesByType(String userType)
	{
		ArrayList<String> studnetName = new ArrayList<String>();
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + USER_DATABASE_NAME + ";");
			while (rs.next())
			{
				if (rs.getString(3).equals(userType))
					studnetName.add(rs.getString(5));
			}
			rs.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
		return studnetName;
	}

}

