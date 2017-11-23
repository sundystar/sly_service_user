package com.sly.facade.mail;
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.util.Properties;  
import javax.mail.BodyPart;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.Multipart;  
import javax.mail.Part;  
import javax.mail.Session;  
import javax.mail.Store;  
public class ParserMail {  
 /** 
  * 获取邮件内容 
  * @param args 
  * @throws Exception  
  */  
 private static String host="pop3.163.com";  
   
   
 public static void main(String[] args) throws Exception {  
  Properties props=new Properties();  
  //设置邮件接收协议为pop3  
  props.setProperty("mail.store.protocol", "pop3");  
  props.setProperty("mail.pop3.host", host);  
    
  Session session = Session.getInstance(props);  
  Store store = session.getStore("pop3");  
  //连接要获取数据的邮箱 主机+用户名+密码  
  store.connect(host, "13651621194@163.com", "sly123456");  
  Folder folder = store.getFolder("inbox");  
  //设置邮件可读可写  
  folder.open(Folder.READ_WRITE);  
    
  Message[] messages = folder.getMessages();  
    
  for (int i = 0; i < messages.length; i++) {  

   //如果只是纯文本文件情况  
   
   System.err.println( messages[i].getContent());
  }  
  folder.close(true);  
  store.close();  
 }
 }  
