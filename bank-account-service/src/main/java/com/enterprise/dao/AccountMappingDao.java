package com.enterprise.dao;

import java.util.List;

import com.enterprise.entity.AccountMapping;

public interface AccountMappingDao {
	
	public void creatMapping(Integer bankId, String branchId, Integer accountDetailId, Integer userId);
	
	public List<AccountMapping> getMappingByUserId(Integer userId);
	
	boolean delete(Integer userId);

}
