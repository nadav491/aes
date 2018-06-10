package user;

import java.util.ArrayList;

import test.Test;

/**
 * This class implement the studnet type user.
 * Extends from user.
 */
public class Student extends User{
	private float grade;
	private ArrayList<Test> tests;
	public Student(String id, String type, boolean login, float grade, ArrayList<Test> tests) {
		super(id, type, login);
		this.grade = grade;
		this.tests = tests;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public ArrayList<Test> getTests() {
		return tests;
	}
	public void setTests(ArrayList<Test> tests) {
		this.tests = tests;
	}
}
