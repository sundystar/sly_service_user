package com.sly.facade.manager.logic.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sly.facade.manager.dao.UserDao;
import com.sly.facade.manager.logic.UserLogic;

@Component("userLogic")
public class UserLogicImpl implements UserLogic{

	@Autowired
	UserDao userDao;
	
	public List<Map<String, Object>> getUserList(Map<String,Object> map) {
		
		System.err.println(map.get("cityCode"));
		
		return userDao.getUserList();
	}

}
