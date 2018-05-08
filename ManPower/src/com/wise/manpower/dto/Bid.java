package com.wise.manpower.dto;

public class Bid {
	private int forceId;
	
	private int bidAmount;
	private int workId;
	private String open;
	private User user;
	private Work work;
	
	@Override
	public String toString() {
		return "Bid [forceId=" + forceId + ", bidAmount=" + bidAmount + ", workId=" + workId + ", open=" + open
				+ ", user=" + user + ", work=" + work + "]";
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public int getForceId() {
		return forceId;
	}
	public void setForceId(int forceId) {
		this.forceId = forceId;
	}
	public int getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}

}
