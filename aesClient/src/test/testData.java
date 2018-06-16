package test;

import java.io.Serializable;
import java.sql.Time;

public class testData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711204674210836444L;
	
	String date;
	Time time;
	int studentNumber;
	int startedNumber;
	int finishedNumber;
	float avg;
	float midean;
	float distributionOfScore[];
	
	public testData(String date, Time time,int studentNumber, int startedNumber, int finishedNumber,float avg, float midean, float distributionOfScore[] )
	{
		super();
		this.date = date;
		this.time = time;
		this.studentNumber = studentNumber;
		this.startedNumber = startedNumber;
		this.finishedNumber = finishedNumber;
		this.avg = avg;
		this.midean = midean;
		this.distributionOfScore = distributionOfScore;
	}
	public testData()
	{
		super();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	public int getStartedNumber() {
		return startedNumber;
	}
	public void setStartedNumber(int startedNumber) {
		this.startedNumber = startedNumber;
	}
	public int getFinishedNumber() {
		return finishedNumber;
	}
	public void setFinishedNumber(int finishedNumber) {
		this.finishedNumber = finishedNumber;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public float getMidean() {
		return midean;
	}
	public void setMidean(float midean) {
		this.midean = midean;
	}
	public float[] getDistributionOfScore() {
		return distributionOfScore;
	}
	public void setDistributionOfScore(float[] distributionOfScore) {
		this.distributionOfScore = distributionOfScore;
	}
}
