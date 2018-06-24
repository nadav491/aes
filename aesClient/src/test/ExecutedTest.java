package test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represent an executed test which is a test that a teacher started and the student can perform.
 * The executed test is a Intermediary between the finish test (named student test) and the test itself.
 */
public class ExecutedTest implements Serializable {
	private static final long serialVersionUID = 3834052242070085516L;
	private Test Execute;
	private String Exe_code;
	private Date date;
	private LocalDateTime StartTime;
	private String CurrentLength;
	private ArrayList<String> SignUplist;
	private final ArrayList<String> FUplist;
	private int StudentNumStart;
	private int StudentNumFinished;
	private int StudentNumForceFinished;
	private String executer;
	private ArrayList<String> gradeList;
	private int sign;
	private int run_sign;
	public Map<Integer , ArrayList<String>> gradelog;
	private String reason;
	private int timeC;
	public ExecutedTest(Test T1,Date currentDate,String Length)
	{
		Execute=new Test();
		Execute=T1;
		Exe_code=null;
		date=currentDate;
		StartTime=LocalDateTime.now();
		CurrentLength=Length;
		SignUplist=new ArrayList<String>();
		StudentNumStart=0;
		StudentNumFinished=0;
		StudentNumForceFinished=0;
		executer=null;
		gradeList=new ArrayList<String>();
		sign=0;
		run_sign=-1;
		gradelog=new HashMap();
		FUplist=new ArrayList<String>();
		reason=null;
		timeC=0;
	}
	public ExecutedTest()
	{
		Execute=new Test();
		SignUplist=new ArrayList<String>();
		gradeList=new ArrayList<String>();
		gradelog=new HashMap();
		FUplist=new ArrayList<String>();
		reason=null;
		timeC=0;
	}
	public String returnR()
	{
		return reason;
	}
	public int returnT()
	{
		return timeC;
	}
	public void setR(String s1)
	{
		 this.reason=s1;
	}
	public void setT(int s1)
	{
		 this.timeC=s1;
	}
	public ArrayList<String> getF()
	{
		return FUplist;
	}
	
	public int getSign() {return sign;};
	public void setSign(int state) {sign=state;};
	public int getrSign() {return run_sign;};
	public void setrSign(int state) {run_sign=state;};
	public String getexecuter() {return executer;};
	public void setexecuter(String str) { executer=str;};
	public ArrayList<String> getGradeList(){return gradeList;};
	public Test getTest()
	{
		return Execute;
	}
	public String getExe_code()
	{
		return Exe_code;
	}
	public void setExe_code(String EXE)
	{
		Exe_code=EXE;
	}
	public Date getDate()
	{
		return date;
	}
	public LocalDateTime getStartTime()
	{
		return StartTime;
	}
    public String getCurrentTime()
    {
    	return CurrentLength;
    }
    public void setCurrentTime(String time)
    {
    	CurrentLength=time;
    }
    public ArrayList<String> getSignUpList()
    {
    	return SignUplist;
    }
    public int getStudentNumber()
    {
    	return StudentNumStart;
    }
    public void setStudentNumber(int value)
    {
    	if(value>0)
    	{
    		StudentNumStart++;
    	}
    	if(value<0)
    	{
    		if(StudentNumStart>0)StudentNumStart--;
    	}
    }
    public int getFInishedNum()
    {
    	return StudentNumFinished;
    }
    public void setFInishedNum(int num)
    {
    	StudentNumFinished=num;
    }
    public int getFFInishedNum()
    {
    	return StudentNumForceFinished;
    }
    public void setFFInishedNum(int num)
    {
    	 StudentNumForceFinished=num;
    }
	public void setSignUpList(ArrayList<String> signUpList2) {
		this.SignUplist = signUpList2;	
	}
	public void setGList(ArrayList<String> gradeList2) {
		this.gradeList = gradeList2;
		
	}
}
