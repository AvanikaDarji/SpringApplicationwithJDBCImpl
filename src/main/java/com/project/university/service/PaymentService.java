/**

 * 
 */
package com.project.university.service;

import javax.naming.directory.InvalidAttributeValueException;

import com.project.university.domain.Student;
import com.project.university.exception.InvalidCreditCard;
import com.sun.media.sound.InvalidDataException;


public interface PaymentService {
	
	public void makePayment(Student student,double amount, 
			String creditCardNumber ) throws InvalidCreditCard;

}
