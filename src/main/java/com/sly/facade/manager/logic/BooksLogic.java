package com.sly.facade.manager.logic;

import java.util.List;

import com.sly.facade.crawer.model.CrawerModel;

public interface BooksLogic {
	void insertBooks(List<CrawerModel> list);

	void insertBook(CrawerModel list);
}
