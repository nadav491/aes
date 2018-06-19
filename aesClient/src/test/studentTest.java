package test;

import java.io.Serializable;
import java.util.ArrayList;

public class studentTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5614617560589691158L;
	int grade;
	ArrayList<String> answers;
	String time;
	Test test;
	String teacher;
	String student;
	String reason;
	ArrayList<String> remarks;
	public studentTest(String student, Test test, int grade, ArrayList<String> answers, String time, String teacher)
	{
		super();
		this.test = test;
		this.grade = grade;
		this.answers = answers;
		this.time = time;
		this.teacher = teacher;
		this.student = student;
		reason=null;
		remarks=new ArrayList<String>();
	}
	public studentTest(studentTest st)
	{
		super();
		this.test = st.test;
		this.grade = st.grade;
		this.answers = st.answers;
		this.time = st.time;
		this.teacher = st.teacher;
		this.student = st.student;
		reason=null;
		remarks=new ArrayList<String>();
	}
	public ArrayList<String> getRemark() {
		return remarks;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String student) {
		this.reason= student;
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
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
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

	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String fromIntToString(int grade)
	{
		String str="";
		String temp=Integer.toString(grade);
		if(temp.length() == 3)
			return temp;
		else if(temp.length() == 2)
		{
			str = str.concat("0");
			str = str.concat(temp);
		}
		else if (temp.length() == 1)
		{
			str = str.concat("00");
			str = str.concat(temp);
		}
		return str;
	}
	@Override
	public String toString()
	{
		return "\n  details | " + test.toString() + ", student's answers = " + answersToString(answers);
	}
	public String answersToString(ArrayList<String> answers)
	{
		String str = "";
		for(int i=0; i<answers.size(); i++)
		{
			str = str.concat("q # " + (i+1) + ": a = " + answers.get(i));
			str = str.concat(", ");
		}
		return str;
	}
}
