package com.sly.facade.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sly.demo.model.APIRequest;
import com.sly.demo.model.APIResult;
import com.sly.facade.manager.logic.UserLogic;
import com.sly.facade.service.UserService;

@Component("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserLogic  userLogic;
	
	public void push(final Object o) {

	}

	public APIResult getUserList(APIRequest request) {

		APIResult result = new APIResult();
		
		Map<String,Object> map = request.getDataMap();
		
		List<Map<String,Object>> mapList = 	userLogic.getUserList(map);
		
		result.setData(mapList);
				
		return result;
	}

	public APIResult PUT(APIRequest request) {
		Map<String,Object> map = request.getDataMap();
		for (Entry<String,Object> iterable_element : map.entrySet()) {
			System.err.println(iterable_element.getValue()+iterable_element.getKey());
		}
		return null;
	}

	public APIResult POST(APIRequest request) {
		Map<String,Object> map = request.getDataMap();
		for (Entry<String,Object> iterable_element : map.entrySet()) {
			System.err.println(iterable_element.getValue()+iterable_element.getKey());
		}
		return null;
	}

	public APIResult del(APIRequest request) {
		Map<String,Object> map = request.getDataMap();
		for (Entry<String,Object> iterable_element : map.entrySet()) {
			System.err.println(iterable_element.getValue()+iterable_element.getKey());
		}
		return null;
	}

}
