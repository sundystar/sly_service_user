package com.sly.facade.manager.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sly.facade.crawer.model.CrawerModel;
import com.sly.facade.manager.dao.BooksDAO;
import com.sly.facade.manager.logic.BooksLogic;

@Component("booksLogic")
public class BooksLogicImpl  implements BooksLogic{

	@Autowired
	BooksDAO booksDAO;

	public void insertBooks(List<CrawerModel> list) {
		
		booksDAO.insertBooks(list);
	}

	public void insertBook(CrawerModel list) {
		booksDAO.insertBook(list);
		
	}
	
}
