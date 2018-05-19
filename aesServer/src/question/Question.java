package question;

import java.io.Serializable;

public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6372195476148742068L;

	private static final int CODE_LENGTH = 5 ;
	
	private String code;
	private String owner;
	private String instruction;
	private String body;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private Course courseList;
	private int correct;
	

	
	public Question(String code, String owner, String instruction, String body, String answer1, String answer2,
			String answer3, String answer4, int correct , Course courseList) {
		super();
		this.code = code;
		this.owner = owner;
		this.instruction = instruction;
		this.body = body;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.courseList = courseList;
		this.correct = correct;
	}

	public Question(String code, String owner, String body, String answer1, String answer2,
			String answer3, String answer4,  int correct) {
		super();
		this.code = code;
		this.owner = owner;
		this.instruction = "";
		this.body = body;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.courseList = null;
		this.correct = correct;
	}
	
	public Question() {
		super();
	}

	public Question(Question question) {
		super();
		this.code = question.code;
		this.owner = question.owner;
		this.instruction = question.instruction;
		this.body = question.body;
		this.answer1 = question.answer1;
		this.answer2 = question.answer2;
		this.answer3 = question.answer3;
		this.answer4 = question.answer4;
		this.courseList = question.courseList;
		this.correct = question.correct;
	}
	public boolean checkQuestion()
	{
		if(this.code.length() != CODE_LENGTH)
		{
			return false;
		}
		if(this.owner == null)
		{
			return false;
		}
		if(this.body == null)
		{
			return false;
		}
		if(this.answer1 == null)
		{
			return false;
		}
		if(this.answer2 == null)
		{
			return false;
		}
		if(this.answer3 == null)
		{
			return false;
		}
		if(this.answer4 == null)
		{
			return false;
		}
		if(this.correct > 4 || this.correct < 1 )
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

	public String showQuestion() {
		return body + "\n" + answer1+"\n" + answer2+"\n" + answer3+"\n"+ answer4+"\n";
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
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

	public Course getCourseList() {
		return courseList;
	}

	public void setCourseList(Course courseList) {
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

	public String getOwner() {
		return owner;
	}
	


}