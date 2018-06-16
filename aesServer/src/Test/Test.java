package Test;

import java.io.Serializable;

import question.*;
public class Test implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8046372034304566997L;
	
	private static final int CODE_LENGTH = 6;
	private Question questions[];
	private String questionGrade[];
	private String commentsForTeacher;
	private String commentsForStudent;
	private String code;
	private String owner;
	private String time;
	public Test(String code, Question questions[], String questionGrade[], String commentsForTeacher, String commentsForStudent, String owner, String time)
	{
		super();
		this.code = code;
		this.questions = questions;
		this.commentsForTeacher = commentsForTeacher;
		this.commentsForStudent = commentsForStudent;
		this.questionGrade = questionGrade;
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
	public Question[] getQuestions() {
		return questions;
	}
	public void setQuestions(Question[] questions) {
		this.questions = questions;
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
	public String[] getQuestionGrade() {
		return questionGrade;
	}
	public void setQuestionGrade(String[] questionGrade) {
		this.questionGrade = questionGrade;
	}
	@Override
	public String toString() {
		return "[code = " + code + ", Owner = " + owner + ", Time = " + time + "(min)" + ", comments for teacher= " + commentsForTeacher + 
				", comments for student= " + commentsForStudent +
				"\nQuestions = [" + FromQuestionArrayToString(questions, questionGrade) +
				 "]\n";
	}
	public static String FromQuestionArrayToString(Question q[], String grades[])
	{
		String str="\n  ";
		for(int i=0; i<q.length; i++)
			str = str.concat(q[i].toString() + " | Grade = " +grades[i] + "\n  ");
		return str;
	}
}
