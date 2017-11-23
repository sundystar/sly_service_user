package com.sly.facade.crawer.util;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import com.sly.facade.crawer.model.CrawerModel;

/*   
 *  合肥工业大学 管理学院 qianyang 1563178220@qq.com
 */
public class URLFecter {
    public static List<CrawerModel> URLParser (HttpClient client, String url) throws Exception {
        //用来接收解析的数据
        List<CrawerModel> JingdongData = new ArrayList<CrawerModel>();
        //获取网站响应的html，这里调用了HTTPUtils类
        HttpResponse response = HTTPUtils.getRawHtml(client, url);      
        //获取响应状态码
        int StatusCode = response.getStatusLine().getStatusCode();
        //如果状态响应码为200，则获取html实体内容或者json文件
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");    
            JingdongData = JdParse2.getData(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            //否则，消耗掉实体
            EntityUtils.consume(response.getEntity());
        }
        return JingdongData;
    }
}