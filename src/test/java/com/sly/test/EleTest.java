package com.sly.test;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sly.facade.entity.City;
import com.sly.facade.entity.Symentry;
import com.sly.facade.repositories.CityRepository;
import com.sly.facade.repositories.SymentryRepository;

/**
 * 
 * <b>Description：</b> 直销测试 <br/>
 * <b>ClassName：</b> sysCitytest <br/>
 * <b>@author：</b> sly <br/>
 * <b>@date：</b> 2016年7月19日 上午11:38:20 <br/>
 * <b>@version: </b>  <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context.xml" })
public class EleTest {

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	SymentryRepository symentryRepository;

	@Autowired
	CityRepository cityRepository;
	@Test
	public void addS() throws Exception{
		
		elasticsearchTemplate.deleteIndex(Symentry.class);
		elasticsearchTemplate.createIndex(Symentry.class);
		elasticsearchTemplate.putMapping(Symentry.class);
		elasticsearchTemplate.refresh(Symentry.class);
		Symentry city = new Symentry();
		city.setId("1345l");
		city.setText("别克 中国");
		symentryRepository.save(city);
		
	}
	
	@Test
	public void addC() throws Exception{
		elasticsearchTemplate.deleteIndex(Symentry.class);
		elasticsearchTemplate.createIndex(Symentry.class);
		elasticsearchTemplate.putMapping(Symentry.class);
		elasticsearchTemplate.refresh(Symentry.class);
		City c = new City();
		c.setId("qwerf");
		c.setName("你好");
		c.setDescription("我热爱我的祖国，别克都不要买");
		
		cityRepository.save(c);
	}
	
	@Test
	public void searC() throws Exception{
		
		String d = "bieke";
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("provincea")
        	    .withTypes("city").withQuery(matchQuery("description", d)).build();  
		
		List<City> sellingCarInfos = elasticsearchTemplate.queryForList(searchQuery, City.class);
		for (City symentry : sellingCarInfos) {
			System.err.println(symentry);
		}
	}
	
	@Test
	public void search() throws Exception{

		String d = "bieke，我是谁";
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("symentry")
        	    .withTypes("city-type").withQuery(matchQuery("text", d)).build();  
		
		List<Symentry> sellingCarInfos = elasticsearchTemplate.queryForList(searchQuery, Symentry.class);
		for (Symentry symentry : sellingCarInfos) {
			System.err.println(symentry);
		}
	}
}