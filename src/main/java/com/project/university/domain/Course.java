package com.project.university.domain;

public class Course {

	String courseName;
	String deptName;
	boolean isGraduate;
	int units;
	int noOfStudentsEnrold;

	public Course(String courseName, String deptName, boolean isGraduate, int units, int noOfStudentsEnrold) {
		super();
		this.courseName = courseName;
		this.deptName = deptName;
		this.isGraduate = isGraduate;
		this.units = units;
		this.noOfStudentsEnrold = noOfStudentsEnrold;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public boolean isGraduate() {
		return isGraduate;
	}

	public void setGraduate(boolean isGraduate) {
		this.isGraduate = isGraduate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getNoOfStudentsEnrold() {
		return noOfStudentsEnrold;
	}

	public void setNoOfStudentsEnrold(int noOfStudentsEnrold) {
		this.noOfStudentsEnrold = noOfStudentsEnrold;
	}

}
