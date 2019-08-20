package com.enterprise.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.enterprise.entity.UserDetail;

@Repository
public class UserDetailDaoImpl implements UserDetailDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	class UserDetailMapper implements RowMapper<UserDetail> {
        @Override
        public UserDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        	
            final UserDetail customer = new UserDetail();
            
            customer.setUserId(rs.getInt("user_detail_id"));
            customer.setUserName(rs.getString("username"));
            customer.setEmail(rs.getString("email"));
            customer.setMobile(rs.getString("mobile"));
            customer.setCurrentAddress(rs.getString("current_address"));
            customer.setPermanentAddress(rs.getString("permanent_address"));
            customer.setCityRef(Integer.parseInt(rs.getString("city_ref")));
            customer.setStateRef(Integer.parseInt(rs.getString("state_ref")));
            customer.setPincode(rs.getString("pincode"));
            
            return customer;
        }

    }
	
	 // Create
	@Override
    public UserDetail create(final UserDetail customer) {
        final String sql = "INSERT INTO user_detail (username, email, mobile, current_address, permanent_address, city_ref, state_ref, pincode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        
        this.jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getUserName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getMobile());
            statement.setString(4, customer.getCurrentAddress());
            statement.setString(5, customer.getPermanentAddress());
            statement.setString(6, Integer.toString(customer.getCityRef().intValue()));
            statement.setString(7, Integer.toString(customer.getStateRef().intValue()));
            statement.setString(8, customer.getPincode());
            return statement;
        }, keyHolder);
        
        Integer newCustomerId = keyHolder.getKey().intValue();
        customer.setUserId(newCustomerId);
        return customer;
	}
	
	
	 // Update
	@Override
    public boolean update(final UserDetail userDetail) {
       final String sql = "UPDATE user_detail SET user_name=?, email=?, mobile=?, current_address=?, permanent_address=?, city_ref=?, state_ref=?, pincode=? WHERE user_id=?";
       final Object[] params = new Object[]{userDetail.getUserName(), userDetail.getEmail(), userDetail.getMobile(),userDetail.getCurrentAddress(), userDetail.getPermanentAddress(), userDetail.getCityRef(), 
    		   userDetail.getStateRef(), userDetail.getPincode(), userDetail.getBankRef(), userDetail.getBranchRef(), userDetail.getUserId()};
        
		return this.jdbcTemplate.update(sql, params) == 1;
	}

    // Delete
	@Override
    public boolean delete(Integer id) {
        final String sql = "DELETE FROM customer WHERE customer_id = ?";
        final Object[] params = new Object[]{id};
        
		return this.jdbcTemplate.update(sql, params) == 1;
	}
	
	// Retrieve all
	@Override
    public List<UserDetail> findAll() {
        final String sql = "SELECT user_detail_id, username, email, mobile, current_address, permanent_address, city_ref, state_ref, pincode FROM user_detail";
		return this.jdbcTemplate.query(sql, new UserDetailMapper());
	}

    // Retrieve one
	@Override
    public Optional<UserDetail> findById(Integer id) {
		final String sql = "SELECT user_detail_id, username, email, mobile, current_address, permanent_address, city_ref, state_ref, pincode FROM user_detail WHERE user_id = ?";
		
		return this.jdbcTemplate.query(
		        sql,
				rs -> rs.next() ?
                        Optional.of(new UserDetailMapper().mapRow(rs, 1)):
                        Optional.empty(),
                id);
	}

}
