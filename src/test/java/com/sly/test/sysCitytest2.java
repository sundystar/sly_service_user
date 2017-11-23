
package com.sly.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sly.facade.crawer.model.BookType;
import com.sly.facade.crawer.model.CrawerModel;
import com.sly.facade.crawer.model.HoneModel;
import com.sly.facade.crawer.util.HtmlUnit01;
import com.sly.facade.ela.CityService;
import com.sly.facade.entity.City;
import com.sly.facade.manager.logic.BookTypeLogic;
import com.sly.facade.manager.logic.BooksLogic;

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
public class sysCitytest2 {

	@Autowired
	BooksLogic booksLogic;
	
	@Autowired
	BookTypeLogic bookTypeLogic;
	
	@Autowired
	CityService cityService;
	
	
	/**
	 * 
	 * @Title: test1
	 * @Description: (成功励志类)
	 * @author: sly
	 * @date: 2017年9月20日 上午11:54:19
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		
		String url = "http://book.dangdang.com/01.07.htm";; String id="bd";
//		String url = "http://book.dangdang.com/"; String id="bd_auto";
		List<CrawerModel> list =HtmlUnit01.test1(url,id,8);//1代表成功励志 2经济管理3小说4文学5科普6两性7古籍8艺术9计算机
		
		booksLogic.insertBooks(list);
		
	}
	
	/**
	 * 
	 * @Title: test1
	 * @Description: (图书类别)
	 * @author: sly
	 * @date: 2017年9月20日 上午11:54:19
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception{
		
		String url = "http://book.dangdang.com/";
		
		List<BookType> list =HtmlUnit01.test2(url);
		
		bookTypeLogic.insertBookType(list);
	
	}
	
	
	/**
	 * 
	 * @Title: test1
	 * @Description: (java)
	 * @author: sly
	 * @date: 2017年9月20日 上午11:54:19
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception{
		
		String url = "http://search.dangdang.com/?key=java&act=input&show=big#J_tab";
		
		List<CrawerModel> list =HtmlUnit01.test3(url,8);
		
		booksLogic.insertBooks(list);
	
	}
	
	
	
	/**
	 * 
	 * @Title: test1
	 * @Description: 房天下
	 * @author: sly
	 * @date: 2017年9月20日 上午11:54:19
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception{
		
		String url = "http://esf.sh.fang.com";
		
		List<HoneModel> list =HtmlUnit01.fangtest(url);
		
		for (HoneModel honeModel : list) {
			System.err.println(honeModel.getNum()+"	"+honeModel.getTi()+"	"+honeModel.getSart()+"	"+honeModel.getCeng()+"	"+honeModel.getNiandai()+"	"+honeModel.getPrice());
		}
//		booksLogic.insertBooks(list);
	
	}
	
	@Test
	public void test8() throws Exception{
		
		String url = "http://auto.chexun.com/search-a2-b0-c0-d0-e0-f0-g0-h0-i0:0.html";
		
		List<HoneModel> list =HtmlUnit01.fangtest1(url);
		
//		booksLogic.insertBooks(list);
	
	}
	
	
	/**
	 * 
	 * @Title: test1
	 * @Description: 景点数据的爬取
	 * @author: sly
	 * @date: 2017年9月20日 上午11:54:19
	 * @throws Exception jz1P4yP46Qfjn0hfcyswX88AjsMVbEdL
	 * http://api.map.baidu.com/geocoder/v2/?address=地址&output=json&ak=6eea93095ae93db2c77be9ac910ff311
	 */
	@Test
	public void view() throws Exception{
		
//		int num = 1;
//		String searchContent = "上海";
//		String url = "http://piao.qunar.com/ticket/list.htm?keyword="+searchContent+"&region=&from=mpl_search_suggest&page="+num;
		
		HtmlUnit01.view2("");
		

	
	}
	
}