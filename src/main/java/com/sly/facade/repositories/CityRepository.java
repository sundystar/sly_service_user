package com.sly.facade.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import com.sly.facade.entity.City;

public interface CityRepository    extends ElasticsearchCrudRepository<City,String>{

}
