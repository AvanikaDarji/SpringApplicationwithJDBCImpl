
package com.project.university.service;

import javax.naming.directory.InvalidAttributeValueException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.university.dao.AccountDao;
import com.project.university.exception.InvalidCreditCard;



@Service("paymentUsingWells")
public class PaymentServiceWells implements PaymentService {

	@Autowired
	@Qualifier("accountDaoJdbc")
	AccountDao accountDao;

	public void makePayment(com.project.university.domain.Student student, double amount, String creditCardNumber)
			throws InvalidCreditCard {

		// Add amount to be billed
		accountDao.addBillToAccount(student.getId(), amount);

		if (creditCardNumber.startsWith("5")) {

			throw new InvalidCreditCard("All card number starting with 5 are rejected by Wells Fargo");
		}

		System.out.println("Using the Wells Fargo credit card processor to process credit card number "
				+ creditCardNumber + " for " + student.getName() + "\nPayment of " + amount);
		
		//Extra** Payment Success so remover amount from balance
		accountDao.deductBillFromAccount(student.getId(), amount);
	}

}
