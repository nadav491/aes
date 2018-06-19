package report;
import client.Client;

public class StudentReport extends Report {
	String studentId;
	
	private StudentReport(float avg, float median) {
		super(avg, median);
	}


}
