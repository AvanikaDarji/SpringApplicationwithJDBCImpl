package com.project.university.dao.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.project.university.dao.AccountDao;
import com.project.university.domain.Account;


@Repository("accountDaoJdbc")
public class AccountDaoImplementation implements AccountDao {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	private AccountRowMapper accountRowMapper;
	private NamedParameterJdbcTemplate dbTemplate;

	@PostConstruct
	private void setup(){
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		accountRowMapper = new AccountRowMapper();
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
						   .withTableName("account")
						   .usingColumns("accountID","balanceDue","dueDate");
	}
	
	
	public void createAccountForStudent(String studentID) {
		MapSqlParameterSource mapSqlParameterSource = getInsertParams( studentID );
		int rowsAffected = simpleJdbcInsert.execute(mapSqlParameterSource);
	}
	
	public MapSqlParameterSource getInsertParams( String studentID ){
		MapSqlParameterSource params = new MapSqlParameterSource( "accountID", studentID);
		params.addValue("accountDue", 0.0);
		params.addValue("dueDate", new Date());
		
		return params;
	}

	public double getBalance(String acctID) {
		
		String sql = "SELECT * FROM account WHERE accountID = :accountID";
		MapSqlParameterSource params = new MapSqlParameterSource("accountID", acctID);
		List<Account> matchingAccount = dbTemplate.query(sql, params, accountRowMapper);

		if (matchingAccount.size() == 0) {
			return 0.0;
		}
		
		return matchingAccount.get(0).getBalanceDue();
	}

	public double addBillToAccount(String acctID, double amountToBeAdded) {
		
		double exisingBal = getBalance(acctID);
		amountToBeAdded = exisingBal + amountToBeAdded;
		
		String sql = "UPDATE account set balanceDue = :balanceDue where accountID = :accountID";
		MapSqlParameterSource params = new MapSqlParameterSource("accountID", acctID);
		params.addValue("balanceDue", amountToBeAdded);
		int rowUpdated = dbTemplate.update(sql, params);

		return 0.00 ;
	}

	public List<String> overdueBalanceAcct() {
	
		String sql = "SELECT * FROM account WHERE balanceDue > 0 AND dueDate < CURDATE()";
		List<Account> matchingAccount = dbTemplate.query(sql, accountRowMapper);

		if (matchingAccount.size() == 0) {
			return null;
		}
		
		List<String> listofAcc = new ArrayList<String>();
		for (int i = 0; i < matchingAccount.size(); i++) {
			listofAcc.add( matchingAccount.get(i).getAccountID());
		}
		return listofAcc;
	}

	public double deductBillFromAccount(String acctID, double amountToBeDeducted) {
		double exisingBal = getBalance(acctID);
		amountToBeDeducted = exisingBal - amountToBeDeducted;
		
		String sql = "UPDATE account set balanceDue = :balanceDue where accountID = :accountID";
		MapSqlParameterSource params = new MapSqlParameterSource("accountID", acctID);
		params.addValue("balanceDue", amountToBeDeducted);
		int rowUpdated = dbTemplate.update(sql, params);

		return 0.00 ;
	}
	

	public void updateDueDate( String acctID){
		
		String sql = "UPDATE account set dueDate = date_sub(dueDate, interval 3 DAY) where accountID = :accountID";
		MapSqlParameterSource params = new MapSqlParameterSource("accountID", acctID);
		int rowUpdated = dbTemplate.update(sql, params);

	}
}
