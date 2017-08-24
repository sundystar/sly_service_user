package com.sly.facade.manager.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sly.facade.manager.dao.UserDao;
import com.sly.facade.manager.mapper.UserMapper;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	UserMapper userMapper;
	
	public List<Map<String, Object>> getUserList() {
		return userMapper.getUserList();
	}

}
