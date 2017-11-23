package com.sly.facade.manager.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sly.facade.crawer.model.CrawerModel;
import com.sly.facade.manager.dao.BooksDAO;
import com.sly.facade.manager.mapper.BooksMapper;
@Repository("booksDAO")
public class BooksDAOImpl implements BooksDAO {

	@Autowired
	BooksMapper booksMapper;

	public void insertBooks(List<CrawerModel> list) {
		
		booksMapper.insertBooks(list);
	}

	public void insertBook(CrawerModel list) {
		booksMapper.insertBook(list);
		
	}
	
	
	
}
