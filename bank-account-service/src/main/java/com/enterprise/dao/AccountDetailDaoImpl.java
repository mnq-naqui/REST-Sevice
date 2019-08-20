package com.enterprise.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.enterprise.entity.AccountDetail;

@Repository
public class AccountDetailDaoImpl implements AccountDetailDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	class AccountDetailMapper implements RowMapper<AccountDetail> {
        @Override
        public AccountDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        	
            final AccountDetail accDetail = new AccountDetail();
            
            accDetail.setAccountDetailId(rs.getString("account_detail_id"));
            accDetail.setAccountNumber(rs.getString("account_number"));
            accDetail.setAccountType(rs.getString("account_type"));
            accDetail.setBalance(rs.getDouble("balance"));
            
            return accDetail;
        }

    }

	@Override
	public AccountDetail createAccountDetail(String accountNumber) {
        final String sql = "INSERT INTO account_detail (account_number, account_type, balance) VALUES (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        
        AccountDetail ad = new AccountDetail();
        ad.setAccountNumber(accountNumber);
        ad.setAccountType("Saving");
        ad.setBalance(0.0D);
        
        this.jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ad.getAccountNumber());
            statement.setString(2, ad.getAccountType());
            statement.setDouble(3, ad.getBalance());
            return statement;
            
        }, keyHolder);
        
        Integer newCustomerId = keyHolder.getKey().intValue();
        ad.setAccountDetailId(newCustomerId.toString());
        return ad;
	}

}
