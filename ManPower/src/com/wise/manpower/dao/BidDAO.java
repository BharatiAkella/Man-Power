package com.wise.manpower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.manpower.dto.Bid;

import com.wise.manpower.dao.DBUtility;

public class BidDAO {
	public int insert(Bid bid){
		final String QUERY ="insert into Bid(Force_Id,Bid_Amount,Work_Id,Open) values(?,?,?,?)";
		int status = 0;
		Connection connection = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 ,bid.getForceId());
			preparedStatement.setInt(2 ,bid.getBidAmount());
			preparedStatement.setInt(3 ,bid.getWorkId());
			preparedStatement.setString(4 ,"open");
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;
	}
	
	public Bid getBidByForce(int forceId){
		ResultSet resultSet = null;
		Bid bid = new Bid();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Bid where Force_id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, forceId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				bid.setForceId(resultSet.getInt(1));
				bid.setBidAmount(resultSet.getInt(2));
				bid.setWorkId(resultSet.getInt(3));
				bid.setOpen(resultSet.getString(4));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet, preparedStatement);
		}
		return bid;
	}
	public List<Bid> getAllBid() {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Bid";
		List<Bid> bidList =  null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery(QUERY);
			if(resultSet.next()){
				bidList = new ArrayList<Bid>();
			do{
				//while(resultSet.next()){
				Bid bid = new Bid();
				bid.setForceId(resultSet.getInt(1));
				bid.setBidAmount(resultSet.getInt(2));
				bid.setWorkId(resultSet.getInt(3));
				bid.setOpen(resultSet.getString(4));
				bidList.add(bid);
				
			}
			while(resultSet.next());
			
			}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtility.close(connection,resultSet, preparedStatement);
		}
	return bidList;
		
	}
	public List<Bid> getBidsByWorkId(int workId) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Bid where Work_Id=?";
		List<Bid> bidWorkList = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, workId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				bidWorkList = new ArrayList<Bid>();
			do{
				//while(resultSet.next()){
				Bid bid = new Bid();
				bid.setForceId(resultSet.getInt(1));
				bid.setBidAmount(resultSet.getInt(2));
				bid.setWorkId(resultSet.getInt(3));
				bid.setOpen(resultSet.getString(4));
				//System.out.print(resultSet.getInt(1));
				bid.setUser(new UserDAO().getByUserId(resultSet.getInt(1)));
				bidWorkList.add(bid);
				//System.out.println(bidWorkList);
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtility.close(connection,resultSet, preparedStatement);
		}
		return bidWorkList ;
		
		
	}
	public int bidUpdate(int workId){
		PreparedStatement preparedStatement = null;
		final String QUERY = "update bid set open = 'closed' where Work_Id = ?";
		Connection connection = null;
		ResultSet resultSet = null;
		connection = DBUtility.getConnection();
		//Work work = new Work();
		try{
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 ,workId);
			return preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return 0;
	}
	public static void main(String args[]){
		BidDAO u = new BidDAO();
		//User r = new User();
		//r = u.update(1,987654321);
		System.out.println(u.getBidsByWorkId(3));
	}

}
