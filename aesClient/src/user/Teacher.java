package user;

import java.util.ArrayList;

import question.Course;

/**
 * This class implement the teacher type user.
 * Extends from user.
 */
public class Teacher extends User {
	private ArrayList<Course> courses;
	
	public Teacher(String id, String type, boolean login, ArrayList<Course> courses) {
		super(id, type, login);
		this.courses = courses;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
}
