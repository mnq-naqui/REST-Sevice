package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enterprise.entity.Bank;

@Repository
public class BankDaoImpl implements BankDao {
	
	@Autowired
	private NamedParameterJdbcTemplate nameParameterJdbcTemplate;
	
	class BankMapper implements RowMapper<Bank> {
        @Override
        public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
        	
            final Bank bank = new Bank();
            
            bank.setBankId(rs.getInt("bank_id"));
            bank.setBankName(rs.getString("bank_name"));
            bank.setBankCode(rs.getString("bank_code"));
            bank.setIfscCode(rs.getString("ifsc_code"));
            bank.setAddress(rs.getString("address"));
            bank.setCityRef(Integer.parseInt(rs.getString("city_ref")));
            bank.setStateRef(Integer.parseInt(rs.getString("state_ref")));
            bank.setPincode(rs.getString("pincode"));
            
            return bank;
        }

    }

	@Override
	public List<Bank> findBankForUser(List<String> bankIds) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("bankIds", bankIds);
		final String sql = "SELECT bank_id, bank_name, bank_code, ifsc_code, address, city_ref, state_ref, pincode FROM bank where bank_id in (:bankIds) ";
		return this.nameParameterJdbcTemplate.query(sql, parameters ,new BankMapper());

	}

}
