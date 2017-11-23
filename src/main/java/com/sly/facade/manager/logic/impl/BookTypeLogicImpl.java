package com.sly.facade.manager.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sly.facade.crawer.model.BookType;
import com.sly.facade.manager.logic.BookTypeLogic;
import com.sly.facade.manager.mapper.BooksTypeMapper;
@Component("bookTypeLogic")
public class BookTypeLogicImpl implements BookTypeLogic {

	@Autowired
	BooksTypeMapper booksTypeMapper;
	
	public void insertBookType(List<BookType> list){
		booksTypeMapper.insertBooksType(list);
	}
	
}
