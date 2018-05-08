package com.wise.manpower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.wise.manpower.dto.User;
import com.wise.manpower.dao.DBUtility;

public class UserDAO {
	public int insert(User user){
		Connection connection = DBUtility.getConnection();
		final String QUERY ="insert into User(First_Name,Last_Name, Password,Email,Phone_Number) values(?,?,?,?,?)";
		int status = 0;
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setString(1 ,user.getFirstName());
			preparedStatement.setString(2 ,user.getLastName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getEmail());  
			preparedStatement.setString(5 , user.getPhone());
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;
	}
	
	public User get(String email){
		ResultSet resultSet = null;
		User user = new User();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from User where Email = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				user.setUserId(resultSet.getInt(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setPhone(resultSet.getString(6));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return user;
	}
	public User getByUserId(int userId){
		ResultSet resultSet = null;
		User user = new User();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from User where User_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				user.setUserId(resultSet.getInt(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setPhone(resultSet.getString(6));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return user;
	}
	public List<User> getAll() {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from User";
		List<User> userList = null;
		User user = new User();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery(QUERY);
		if(resultSet.next()){
				userList = new ArrayList<User>();
			do{
				//while(resultSet.next()){
				user.setUserId(resultSet.getInt(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setPhone(resultSet.getString(6));
				userList.add(user);
				
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return userList ;
		
		
	}
	public int updatePhoneNumber(int userId,int phoneNumber){
		//ResultSet resultSet = null;
		//User user = new User();
		PreparedStatement preparedStatement = null;
		int status = 0;
		final String QUERY = "update User set Phone_Number = ? where User_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, phoneNumber);
			preparedStatement.setInt(2 ,userId);
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;	
	}
	public static void main(String args[]){
		UserDAO u = new UserDAO();
		//User r = new User();
		//r = u.update(1,987654321);
		System.out.println(u.updatePhoneNumber(1,987654321));
	}
	}
