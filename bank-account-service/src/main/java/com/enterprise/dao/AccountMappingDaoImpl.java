package com.enterprise.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.enterprise.entity.AccountMapping;

@Repository
public class AccountMappingDaoImpl implements AccountMappingDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	class AccountMappingMapper implements RowMapper<AccountMapping> {
        @Override
        public AccountMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
        	
            final AccountMapping accMapping = new AccountMapping();
            
            accMapping.setAccountDetailRef(rs.getInt("account_detail_ref"));
            accMapping.setBankRef(Integer.parseInt(rs.getString("bank_ref")));
            accMapping.setBranchRef(rs.getString("branch_ref"));
            accMapping.setUserRef(Integer.parseInt(rs.getString("user_ref")));
            return accMapping;
        }

    }

	@Override
	public void creatMapping(Integer bankId, String branchId, Integer accountDetailId, Integer userId) {
        final String sql = "INSERT INTO account_mapping (account_detail_ref, bank_ref, branch_ref, user_ref) VALUES (?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        
        this.jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, accountDetailId);
            statement.setInt(2, bankId);
            statement.setString(3, branchId);
            statement.setInt(4, userId);
            return statement;
        }, keyHolder);
        
	}

	@Override
	public List<AccountMapping> getMappingByUserId(Integer userId) {

		final String sql = "SELECT * FROM account_mapping WHERE user_ref = ?";
		return jdbcTemplate.query(sql, new Object[]{userId}, new AccountMappingMapper());
	
	}

	// Delete
	@Override
    public boolean delete(Integer id) {
        final String sql = "DELETE FROM account_mapping WHERE user_ref = ?";
        final Object[] params = new Object[]{id};
        
		return this.jdbcTemplate.update(sql, params) == 1;
	}
	
	

}
