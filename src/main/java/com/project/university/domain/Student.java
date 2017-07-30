package com.project.university.domain;

import java.util.List;

public class Student {

	String name;
	String id;
	boolean isInternational;
	boolean isGraduate;
	List<Course> studCourseLst;

	public Student(String name, String id, boolean isInternational, boolean isGraduate, List<Course> studCourseLst) {
		super();
		this.name = name;
		this.id = id;
		this.isInternational = isInternational;
		this.isGraduate = isGraduate;
		this.studCourseLst = studCourseLst;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isInternational() {
		return isInternational;
	}

	public void setInternational(boolean isInternational) {
		this.isInternational = isInternational;
	}

	public boolean isGraduate() {
		return isGraduate;
	}

	public void setGraduate(boolean isGraduate) {
		this.isGraduate = isGraduate;
	}

	public List<Course> getStudCourseLst() {
		return studCourseLst;
	}

	public void setStudCourseLst(List<Course> studCourseLst) {
		this.studCourseLst = studCourseLst;
	}

}
