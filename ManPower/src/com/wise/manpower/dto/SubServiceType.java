package com.wise.manpower.dto;

import java.util.List;

public class SubServiceType {
	private int subServiceTypeId;
	private String name;
	private int serviceTypeId;
	private String active;
	private String icon;
	private List<Bid> bidList;
	
	public List<Bid> getBidList() {
		return bidList;
	}
	public void setBidList(List<Bid> bidList) {
		this.bidList = bidList;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getSubServiceTypeId() {
		return subServiceTypeId;
	}
	public void setSubServiceTypeId(int subServiceTypeId) {
		this.subServiceTypeId = subServiceTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "SubServiceType [subServiceTypeId=" + subServiceTypeId + ", name=" + name + ", serviceTypeId="
				+ serviceTypeId + ", active=" + active + ", icon=" + icon + "]";
	}
	
}
