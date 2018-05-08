package com.wise.manpower.dto;

public class Force {
	private int forceId;
	private int subServiceId;
	private int experience;
	private double costPerHour;
	private User user;
	private SubServiceType subServiceType;
	private Address address;
	private Work work;
	
	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Forces [forceId=" + forceId + ", subServiceId=" + subServiceId + ", experience=" + experience
				+ ", costPerHour=" + costPerHour + "]";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SubServiceType getSubServiceType() {
		return subServiceType;
	}

	public void setSubServiceType(SubServiceType subServiceType) {
		this.subServiceType = subServiceType;
	}

	public int getForceId() {
		return forceId;
	}
	public void setForceId(int forceId) {
		this.forceId = forceId;
	}
	public int getSubServiceId() {
		return subServiceId;
	}
	public void setSubServiceId(int subServiceId) {
		this.subServiceId = subServiceId;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public double getCostPerHour() {
		return costPerHour;
	}
	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}
	
	
}
