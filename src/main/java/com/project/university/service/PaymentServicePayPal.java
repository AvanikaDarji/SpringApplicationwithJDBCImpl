/**
 * 
 */
package com.project.university.service;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.university.dao.AccountDao;
import com.project.university.domain.Student;
import com.project.university.exception.InvalidCreditCard;


@Service("paymentUsingPaypal")
public class PaymentServicePayPal implements PaymentService {

	@Autowired
	@Qualifier("accountDaoJdbc")
	AccountDao accountDao;

	public void makePayment(Student student, double amount, String creditCardNumber)
			throws InvalidCreditCard {

		// Add amount to be billed
		accountDao.addBillToAccount(student.getId(), amount);

		Pattern pattern = Pattern.compile(".*[a-zA-Z]+.*$");
		Matcher matcher = pattern.matcher(creditCardNumber);

		if (creditCardNumber.length() > 15 || matcher.matches()) {
			throw new InvalidCreditCard("Only 15 Digits(alphabets are not allowed");
		}

		System.out.println("Using the PayPal credit card" + creditCardNumber
				+ " for " + student.getName() + "\nPayment of " + amount);
		
		//Extra** Payment Success so remover amount from balance
		accountDao.deductBillFromAccount(student.getId(), amount);

	}

}
