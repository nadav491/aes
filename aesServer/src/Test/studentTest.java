package Test;

import java.io.Serializable;

public class studentTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5614617560589691158L;
	int grade;
	int answers[];
	String time;
	Test test;
	String testType;
	String teacher;
	String student;
	
	public studentTest(String student, Test test, int grade, int answers[], String time, String testType, String teacher)
	{
		super();
		this.grade = grade;
		this.answers = answers;
		this.time = time;
		this.testType = testType;
		this.teacher = teacher;
		this.student = student;
	}
	public studentTest(studentTest st)
	{
		super();
		this.grade = st.grade;
		this.answers = st.answers;
		this.time = st.time;
		this.testType = st.testType;
		this.teacher = st.teacher;
		this.student = st.student;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public studentTest()
	{
		super();
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int[] getAnswers() {
		return answers;
	}
	public void setAnswers(int[] answers) {
		this.answers = answers;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString()
	{
		return "[ student = " +student + ", test =\n  " + test.toString();
	}
}
