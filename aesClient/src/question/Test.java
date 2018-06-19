package question;

import java.util.ArrayList;

public class Test {

	private String ID;
	private String code;
	private ArrayList<Question> QuestionList;
	private ArrayList<String> Q_point_list;
	private ArrayList<Integer> Answers;
	private ArrayList<String> remarkList;
	private String GradeChangeReason;
	private String Owner;
	private String Length;
	private String EXE_code;
	private int grade;
	private String name;
	private int c;
	private int copy_sign;
	public Test(Test c)
	{
		this.ID=c.ID;
		this.code=c.code;
		this.QuestionList=c.QuestionList;
		this.Q_point_list=c.Q_point_list;
		this.Answers=new ArrayList<Integer>();
		this.remarkList=new ArrayList<String>();
		this.GradeChangeReason=null;
		this.Owner=c.Owner;
		this.Length=c.Length;
		this.EXE_code=c.EXE_code;
		this.grade=0;
		this.name=c.name;
		this.c=c.c;
		this.copy_sign=c.copy_sign;
	}
	public Test()
	{
		remarkList=new ArrayList<String>();
		QuestionList=new ArrayList<Question>();
		Q_point_list=new ArrayList<String>();
		Answers=new ArrayList<Integer>();
		EXE_code=null;
		grade=0;
		name=null;
		GradeChangeReason=null;
		c=-1;
		this.copy_sign=0;
	}
	public Test(String Test_ID , String Test_code , String Own , String time)
	{
		QuestionList=new ArrayList<Question>();
		Q_point_list=new ArrayList<String>();
		Answers=new ArrayList<Integer>();
		remarkList=new ArrayList<String>();
		this.ID=Test_ID;
		this.code=Test_code;
		this.Owner=Own;
		this.Length=time;
		EXE_code=null;
		grade=0;
		name=null;
		GradeChangeReason=null;
		c=-1;
		this.copy_sign=0;
	}
	public int getcop() {return copy_sign;}
	public void setcop(int reason) {copy_sign=reason;}
	public String getReason() {return GradeChangeReason;}
	public void setReason(String reason) {GradeChangeReason=reason;}
	public String getName() {return name;}
	public void setName(String N) {name=N;}
	public ArrayList<String> getremarks()
	{
		return remarkList;
	}
	public ArrayList<Integer> getAnswers()
	{
		return Answers;
	}
	public ArrayList<Question> getQuestionlist()
	{
		return QuestionList;
	}
	public void setQuestionlist(ArrayList<Question> q_list)
	{
		 QuestionList=q_list;
	}
	public ArrayList<String> getPointsList()
	{
		return Q_point_list;
	}
	public void setPointsList(ArrayList<String> points)
	{
		Q_point_list=points;
	}
	public String getId()
	{
		return ID;
	}
	public void setId(String Id)
	{
		ID=Id;
	}
	public String getcode()
	{
		return code;
	}
	public void setcode(String code1)
	{
		code=code1;
	}
	public String getOwner()
	{
		return Owner;
	}
	public void setOwner(String Own)
	{
		Owner=Own;
	}
	public String getLength()
	{
		return Length;
	}
	public void setLength(String time)
	{
		Length=time;
	}
	public String getExe_code()
	{
		return this.EXE_code;
	}
	public void setExe_code(String exe)
	{
	    this.EXE_code=exe;
	}
	public int getGrade()
	{
		return grade;
	}
	public void setGrade(int val)
	{
		grade=val;
	}
	public int getC() {return c;}
	public void setC(int f) {c=f;}
    public void setAnswers()
    {
    	Answers=new ArrayList<Integer>();
    }

}
