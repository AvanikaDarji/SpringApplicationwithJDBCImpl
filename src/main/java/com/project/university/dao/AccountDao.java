
package com.project.university.dao;

import java.util.List;

import com.project.university.domain.*;


public interface AccountDao {

	public void createAccountForStudent(String studentID);

	public double getBalance(String acctID);

	public double addBillToAccount(String acctID, double amountToBeAdded);

	public List<String> overdueBalanceAcct();
	

	public double deductBillFromAccount(String acctID, double amountToBeDeducted);

}
