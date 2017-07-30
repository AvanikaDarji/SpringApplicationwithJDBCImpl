
 
package com.project.university.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.university.domain.*;
import com.project.university.exception.InvalidCreditCard;



@Service("studentCheckout")
public class StudentCheckoutService {

	@Autowired
	@Qualifier("paymentUsingWells")
	PaymentService paymentService;

	@Autowired
	@Qualifier("tutionNational")
	TuitionCalculatorService tutionCalculator;

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void setTutionCalculator(TuitionCalculatorService tutionCalculator) {
		this.tutionCalculator = tutionCalculator;
	}

	public void checkout(Student student, List<Course> listOfCourseByStudent, String creditCardNumber) {

		student.setStudCourseLst(listOfCourseByStudent);

		for (int i = 0; i < listOfCourseByStudent.size(); i++) {
			listOfCourseByStudent.get(i)
					.setNoOfStudentsEnrold(listOfCourseByStudent.get(i).getNoOfStudentsEnrold() + 1);
		}

		// Compute Tuition bill
		double amount = tutionCalculator.computeTutition(student, listOfCourseByStudent);

		// Process Payment
		try {
			paymentService.makePayment(student, amount, creditCardNumber);
		} catch (InvalidCreditCard e) {
			// TODO Auto-generated catch block
		}

	}
}
