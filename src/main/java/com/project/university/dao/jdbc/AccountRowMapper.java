/**
 * 
 */
package com.project.university.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.university.domain.Account;


public class AccountRowMapper implements RowMapper<Account> {

	public Account mapRow(ResultSet rs, int row) throws SQLException {

		Account account = null;

		account = new Account(rs.getString("accountID"), rs.getDouble("balanceDue"), rs.getDate("dueDate"));

		return account;
	}

}
