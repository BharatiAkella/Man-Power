package com.wise.manpower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.manpower.dto.SubServiceType;
import com.wise.manpower.dao.DBUtility;

public class SubServiceTypeDAO {
	public int insert(SubServiceType subServiceType){
		final String QUERY = "insert into SubServiceType(Sub_Service_Type_Id,Name,Service_Type_Id,Icon,Open) values(?,?,?,?,?)";
		int status = 0;
		Connection connection = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,subServiceType.getSubServiceTypeId());
			preparedStatement.setString(2,subServiceType.getName());
			preparedStatement.setInt(3,subServiceType.getServiceTypeId());
			preparedStatement.setString(4,subServiceType.getIcon());
			preparedStatement.setString(5,subServiceType.getActive());
			status = preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;
	}
	public SubServiceType get(int subServiceTypeId) {

		ResultSet resultSet = null;
		SubServiceType subServiceType = new SubServiceType();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from SubServiceType where Sub_Service_Type_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, subServiceTypeId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				subServiceType.setSubServiceTypeId(resultSet.getInt(1));
				subServiceType.setName(resultSet.getString(2));
				subServiceType.setServiceTypeId(resultSet.getInt(3));
				subServiceType.setActive(resultSet.getString(4));
				subServiceType.setIcon(resultSet.getString(5));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return subServiceType;
	}
	public List<SubServiceType> getAll() {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from SubServiceType";
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery(QUERY);
		
			if(resultSet.next()){
				List<SubServiceType> subServiceList = new ArrayList<SubServiceType>();
			do{
				//while(resultSet.next()){
				SubServiceType subServiceType = new SubServiceType();
				subServiceType.setSubServiceTypeId(resultSet.getInt(1));
				subServiceType.setName(resultSet.getString(2));
				subServiceType.setServiceTypeId(resultSet.getInt(3));
				subServiceType.setIcon(resultSet.getString(4));
				subServiceType.setActive(resultSet.getString(5));
				
			}
			while(resultSet.next());
			return subServiceList ;
			}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
	return null;
		
		
	}
/*	public static void main(String args[]) {
		SubServiceTypeDAO s = new SubServiceTypeDAO();
	//	List<ServiceType> s = new ArrayList<ServiceType>();
		//S = s.getAll();
		System.out.println(s.getAll());
	}*/
	public List<SubServiceType> getAllSubServices(int serviceId){
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		List<SubServiceType> list = null;
		final String QUERY = "select * from SubServiceType where Service_Type_Id = ?";
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 , serviceId);
			resultSet = preparedStatement.executeQuery();
		
			if(resultSet.next()){
			 list = new ArrayList<SubServiceType>();
			do{
				//while(resultSet.next()){
				SubServiceType subServiceType = new SubServiceType();
				subServiceType.setSubServiceTypeId(resultSet.getInt(1));
				subServiceType.setName(resultSet.getString(2));
				subServiceType.setServiceTypeId(resultSet.getInt(3));
				subServiceType.setIcon(resultSet.getString(4));
				subServiceType.setActive(resultSet.getString(5));
				list.add(subServiceType);
				
			}
			while(resultSet.next());
	
			}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
	return list;
	}
}
