package com.sly.facade.crawer.parse;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import com.sly.facade.crawer.model.CrawerModel;
import com.sly.facade.crawer.util.URLFecter;

/*   
 *  合肥工业大学 管理学院 qianyang 1563178220@qq.com
 */
public class JdongMain {
    //log4j的是使用，不会的请看之前写的文章
    static final Log logger = LogFactory.getLog(JdongMain.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="http://book.dangdang.com/";
        //抓取的数据
        List<CrawerModel> bookdatas=URLFecter.URLParser(client, url);
        //循环输出抓取的数据
        for (CrawerModel jd:bookdatas) {
        	System.err.println(jd);
//            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        //将抓取的数据插入数据库
//        MYSQLControl.executeInsert(bookdatas);
    }
}