package com.sly.facade.crawer.util;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtils {
    private static Logger logger = Logger  
            .getLogger(HttpClientUtils.class); // 日志记录  
    
    
    public static JSONObject httpGetAnthor(String url){
    	 CloseableHttpClient httpClient =  HttpClientBuilder.create().build();
	    // get method
	    HttpGet httpGet = new HttpGet(url);
	    String temp="";
	    //response
	    HttpResponse response = null;  
	    try{
	        response = httpClient.execute(httpGet);
	        if (response.getStatusLine().getStatusCode() == 200) {
	    	    try{
	    	        HttpEntity entity = response.getEntity();
	    	        temp=EntityUtils.toString(entity,"UTF-8");
	    	    }catch (Exception e) {
	    	    	logger.error("get请求提交失败:" + url);  
	    	    } 
	        }
	    }catch (Exception e) {} 
	    
	    
	    return  getJson(temp);
    }
    
    
    /**
     * get 请求 
     */
    public static JSONObject httpGet(String url) {  
        JSONObject jsonResult = null;  
        CloseableHttpClient client = HttpClients.createDefault();  
        HttpGet request = new HttpGet(url);  
        RequestConfig requestConfig = RequestConfig.custom()
        		.setSocketTimeout(5000)
        		  .setConnectTimeout(5000)
        		  .setConnectionRequestTimeout(5000).build();  
        request.setConfig(requestConfig);  
        try {  
            CloseableHttpResponse response = client.execute(request);  
  
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                HttpEntity entity = response.getEntity();  
                String strResult = EntityUtils.toString(entity, "utf-8");  
                jsonResult = getJson(strResult);  
            } else {  
                logger.error("get请求提交失败:" + url);  
            }  
        } catch (IOException e) {  
            logger.error("get请求提交失败:" + url, e);  
        } finally {  
            request.releaseConnection();  
        }  
        return jsonResult;  
    }  
      
    private static JSONObject getJson(String strResult) {
        //查询手机归属地返回
        if(!strResult.startsWith("{")){
//            strResult = strResult.substring(9, strResult.lastIndexOf(")"));
            strResult = strResult.substring(strResult.indexOf("{"), strResult.lastIndexOf("}")+1);
        }
        return JSONObject.parseObject(strResult);
    }

    public static void main(String[] args) {  
        //TODO 测试代码  
        Long times = new Date().getTime();
        //"http://10.0.5.242:8080/hmc-web-api/ware/carPrice/initData?time="+times+"&source=101"
        //http://api.haomaiche.com/ware/ds/car/recommend/310000?cityCode=310000&mark=ask_modelType&modelId=12f994a1963f4baba823511a0b52d0cf&source=102&time=1482897368070
//        JSONObject result = httpGet("http://api.haomaiche.com/ware/ds/car/recommend/310000?cityCode=310000&mark=ask_modelType&modelId=12f994a1963f4baba823511a0b52d0cf&source=102&time=1482897368070");
//        if("1".equals(result.get("status"))){
//            System.out.println("note success.");
//        }
//        System.out.println(JsonUtil.showJson(result));
        String url = "http://api-dev.haomaiche.com/ware/carPrice/initData?source=102&time=1495610432137";
        JSONObject result = httpGetAnthor(url);
        System.out.println(result);
//        System.out.println(result.get("province"));
//        System.out.println(times);
    }  
}
