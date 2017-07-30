package com.project.university.client;

import java.util.ArrayList;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.university.dao.jdbc.AccountDaoImplementation;
import com.project.university.domain.*;
import com.project.university.service.StudentCheckoutService;



public class UniversityApplication {

	static List<Student> studList = null;

	public static void main(String[] args) {

		ApplicationContext container = new ClassPathXmlApplicationContext("app-context.xml");
		
		StudentCheckoutService studCheckout = (StudentCheckoutService) container.getBean("studentCheckout");
		
		AccountDaoImplementation accountDao = (AccountDaoImplementation) container.getBean("accountDaoJdbc");

		// Create Course
				List<Course> listOfAllCourses = createCourseObjects();
				
		studList = new ArrayList<Student>();

		

		// Add Courses
		List<Course> listofCourse = new ArrayList<Course>();
		listofCourse.add(listOfAllCourses.get(0));
		listofCourse.add(listOfAllCourses.get(2));

		// Create Students list
		createStudentObject("Avanika", "16387", true, false, listofCourse);
		accountDao.createAccountForStudent("16387");

		createStudentObject("Sid", "17523", false, true, listofCourse);
		accountDao.createAccountForStudent("17523");

		listOfAllCourses.add(listOfAllCourses.get(3));
		createStudentObject("Mark", "14587", true, false, listofCourse);
		accountDao.createAccountForStudent("14587");

		createStudentObject("David", "18656", false, false, listofCourse);
		accountDao.createAccountForStudent("18656");
		
		
		// Call Checkout for one student with valid credit card number
		studCheckout.checkout(studList.get(0), listofCourse, "54345555440"); //invalid credit card
		System.out.println("\n");
		studCheckout.checkout(studList.get(1), listofCourse, "66678778888");
		System.out.println("\n");
		studCheckout.checkout(studList.get(2), listofCourse, "12345678910");

		// Check Account
		
		System.out.println("Account Balance " + studList.get(0).getId() + ": " + accountDao.getBalance(studList.get(0).getId()));
		System.out.println("Account Balance " + studList.get(1).getId() + ": " + accountDao.getBalance(studList.get(1).getId()));
		System.out.println("Account Balance  " + studList.get(2).getId() + ": " + accountDao.getBalance(studList.get(2).getId()));

		
		accountDao.updateDueDate("17527");
		
		//Check over due student
		List<String> studIds = accountDao.overdueBalanceAcct();
		
		for (int i = 0; i < studIds.size(); i++) {
			System.out.println(studIds.get(i));
		}
		

	}
	

	public static List<Course> createCourseObjects() {

		List<Course> listofAllCourse = new ArrayList<Course>();

		// Create courses
		Course course1 = new Course("Rest services", "Computer Science", true, 3, 0);
		Course course2 = new Course("Adv Java", "Computer", true, 3, 0);
		Course course3 = new Course("Chemistry", "Chemistry", true, 3, 0);
		Course course4 = new Course("Marketing", "MBA", true, 3, 0);
		Course course5 = new Course("Maths", "General", false, 3, 0);

		listofAllCourse.add(course1);
		listofAllCourse.add(course2);
		listofAllCourse.add(course3);
		listofAllCourse.add(course4);
		listofAllCourse.add(course5);

		return listofAllCourse;
	}

	public static void createStudentObject(String name, String id, boolean isInternational, boolean isGraduate,
			List<Course> listofCourse) {

		// Create Student
		Student student = new Student(name, id, isInternational, isGraduate, listofCourse);
		studList.add(student);

	}

}
