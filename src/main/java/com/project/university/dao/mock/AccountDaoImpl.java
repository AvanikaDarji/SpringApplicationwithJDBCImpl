/**
 * 
 */
package com.project.university.dao.mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.security.auth.login.AccountLockedException;

import org.springframework.stereotype.Repository;

import com.project.university.dao.AccountDao;
import com.project.university.domain.Account;
import com.project.university.domain.Student;


@Repository("accountDaoMock")
public class AccountDaoImpl implements AccountDao {

	public ArrayList<Account> acctList = new ArrayList<Account>();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public void createAccountForStudent(String studentID) {

		// Set date to current date
		Account newAcc = new Account(studentID, 0.00, new Date());
		acctList.add(newAcc);
	}

	public double getBalance(String acctID) {

		for (Account account : acctList) {
			if (account.getAccountID().equals(acctID)) {
				return account.getBalanceDue();
			}
		}
		return 0.00;
	}

	public double addBillToAccount(String acctID, double amountToBeAdded) {

		for (Account account : acctList) {
			if (account.getAccountID().equals(acctID)) {

				double totAmount = account.getBalanceDue() + amountToBeAdded;
				account.setBalanceDue(totAmount);
				return account.getBalanceDue();
			}
		}
		return 0.00;
	}

	public List<String> overdueBalanceAcct() {

		// modify account 0 date set to 21 Feb
		Calendar cal = Calendar.getInstance();
		cal.setTime(acctList.get(0).getDueDate());
		cal.set(Calendar.DATE, 21);
		acctList.get(0).setDueDate(cal.getTime());
		// System.out.println( "DATE of "+ acctList.get(0).getAccountID() +": "
		// + dateFormat.format(acctList.get(0).getDueDate()) );

		List<String> duePayAccts = new ArrayList<String>();

		for (Account account : acctList) {
			if (account.getBalanceDue() > 0 && account.getDueDate().before(new Date())) {

				duePayAccts.add(account.getAccountID());
			}
		}
		return duePayAccts;
	}
	
	/**
	 * Extra implementation 
	 * To deduct amount from balance if payment successful
	 */

	public double deductBillFromAccount(String acctID, double amountToBeDeducted) {

		for (Account account : acctList) {
			if (account.getAccountID().equals(acctID)) {

				double totAmount = account.getBalanceDue() - amountToBeDeducted;
				account.setBalanceDue(totAmount);
				return account.getBalanceDue();
			}
		}
		return 0.00;
	}
}
