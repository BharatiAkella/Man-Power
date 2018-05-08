package com.wise.manpower.dto;

public class Feedback {
	private int givenById;
	private int givenToId;
	private String feedbackType;
	private String comments;
	private int rating;
	public int getGivenById() {
		return givenById;
	}
	public void setGivenById(int givenById) {
		this.givenById = givenById;
	}
	public int getGivenToId() {
		return givenToId;
	}
	public void setGivenToId(int givenToId) {
		this.givenToId = givenToId;
	}
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Feedback [givenById=" + givenById + ", givenToId=" + givenToId + ", feedbackType=" + feedbackType
				+ ", comments=" + comments + ", rating=" + rating + "]";
	}
	
}
