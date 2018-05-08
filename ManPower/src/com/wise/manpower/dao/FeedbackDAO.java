package com.wise.manpower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wise.manpower.dto.Feedback;
import com.wise.manpower.dao.DBUtility;
public class FeedbackDAO {
	public int giveFeedback(Feedback feedback){
		final String QUERY ="insert into Feedback(Given_By_Id,Given_To_Id,Feedback_Type,Comments,Rating) values(?,?,?,?,?)";
		int status = 0;
		Connection connection = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 ,feedback.getGivenById());
			preparedStatement.setInt(2 ,feedback.getGivenToId());
			preparedStatement.setString(3 ,feedback.getFeedbackType());
			preparedStatement.setString(4 ,feedback.getComments());
			preparedStatement.setInt(5 ,feedback.getRating());
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;
	}
	
	public Feedback givenById(int givenToId){
		ResultSet resultSet = null;
		Feedback feedback = new Feedback();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Feedback where Given_To_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, givenToId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				feedback.setGivenById(resultSet.getInt(1));
				feedback.setGivenToId(resultSet.getInt(2));
				feedback.setFeedbackType(resultSet.getString(3));
				feedback.setComments(resultSet.getString(4));
				feedback.setRating(resultSet.getInt(5));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return feedback;
	}
}

