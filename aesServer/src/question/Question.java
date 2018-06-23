package question;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
* This class represent the question in the database.
*/
public class Question implements Serializable {
	private static final long serialVersionUID = 6372195476148742068L;
	private static final int CODE_LENGTH = 5;
	private String code;
	private String owner;
	private String Sinstruction;
	private String Tinstruction;
	private String body;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String courseList;
	private int correct;


	public Question(String code, String owner, String body, String answer1, String answer2,
		String answer3, String answer4, int correct, String courseList, String Sinstruction, String Tinstruction) {
		super();
		this.code = code;
		this.owner = owner;
		this.Sinstruction = Sinstruction;
		this.Tinstruction = Tinstruction;
		this.body = body;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.courseList = courseList;
		this.correct = correct;
	}

	public Question() {
		super();
	}

	public Question(Question question) {
		super();
		this.code = question.code;
		this.owner = question.owner;
		this.Sinstruction = question.Sinstruction;
		this.Tinstruction = question.Tinstruction;
		this.body = question.body;
		this.answer1 = question.answer1;
		this.answer2 = question.answer2;
		this.answer3 = question.answer3;
		this.answer4 = question.answer4;
		this.courseList = question.courseList;
		this.correct = question.correct;
	}

	/**
	 * Check if the question is full and can be saved to the database.
	 * @return True if its full.
	 */
	public boolean checkQuestion()
	{
		if (this.code.length() != CODE_LENGTH)
		{
			return false;
		}
		if (this.owner == null)
		{
			return false;
		}
		if (this.body == null)
		{
			return false;
		}
		if (this.answer1 == null)
		{
			return false;
		}
		if (this.answer2 == null)
		{
			return false;
		}
		if (this.answer3 == null)
		{
			return false;
		}
		if (this.answer4 == null)
		{
			return false;
		}
		if (this.correct > 4 || this.correct < 1)
		{
			return false;
		}
		return true;

	}
	
	@Override
		public String toString() {
		return "[id=" + code + ", teacherName=" + owner + ", questionText=" + body + ", answer1="
			+ answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + ", correct="
			+ correct + "]";
	}

	/**
	 * Second way to print the question.
	 * @return String of the question.
	 */
	public String showQuestion() {
		return body + "\n" + answer1 + "\n" + answer2 + "\n" + answer3 + "\n" + answer4 + "\n";
	}
	
	/**
	 * Write a question to the given file.
	 * @param bw - the file to  write into.
	 */
	public void writeQuestionToFile(BufferedWriter bw)
	{
		try {
			bw.write(body);
			bw.newLine();
			bw.write(answer1);
			bw.newLine();
			bw.write(answer2);
			bw.newLine();
			bw.write(answer3);
			bw.newLine();
			bw.write(answer4);
			bw.newLine();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public String getSInstruction() {
		if (Sinstruction == null)
			Sinstruction = " ";
		return Sinstruction;
	}
	public String getTInstruction() {
		return Tinstruction;
	}
	public void setSInstruction(String instruction) {
		this.Sinstruction = instruction;
	}
	public void setTInstruction(String instruction) {
		this.Tinstruction = instruction;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getCourseList() {
		return courseList;
	}

	public void setCourseList(String courseList) {
		this.courseList = courseList;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String str)
	{
		this.code = str;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String str)
	{
		this.owner = str;
	}
}