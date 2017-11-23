package com.sly.facade.crawer.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sly.facade.crawer.model.BookType;
import com.sly.facade.crawer.model.CrawerModel;
import com.sly.facade.crawer.model.HoneModel;
import com.sly.facade.crawer.model.HotPhoto;
import com.sly.facade.crawer.model.SightHotPhoto;
import com.sly.facade.crawer.thread.CollectionCo;
import com.sly.facade.crawer.thread.LocationCo;

public class HtmlUnit01 {
	
	static 		WebClient webClient = new WebClient(BrowserVersion.CHROME);
	
	static {
		// 打开的话，就是执行javaScript/Css
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(true);
//		//设置代理  
//        ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();   
//        proxyConfig.setProxyHost("139.224.13.34");    
//        proxyConfig.setProxyPort(8080);

	}
	
	public static List<CrawerModel> test1(String url,String id,int type) throws Exception {

		// 获取页面
		HtmlPage page = webClient.getPage(url);
		DomElement el = page.getElementById(id);
		
		Document docu =  Jsoup.parse(el.asXml());
		
		Elements overContent = docu.getElementsByClass("over");
		
		List<CrawerModel> d = new ArrayList<CrawerModel>();

		for (Element tt : overContent) {
			Elements elements = tt.select("ul[class=list_aa]").select("li[type=rollitem]");
			label:
			for (Element ele : elements) {
				Elements elebook = ele.select("ul[class=product_ul ]").select("li");

				for (Element element : elebook) {
					CrawerModel model = new CrawerModel();
					Elements e = element.select("a[class=img]");
					for (Element book : e) {
						if(book.attr("title").contains("电子书")){
							break label;
						}
						model.setTitle(book.attr("title"));
						model.setImg(book.children().attr("src"));
						model.setHref(book.attr("href"));
					}
					model.setBookId(element.attr("nname"));
					Element author = element.select("p[class=author]").first();
					if(author==null){
						model.setAuthor("");
					}else{
						model.setAuthor(author.text());
					}
					model.setIsNew(0);
					model.setIsHot(1);
					model.setType(type);
					model.setCreateDate(new Date());
					model.setId(0);
					Elements price = element.select("p[class=price]");
					for (Element bookPrice : price) {
						Elements rob = bookPrice.select("span");
						for (Element element2 : rob) {
							if (element2.hasClass("rob")) {
								model.setPrice_num(element2.select("span[class=num]").text());
								model.setPrice_tail(element2.select("span[class=tail]").text());
							}
							if (element2.hasClass("price_r")) {
								model.setPrice_r_num(element2.select("span[class=num]").text());
								model.setPrice_r_tail(element2.select("span[class=tail]").text());
							}
						}
					}
					d.add(model);
				}
			}
		}
		for (CrawerModel crawerModel : d) {
			ParseHtml.getContent(crawerModel, webClient);
		}
		// 关闭webClient
		webClient.close();
		
		return d;
	}


	public static List<BookType> test2(String url) {
		
		List<BookType> list = new ArrayList<BookType>();
		// 获取页面
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "bd_auto");
		map.put("url", url);
		Document doc = getDocument(webClient,map);
	
