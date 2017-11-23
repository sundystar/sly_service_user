package com.sly.facade.crawer.util;
import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sly.facade.crawer.model.CrawerModel;

public class ParseHtml {
	
	public static void getContent(CrawerModel crawerModel,WebClient webClient  ) {
			//获取页面
			try{
						HtmlPage page = webClient.getPage(crawerModel.getHref());
						
						HtmlElement btn = page.getDocumentElement();
				
						Document doc = Jsoup.parse(page.asXml());
						
						//
						Element desc=doc.select("div[class=product_main clearfix]").select("div[class=show_info]").first();
						Elements pree = desc.select("div[class=messbox_info]").select("span");
						
						Element num = desc.select("div[class=messbox_info]").select("div[class=pinglun]").select("a[id=comm_num_down]").get(0);
						
						crawerModel.setCommonsNum(Integer.parseInt(num.text()));
						crawerModel.setPress(pree.get(1).text()); 
						crawerModel.setPressDate(pree.get(2).text());
						
						Element r = desc.getElementsByTag("h2").first();
						String result = r.text();
						if(result==null || "".equals(result)){
							crawerModel.setContent("");
						}else{
							crawerModel.setContent(result);
						}
						
						Element source = desc.select("div[class=messbox_info]").select("div[class=pinglun]").select("span[class=star]").get(0);
						if(source.attr("style")==null || "".equals(source.attr("style"))){
							crawerModel.setBookSource("0");
						}else{
							String t =  source.attr("style").split(":")[1];
							crawerModel.setBookSource(t);
						}
						
			}catch(Exception e){
				
			}
		}
	}
