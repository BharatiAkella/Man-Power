package com.wise.manpower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wise.manpower.dto.Address;
import com.wise.manpower.dao.DBUtility;

public class AddressDAO {
	public int insert(Address address){
		final String QUERY ="insert into Address(User_Id,Door_Number,Street,City,State,Postal_Code,Country) values(?,?,?,?,?,?,?)";
		int status = 0;
		PreparedStatement preparedStatement = null;
		Connection connection = DBUtility.getConnection();
		try{
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 ,address.getUserId());
			preparedStatement.setString(2 ,address.getDoorNumber());
			preparedStatement.setString(3 ,address.getStreet());
			preparedStatement.setString(4 ,address.getCity());
			preparedStatement.setString(5 ,address.getState());
			preparedStatement.setInt(6, address.getPostalCode());
			preparedStatement.setString(7, address.getCountry());
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;
	}
	
	public Address get(int userId){
		ResultSet resultSet = null;
		Address address = new Address();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Address where User_id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				address.setUserId(resultSet.getInt(1));
				address.setDoorNumber(resultSet.getString(2));
				address.setStreet(resultSet.getString(3));
				address.setCity(resultSet.getString(4));
				address.setState(resultSet.getString(5));
				address.setPostalCode(resultSet.getInt(6));
				address.setCountry(resultSet.getString(7));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet, preparedStatement);
		}
		return address;
	}
	public int updateAddress(int userId,int doorNumber,String street,String city,String state,int postalCode,String country ){
		//ResultSet resultSet = null;
		//User user = new User();
		PreparedStatement preparedStatement = null;
		int status = 0;
		final String QUERY = "update Address set Door_Number = ?,Street = ?,City = ?,State = ?,Postal_Code = ?,Country = ? where User_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, doorNumber );
			preparedStatement.setString(2 ,street);
			preparedStatement.setString(3 ,city);
			preparedStatement.setString(4 ,state);
			preparedStatement.setInt(5 ,postalCode);
			preparedStatement.setString(6 ,country);
			preparedStatement.setInt(7 ,userId);
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;	
	}
	public static void main(String args[]){
		AddressDAO u = new AddressDAO();
		//User r = new User();
		//r = u.update(1,987654321);
		System.out.println(u.updateAddress(1,9,"street","city","state",99,"country"));
	}

	}