		Elements eles = doc.select("div[class=con flq_body]").select("div[class=level_one]");
		for (Element element : eles) {
			BookType bookType = new BookType();
			if(!element.text().contains("特色书单")){
				String parentId = element.attr("name");
				String typeName = element.select("dt").first().text();
				bookType.setId(parentId);
				bookType.setParentId("0");
				bookType.setOrder("1");
				bookType.setStatus(1);
				bookType.setWeight(1);
				bookType.setTypeName(typeName);
				list.add(bookType);
				
				String elesA = element.getElementsByTag("dd").first().text();
				String[] result = elesA.split(" ");
				for (int i=0;i<result.length;i++) {
					String typeChildName = result[i];
					BookType bookType2 = new BookType();
					bookType2.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					bookType2.setParentId(parentId);
					bookType2.setOrder("1_"+i);
					bookType2.setStatus(1);
					bookType2.setWeight(1);
					bookType2.setTypeName(typeChildName);
					list.add(bookType2);
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: getDocument
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author: lzy
	 * @date: 2017年9月20日 下午1:45:14
	 * @param webClient
	 * @param id 
	 * @param key
	 * @param url
	 * @return
	 */
	public static Document getDocument(WebClient webClient,Map<String,Object> map){
		
		HtmlPage page = null;
		try {
			page = webClient.getPage(map.get("url").toString());
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HtmlElement btn = page.getHtmlElementById(map.get("id").toString());
		
		Document doc = Jsoup.parse(btn.asXml());
		
		return doc;
	}
	
	
	
	
	
	
	public static List<CrawerModel> test3(String url,int type) throws Exception {

		// 获取页面
		HtmlPage page = webClient.getPage(url);
		
		Document docu =  Jsoup.parse(page.asXml());
		
		Element overContent = docu.getElementById("search_nature_rg");
		
		Elements liContent = overContent.select("ul[class=bigimg cloth_shoplist]").select("li");
		
		List<CrawerModel> d = new ArrayList<CrawerModel>();

		
		// 关闭webClient
		webClient.close();
		
		return d;
	}

	public static List<HoneModel> fangtest(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		// 获取页面
			Pattern p = Pattern.compile("[\\d]+");

				HtmlPage page = webClient.getPage(url);
				List<HoneModel> d = new ArrayList<HoneModel>();
				HoneModel ds  = null;
				Document docu =  Jsoup.parse(page.asXml());
				Element s = docu.getElementsByClass("houseList").first();
				Elements liContent   = s.children();
				for (Element element : liContent) {
					ds = new HoneModel();
					Element dd = element.getElementsByTag("dd").first();
					if(dd!=null ){
						if( !"".equals(dd.select("p[class=mt12]").text())){
							if(dd.select("p[class=mt12]").text().indexOf("|")>-1){
								String[] home= dd.select("p[class=mt12]").text().split("\\|");
								for (String string : home) {
									if(string.indexOf("室")>-1){
										String[] h = string.split("室");
										ds.setNum(Integer.parseInt(h[0]));
										ds.setTi(Integer.parseInt(h[1].substring(0, 1)));
									}
									if(string.indexOf("层")>-1){
										Matcher m = p.matcher(string);
										if(m.find()){
											ds.setCeng(Integer.parseInt(m.group()));
										}
									}
									if(string.indexOf("年代")>-1){
										Matcher m = p.matcher(string);
										if(m.find()){
											ds.setNiandai(Integer.parseInt(m.group()));
										}
									}
									if(string.indexOf("向")>-1){
										System.err.println(string);
										if(string.equals("东向")){
											ds.setFangxiang(1);
										}
if(string.equals("西向")){
	ds.setFangxiang(2);
				
										}
if(string.equals("南向")){
	ds.setFangxiang(3);

}
if(string.equals("北向")){
	ds.setFangxiang(4);

}
if(string.equals("东南向")){
	ds.setFangxiang(5);

}if(string.equals("东北向")){
	ds.setFangxiang(6);

}if(string.equals("西南向")){
	ds.setFangxiang(7);

}if(string.equals("西北向")){
	ds.setFangxiang(8);

}if(string.equals("南北向")){
	ds.setFangxiang(9);

}
									}
								}
							}
						}

						String area = dd.getElementsByClass("area").first().getElementsByTag("p").first().html();
						area = area.substring(0, area.indexOf("�"));
						ds.setSart(Integer.parseInt(area));
						String price = dd.getElementsByClass("moreInfo").first().getElementsByTag("p").first().getElementsByTag("span").first().text(); 
						ds.setPrice(Integer.parseInt(price.split("\\.")[0]));
						d.add(ds);
					}
					
				}

				// 关闭webClient
				webClient.close();
				
				return d;
	}

/**
 * 
 * @Title: view
 * @Description: 经典(这里用一句话描述这个方法的作用)
 * @author: sly
 * @date: 2017年10月18日 上午11:35:29
 * @param url
 * @throws IOException 
 * @throws MalformedURLException 
 * @throws FailingHttpStatusCodeException 
 */
	public static void view(String urlP) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		
		// 获取页面
		String pricein = "北京市，天津市，上海市，重庆市，河北省，山西省，辽宁省，吉林省，黑龙江省，江苏省，浙江省，安徽省，福建省，江西省，山东省，河南省，湖北省，湖南省，广东省，海南省，四川省，贵州省，云南省，陕西省，甘肃省，青海省，台湾省，内蒙古自治区，广西壮族自治区，西藏自治区，宁夏回族自治区，新疆维吾尔自治区";
		
		String[] citys =pricein.split("，");
		
		List<SightHotPhoto> t = new ArrayList<SightHotPhoto>();

		for (String string : citys) {
			for(int i=0;i<15;i++){
				String url = "http://piao.qunar.com/ticket/list.htm?keyword="+URLEncoder.encode(string, "UTF-8")+"&region=&from=mpl_search_suggest&page="+i;
				HtmlPage page = webClient.getPage(url);
				try {
					Thread.sleep(1000*5);
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
					
					
//					String startPrice = sight_item_pop.select("span[class=sight_item_price]").first().getElementsByTag("em").first().text();//起步价
//					sightHotPhoto.setStartPrice(startPrice);
					
					if(sight_item_pop.hasClass("hot_num")){
						String hot_num = sight_item_pop.select("span[class=hot_num]").first().text();//销量
						sightHotPhoto.setAccount(Integer.parseInt(hot_num));
					}else{
						sightHotPhoto.setAccount(0);

					}

					t.add(sightHotPhoto);
				}
			}
		}
		System.err.println("抓取完毕，生成json文件");

				List<HotPhoto> gf = new  ArrayList<HotPhoto>();
				HotPhoto hotPhoto = null;
				for (SightHotPhoto s : t) {
					hotPhoto = new HotPhoto();
				    long time = new Date().getTime();
				    String urlPath ="http://api.map.baidu.com/geocoder/v2/?address="+s.getSightName()+"&output=json&ak=6eea93095ae93db2c77be9ac910ff311&time="+time;
			        JSONObject result = HttpRequestUtils.httpGetAnthor(urlPath);
			        if("0".equals(result.get("status").toString())){
				        JSONObject lngO = result.getJSONObject("result").getJSONObject("location");
				        String lng = lngO.get("lng").toString();
				        String lat = lngO.get("lat").toString();
				        hotPhoto.setLng(Double.valueOf(lng));
				        hotPhoto.setLat(Double.valueOf(lat));
				        hotPhoto.setCount(s.getAccount());
				        gf.add(hotPhoto);
			        }
				}
				System.err.println(new JSONObject().toJSON(gf));
				// 关闭webClient
				webClient.close();
	}
	
	
	
	/**
	 * 
	 * @Title: view 线程解决
	 * @Description: 经典(这里用一句话描述这个方法的作用)
	 * @author: sly
	 * @date: 2017年10月18日 上午11:35:29
	 * @param url
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
		public static void view2(String urlP) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException, ExecutionException {
			
			// 获取页面
			String pricein = "北京市，天津市，上海市，重庆市，河北省，山西省，辽宁省，吉林省，黑龙江省，江苏省，浙江省，安徽省，福建省，江西省，山东省，河南省，湖北省，湖南省，广东省，海南省，四川省，贵州省，云南省，陕西省，甘肃省，青海省，台湾省，内蒙古自治区，广西壮族自治区，西藏自治区，宁夏回族自治区，新疆维吾尔自治区";
			
			String[] citys =pricein.split("，");
			
	        ExecutorService exec=Executors.newCachedThreadPool();

			List<SightHotPhoto> t = new ArrayList<SightHotPhoto>();
			
            List<Future<List<SightHotPhoto>>> resultList = new ArrayList<Future<List<SightHotPhoto>>>(); 
			for (String string : citys) {
	            FutureTask<List<SightHotPhoto>> future = new FutureTask<List<SightHotPhoto>>(new CollectionCo(string));

				exec.execute(future);
				System.err.println(string+"结束");
				resultList.add(future);
			}	
			
			exec.shutdown();  
			//遍历任务的结果
            for (Future<List<SightHotPhoto>> fs : resultList) { 
                    try { 
                           t.addAll(fs.get());     //打印各个线程（任务）执行的结果
                    } catch (InterruptedException e) { 
                            e.printStackTrace(); 
                    } catch (ExecutionException e) { 
                            e.printStackTrace(); 
                    } finally { 
                            //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
                    	exec.shutdown(); 
                    } 
            } 
			
			System.err.println("抓取完毕，生成json文件");
					
			
					List<HotPhoto> gf = new  ArrayList<HotPhoto>();
					
					for (SightHotPhoto s : t) {
						
						List<HotPhoto> gf1 = exec.submit(new LocationCo(s)).get();
						gf.addAll(gf1);
					}
					System.err.println(new JSONObject().toJSON(gf));
					// 关闭webClient
		}
	
		
		
		
		public static List<HoneModel> fangtest1(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

					HtmlPage page = webClient.getPage(url);
					HoneModel ds  = null;
					Document docu =  Jsoup.parse(page.asXml());
					Element s = docu.getElementsByClass("searchcar-result pre clearfix").first();
					System.err.println(s.text());
					// 关闭webClient
					webClient.close();
					
					return null;
		}
}