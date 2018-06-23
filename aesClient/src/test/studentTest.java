package test;
import java.io.Serializable;
import java.util.ArrayList;

/**
* This class is used to represent a finished test done by a student.
*/
public class studentTest implements Serializable {
	private static final long serialVersionUID = 5614617560589691158L;
	private int grade;
	private ArrayList<String> answers;
	private String time;
	private Test test;
	private String teacher;
	private String student;
	private String reason;
	private ArrayList<String> remarks;
	private boolean check;
	private boolean cheat;

	public studentTest(String student, Test test, int grade, ArrayList<String> answers, String time, String teacher)
	{
		super();
		this.grade = grade;
		this.answers = answers;
		this.time = time;
		this.teacher = teacher;
		this.student = student;
		this.reason = " ";
		this.remarks = new ArrayList<String>();
		this.test = test;
		this.check = false;
		this.cheat = false;
	}
	public studentTest(studentTest st)
	{
		super();
		this.grade = st.grade;
		this.answers = st.answers;
		this.time = st.time;
		this.teacher = st.teacher;
		this.student = st.student;
		this.reason = " ";
		this.remarks = new ArrayList<String>();
		this.check = false;
		this.cheat = false;
	}

	public studentTest() {
		super();
		this.remarks = new ArrayList<String>();
	}
	public void setremarks()
	{
		this.remarks = new ArrayList<String>();
	}
	public ArrayList<String> getRemark() {
		return remarks;
	}

	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public boolean isCheat() {
		return cheat;
	}
	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String student) {
		this.reason = student;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
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

	/**
	* Build a string from the given grades to store in the database.
	* @param grade - the grades to store.
	* @return the String.
	*/
	public String fromIntToString(int grade)
	{
		String str = "";
		String temp = Integer.toString(grade);
		if (temp.length() == 3)
			return temp;
		else if (temp.length() == 2)
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

	/**
	* Build a string from the given answers to store in the database.
	* @param answers - the answers to store.
	* @return the String.
	*/
	public String answersToString(ArrayList<String> answers)
	{
		String str = "";
		for (int i = 0; i<answers.size(); i++)
		{
			str = str.concat("q # " + (i + 1) + ": a = " + answers.get(i));
			str = str.concat(", ");
		}
		return str;
	}
}
