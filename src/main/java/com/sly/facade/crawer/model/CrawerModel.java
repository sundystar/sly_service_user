package com.sly.facade.crawer.model;

import java.io.Serializable;
import java.util.Date;

public class CrawerModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String bookId;
	
	private String img;
	
	private String title;
	
	private String author;
	
	private String price_num;//优惠价前缀
	
	private String price_tail;//优惠价后缀
	
	private String price_r_num;//原价前缀
	
	private String price_r_tail;//原价后缀

	private String href ;
	
	private String content;
	
	private int isHot;//热销  1热销 0不热销
	
	private int isNew;//新书 1新书 0不
	
	private Date createDate;
	
	private String press;//出版社
	
	private String pressDate;//出版时间
	
	private int type;//图书类型
	
	private int commonsNum;
	
	private String bookSource;//评分
	
	public String getBookSource() {
		return bookSource;
	}
	public void setBookSource(String bookSource) {
		this.bookSource = bookSource;
	}
	public int getCommonsNum() {
		return commonsNum;
	}
	public void setCommonsNum(int commonsNum) {
		this.commonsNum = commonsNum;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPressDate() {
		return pressDate;
	}
	public void setPressDate(String pressDate) {
		this.pressDate = pressDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice_num() {
		return price_num;
	}

	public void setPrice_num(String price_num) {
		this.price_num = price_num;
	}

	public String getPrice_tail() {
		return price_tail;
	}

	public void setPrice_tail(String price_tail) {
		this.price_tail = price_tail;
	}

	public String getPrice_r_num() {
		return price_r_num;
	}

	public void setPrice_r_num(String price_r_num) {
		this.price_r_num = price_r_num;
	}

	public String getPrice_r_tail() {
		return price_r_tail;
	}

	public void setPrice_r_tail(String price_r_tail) {
		this.price_r_tail = price_r_tail;
	}
}
