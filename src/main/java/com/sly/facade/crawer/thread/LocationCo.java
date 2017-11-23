package com.sly.facade.crawer.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONObject;
import com.sly.facade.crawer.model.HotPhoto;
import com.sly.facade.crawer.model.SightHotPhoto;
import com.sly.facade.crawer.util.HttpRequestUtils;

public class LocationCo implements Callable<List<HotPhoto>> {

	private SightHotPhoto city;  
	
	
    public LocationCo(SightHotPhoto city) {  
        this.city=city;  
        
    }
	
	public List<HotPhoto> call() throws Exception {
		
		List<HotPhoto> gf = new  ArrayList<HotPhoto>();
		HotPhoto hotPhoto = new HotPhoto();
		    long time = new Date().getTime();
		    String urlPath ="http://api.map.baidu.com/geocoder/v2/?address="+city.getSightName()+"&output=json&ak=6eea93095ae93db2c77be9ac910ff311&time="+time;
	        JSONObject result = HttpRequestUtils.httpGetAnthor(urlPath);
	        if("0".equals(result.get("status").toString())){
		        JSONObject lngO = result.getJSONObject("result").getJSONObject("location");
		        String lng = lngO.get("lng").toString();
		        String lat = lngO.get("lat").toString();
		        hotPhoto.setLng(Double.valueOf(lng));
		        hotPhoto.setLat(Double.valueOf(lat));
		        hotPhoto.setCount(city.getAccount());
		        gf.add(hotPhoto);
	        }
		
		return gf;
	}

}
