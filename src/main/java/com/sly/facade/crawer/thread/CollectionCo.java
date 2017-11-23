package com.sly.facade.crawer.thread;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sly.facade.crawer.model.SightHotPhoto;

public class CollectionCo implements Callable<List<SightHotPhoto>> {

	private String city;  
	
	WebClient webClient = new WebClient(BrowserVersion.CHROME,"139.224.13.34",8080);
	
    public CollectionCo(String city) {  
        this.city=city;  
     // 设置Ajax异步  
        try{
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());  
            webClient.getOptions().setJavaScriptEnabled(false);
    		webClient.getOptions().setCssEnabled(true);
        }catch(Exception e){
        	e.printStackTrace();
        }

		
    }
	
	public List<SightHotPhoto> call() throws Exception {
		System.err.println(city+"开始"+Thread.currentThread().getName());
		List<SightHotPhoto> t = new ArrayList<SightHotPhoto>();

		for(int i=0;i<15;i++){
			System.err.println(i+"开始");

			String url = "http://piao.qunar.com/ticket/list.htm?keyword="+URLEncoder.encode(city, "UTF-8")+"&region=&from=mpl_search_suggest&page="+i;
			HtmlPage page = webClient.getPage(url);
			try {
				Thread.sleep(100*5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Document docu =  Jsoup.parse(page.asXml());
			
			Element overContent = docu.getElementById("search-list");
			Elements sight_items =  overContent.getElementsByClass("sight_item");
			SightHotPhoto sightHotPhoto = null;
			for (Element element : sight_items) {
				sightHotPhoto = new SightHotPhoto();
				Element sight_item_about = element.select("div[class=sight_item_about]").first();
				
				String sightName = sight_item_about.getElementsByTag("h3").text();//景区名称
				sightHotPhoto.setSightName(sightName);
				
				Element sight_item_info =  sight_item_about.select("div[class=sight_item_info]").first();
				String area = sight_item_info.select("span[class=area]").first().text();//区域
				sightHotPhoto.setArea(area);
				
				Element hotElement = sight_item_info.select("div[class=sight_item_hot]").select("span[class=product_star_level]").first();
				if(sight_item_info.hasClass("level")){
					String level = sight_item_info.select("span[class=level]").first().text();//景区等级
					sightHotPhoto.setLevel(level);
				}
				String hotContent =  hotElement.getElementsByTag("span").last().attr("style");
				if(hotContent.indexOf(":")>-1){
					String hotNumRate = hotContent.split(":")[1];
					Double hotNum =Double.valueOf(hotNumRate.replaceAll("%", "").replaceAll(";", "")) ;//热度数
					sightHotPhoto.setHot(hotNum);
				}
				
				String address  = sight_item_info.select("p[class=address color999]").text();//地址
				sightHotPhoto.setDetailsAddress(address);
				String slogan = sight_item_info.select("div[class=intro color999]").text();//标语
				sightHotPhoto.setSlogan(slogan);
				Element sight_item_pop =  element.select("div[class=sight_item_pop]").first();
				
				
//				String startPrice = sight_item_pop.select("span[class=sight_item_price]").first().getElementsByTag("em").first().text();//起步价
//				sightHotPhoto.setStartPrice(startPrice);
				
				if(sight_item_pop.hasClass("hot_num")){
					String hot_num = sight_item_pop.select("span[class=hot_num]").first().text();//销量
					sightHotPhoto.setAccount(Integer.parseInt(hot_num));
				}else{
					sightHotPhoto.setAccount(0);

				}

				t.add(sightHotPhoto);
			}
			System.err.println(i+"结束");

		}
		// 关闭webClient
		webClient.close();
		return t;
	}

}
