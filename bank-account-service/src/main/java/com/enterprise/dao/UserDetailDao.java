package com.enterprise.dao;

import java.util.List;
import java.util.Optional;

import com.enterprise.entity.UserDetail;

public interface UserDetailDao {

	UserDetail create(UserDetail userDetail);

	boolean update(UserDetail userDetail);

	boolean delete(Integer id);

	List<UserDetail> findAll();

	Optional<UserDetail> findById(Integer id);

}
