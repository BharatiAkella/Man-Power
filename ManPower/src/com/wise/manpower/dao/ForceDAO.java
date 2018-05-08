package com.wise.manpower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.wise.manpower.dto.Force;
import com.wise.manpower.dao.DBUtility;

public class ForceDAO {
	public int insert(Force force){
		final String QUERY ="insert into Forces (Force_Id,Sub_Service_Type_Id,Experience,Cost_Per_Hour) values(?,?,?,?)";
		int status = 0;
		Connection connection = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 ,force.getForceId());
			preparedStatement.setInt(2 ,force.getSubServiceId());
			preparedStatement.setInt(3 ,force.getExperience());
			preparedStatement.setDouble(4 ,force.getCostPerHour());
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;
	}
	
	public Force get(int forceId){
		ResultSet resultSet = null;
		Force force = new Force();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Forces where Force_id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, forceId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				force.setForceId(resultSet.getInt(1));
				force.setSubServiceId(resultSet.getInt(2));
				force.setExperience(resultSet.getInt(3));
				force.setCostPerHour(resultSet.getInt(4));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet, preparedStatement);
		}
		return force;
	}
	public List<Force> getAll() {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Forces";
		List<Force> forceList = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery(QUERY);
			if(resultSet.next()){
				forceList = new ArrayList<Force>();
			do{
				//while(resultSet.next()){
				Force force = new Force();
				force.setForceId(resultSet.getInt(1));
				force.setSubServiceId(resultSet.getInt(2));
				force.setExperience(resultSet.getInt(3));
				force.setCostPerHour(resultSet.getInt(4));
				
				forceList.add(force);
				
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		
		return forceList;
	}
	public int updateCostPerHour(int forceId,int costPerHour){
		//ResultSet resultSet = null;
		//User user = new User();
		PreparedStatement preparedStatement = null;
		int status = 0;
		final String QUERY = "update Forces set Cost_Per_Hour = ? where Force_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, costPerHour);
			preparedStatement.setInt(2 ,forceId);
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;	
	}
	public int updateExperience(int forceId,int experience){
		//ResultSet resultSet = null;
		//User user = new User();
		PreparedStatement preparedStatement = null;
		int status = 0;
		final String QUERY = "update Forces set Experience = ? where Force_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, experience);
			preparedStatement.setInt(2 ,forceId);
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;	
	}
	public static void main(String args[]){
		ForceDAO f = new ForceDAO();
		//User r = new User();
		//r = u.update(1,987654321);
		System.out.println(f.updateExperience(1,9));
	}

	public List<Force> getWorksForce(int forceId) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Forces where Force_Id = ?";
		List<Force> forceList = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 ,forceId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				forceList = new ArrayList<Force>();
			do{
				//while(resultSet.next()){
				Force force = new Force();
				//force.setForceId(resultSet.getInt(1));
				force.setUser(new UserDAO().getByUserId(resultSet.getInt(1)));
				force.setAddress(new AddressDAO().get(resultSet.getInt(1)));
				force.setWork(new WorkDAO().get(resultSet.getInt(2)));
								
				forceList.add(force);
				
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return forceList;
		
	}
}
