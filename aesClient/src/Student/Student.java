package Student;
import java.io.Serializable;
import test.studentTest;
/**
 * This class represent the students with all its test and grades.
 */
public class Student  implements Serializable{
	private static final long serialVersionUID = 4499287752138916867L;
	private String id;
	private studentTest tests[];
	private int grades[];
	
	public Student()
	{
		super();
	}
	
	public Student(Student std)
	{
		super();
		this.id = std.id;
		this.tests = std.tests;
		this.grades = std.grades;
	}
	
	public Student(String id, studentTest tests[], int grades[])
	{
		super();
		this.id = id;
		this.tests = tests;
		this.grades = grades;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public studentTest[] getTests() {
		return tests;
	}
	
	public void setTests(studentTest[] tests) {
		this.tests = tests;
	}
	
	public int[] getGrades() {
		return grades;
	}
	
	public void setGrades(int[] grades) {
		this.grades = grades;
	}
	
	/**
	 * This function get all the grades and creates a string to save into the database.
	 * @param grades - the grades array.
	 * @return the new string.
	 */
	public String fromIntArrayToString(int grades[])
	{
		String str = "";
		String zer_prefix = "0";
		String double_zer_prefix="00";
		for(int i =0; i<grades.length; i++)
			if(Integer.toString(grades[i]).length()  == 3)
				str = str.concat(Integer.toString(grades[i]));
			else if(Integer.toString(grades[i]).length()  == 2)
			{
				zer_prefix = zer_prefix.concat(Integer.toString(grades[i]));
				str = str.concat(zer_prefix);
				zer_prefix = "0";
			}
			else if (Integer.toString(grades[i]).length()  == 1)
			{
				double_zer_prefix = double_zer_prefix.concat(Integer.toString(grades[i]));
				str = str.concat(double_zer_prefix);
				double_zer_prefix = "00";
			}
		return str;
	}
	
	/**
	 * This function get all the tests and creates a string to save into the database.
	 * @param tests - the tests array.
	 * @return the new string.
	 */
	public String fromStudentTestsArrayToString(studentTest tests[])
	{
		String str ="";
		for(int i=0; i< tests.length; i++)
			str = str.concat(tests[i].getTest().getCode());
		return str;
	}
}
