package question;

import java.io.Serializable;

/**
 * This class represent the courses in the database.
 */
public class Course implements Serializable{
	private static final long serialVersionUID = 7509762707691988447L;

	private String code;
	private String year;
	private int semester;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
