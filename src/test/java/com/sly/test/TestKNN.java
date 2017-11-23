package com.sly.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * KNN算法测试类
 * @author Rowen
 * @qq 443773264
 * @mail luowen3405@163.com
 * @blog blog.csdn.net/luowen3405
 * @data 2011.03.25
 */
public class TestKNN {
    
    /**
     * 从数据文件中读取数据
     * @param datas 存储数据的集合对象
     * @param path 数据文件的路径
     */
    public void read(List<List<Double>> datas, String path){
        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String data = br.readLine();
            List<Double> l = null;
            while (data != null) {
                String t[] = data.split("	");
                l = new ArrayList<Double>();
                
                for (int i = 0; i < t.length; i++) {
                    
                    l.add(Double.parseDouble(t[i]));
//                    System.out.println(l);
                }
                datas.add(l);
                data = br.readLine();
                
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 程序执行入口
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	InputStream stream = new FileInputStream("http://static.haomaiche.com/common/images/hcs/bdRecord/6525ca85a9bc43ccbc8fd30a888707ce.xlsx");
    	byte[] v = new byte[1024];
    	stream.read(v,0,1024);
    	
    	System.err.println(new String(v));
    	
    }
}