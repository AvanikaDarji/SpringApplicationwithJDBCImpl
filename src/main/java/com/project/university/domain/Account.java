package com.project.university.domain;

import java.util.Date;

public class Account {

	String accountID;
	double balanceDue;
	Date dueDate;

	public Account(String accountID, double d, Date dueDate) {
		super();
		this.accountID = accountID;
		this.balanceDue = d;
		this.dueDate = dueDate;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public double getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(double balanceDue) {
		this.balanceDue = balanceDue;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date issue) {
		this.dueDate = issue;
	}

}
