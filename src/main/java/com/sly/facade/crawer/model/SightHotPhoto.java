package com.sly.facade.crawer.model;

import java.io.Serializable;

public class SightHotPhoto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sightName;
	
	private String level;
	
	private String area;
	
	private String startPrice;
	
	private int account;
	
	private Double hot;
	
	private String detailsAddress;
	
	private String slogan;
	
	private String network;

	public String getSightName() {
		return sightName;
	}

	public void setSightName(String sightName) {
		this.sightName = sightName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public Double getHot() {
		return hot;
	}

	public void setHot(Double hot) {
		this.hot = hot;
	}

	public String getDetailsAddress() {
		return detailsAddress;
	}

	public void setDetailsAddress(String detailsAddress) {
		this.detailsAddress = detailsAddress;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}
	
	
	
	
}
