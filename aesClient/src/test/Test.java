package test;

import java.io.Serializable;
import java.util.ArrayList;

import question.*;
public class Test implements Serializable {
	private static final long serialVersionUID = -8046372034304566997L;
	
	private static final int CODE_LENGTH = 6;
	private ArrayList<Question> questions;
	private ArrayList<String> questionGrade;
	private String commentsForTeacher;
	private String commentsForStudent;
	private String code;
	private String owner;
	private String time;

	public Test(String code, ArrayList<Question> questions, ArrayList<String> questionGrade, String commentsForTeacher,
			String commentsForStudent, String owner, String time) {
		super();
		this.questions = questions;
		this.questionGrade = questionGrade;
		this.commentsForTeacher = commentsForTeacher;
		this.commentsForStudent = commentsForStudent;
		this.code = code;
		this.owner = owner;
		this.time = time;
	}

	public Test(Test test)
	{
		super();
		this.questions = test.questions;
		this.commentsForTeacher = test.commentsForTeacher;
		this.commentsForStudent = test.commentsForStudent;
		this.code = test.code;
		this.questionGrade = test.questionGrade;
		this.owner = test.owner;
		this.time = test.time;
	}
	public Test()
	{
		super();
	}

	public String getCommentsForTeacher() {
		return commentsForTeacher;
	}
	public void setCommentsForTeacher(String commentsFroreacher) {
		this.commentsForTeacher = commentsFroreacher;
	}
	public String getCommentsForStudent() {
		return commentsForStudent;
	}
	public void setCommentsForStudent(String commentsForStudent) {
		this.commentsForStudent = commentsForStudent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}


	@Override
	public String toString() {
		return "[code = " + code + ", Owner = " + owner + ", Time = " + time + "(min)" + ", comments for teacher= " + commentsForTeacher + 
				", comments for student= " + commentsForStudent +
				"\nQuestions = [" + FromQuestionArrayToString(questions, questionGrade) +
				 "]\n";
	}
	public static String FromQuestionArrayToString(ArrayList<Question> questions2, ArrayList<String> questionGrade2)
	{
		String str="\n  ";
		for(int i=0; i<questions2.size(); i++)
			str = str.concat(questions2.get(i).toString() + " | Grade = " +questionGrade2.get(i) + "\n  ");
		return str;
	}

	public ArrayList<Question> getQuestions() {
		if(questions == null)
			this.questions = new ArrayList<Question>();
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	public ArrayList<String> getQuestionGrade() {
		if(questionGrade == null)
			this.questionGrade = new ArrayList<String>();
		return questionGrade;
	}
	public void setQuestionGrade(ArrayList<String> questionGrade) {
		this.questionGrade = questionGrade;
	}
}
