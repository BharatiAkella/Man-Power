package com.wise.manpower.dto;

import java.util.List;

public class ServiceType {
	private int serviceTypeId;
	private String name;
	private String icon;
	private String active;
	private List<SubServiceType> subServiceType;

	
	public int getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public List<SubServiceType> getSubServiceType() {
		return subServiceType;
	}
	public void setSubServiceType(List<SubServiceType> subServiceType) {
		this.subServiceType = subServiceType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "ServiceType [serviceTypeId=" + serviceTypeId + ", name=" + name + ", icon=" + icon + ", active="
				+ active + "]";
	}
	
}
