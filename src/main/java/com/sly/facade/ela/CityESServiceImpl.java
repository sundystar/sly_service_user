package com.sly.facade.ela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import com.sly.facade.entity.City;
import com.sly.facade.repositories.CityRepository;
import java.util.List;
import static org.elasticsearch.index.query.QueryBuilders.*;
/**
 * 城市 ES 业务逻辑实现类
 * <p>
 * Created by bysocket on 20/06/2017.
 */
@Service("cityService")
public class CityESServiceImpl implements CityService {

    @Autowired
	ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    CityRepository cityRepository;
    public void saveCity(City city) {
    	cityRepository.save(city);
    }


	public List<City> searchcity(String d) {
		
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("provincea")
        	    .withTypes("city").withQuery(matchQuery("description", d)).build();  
		
		List<City> sellingCarInfos = elasticsearchTemplate.queryForList(searchQuery, City.class);

		return sellingCarInfos;
	}
}