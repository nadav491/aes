package report;
import client.Client;

public class StudentReport extends Report {
	String studentId;
	
	private StudentReport(float avg, float median) {
		super(avg, median);
	}
	
	public StudentReport(String studentId)
	{
		this.studentId = studentId;
		this.setAvg(this.findAvarege());
	}
	
	private float findAvarege(Client Client) 
	{
		
	}

}
