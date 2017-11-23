package com.sly.facade.manager.mapper;

import java.util.List;
import com.sly.facade.crawer.model.CrawerModel;

public interface BooksMapper {

	void insertBooks(List<CrawerModel> list);

	void insertBook(CrawerModel list);

}
