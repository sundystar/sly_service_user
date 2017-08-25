package com.sly.facade.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * <b>Description：</b> 启动Dubbo服务用的MainClass.<br/>
 * <b>ClassName：</b> DubboProvider <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月12日 下午3:59:10 <br/>
 * <b>@version: </b>  <br/>
 */ 
public class DubboProvider {
				

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
			System.err.println("dubbo 启动成功。。。。。。"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (DubboProvider.class) {
			while (true) {
				try {
					DubboProvider.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		}
	}
}