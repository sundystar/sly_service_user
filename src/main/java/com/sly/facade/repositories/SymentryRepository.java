package com.sly.facade.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.sly.facade.entity.Symentry;

public interface SymentryRepository   extends ElasticsearchCrudRepository<Symentry,String>{

}
