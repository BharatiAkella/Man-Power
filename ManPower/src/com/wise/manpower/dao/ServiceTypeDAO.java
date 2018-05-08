package com.wise.manpower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.manpower.dto.ServiceType;
import com.wise.manpower.dao.DBUtility;

public class ServiceTypeDAO {
	public int insert(ServiceType serviceType) {
		final String QUERY = "insert into ServiceType(Service_Type_Id,Name,Icon,Active) values(?,?,?,?)";
		int status = 0;
		Connection connection = DBUtility.getConnection();
		PreparedStatement preparedStatement  = null;
		try {
			 preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,serviceType.getServiceTypeId());
			preparedStatement.setString(2,serviceType.getName());
			preparedStatement.setString(3,serviceType.getIcon());
			preparedStatement.setString(4,serviceType.getActive());
			status = preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;
	}
	public ServiceType get(int ServiceTypeId) {
		ResultSet resultSet = null;
		ServiceType serviceType = new ServiceType();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from ServiceType where Service_Type_Id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, ServiceTypeId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				serviceType.setServiceTypeId(resultSet.getInt(1));
				serviceType.setName(resultSet.getString(2));
				serviceType.setIcon(resultSet.getString(3));
				serviceType.setActive(resultSet.getString(4));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return serviceType;
	}
	public List<ServiceType> getAll() {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from ServiceType";
		List<ServiceType> serviceList = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery(QUERY);
		
			if(resultSet.next()){
				serviceList = new ArrayList<ServiceType>();
			do{
				//while(resultSet.next()){
				ServiceType serviceType = new ServiceType();
				serviceType.setServiceTypeId(resultSet.getInt(1));
				serviceType.setName(resultSet.getString(2));
				serviceType.setIcon(resultSet.getString(3));
				serviceType.setActive(resultSet.getString(4));
				serviceList.add(serviceType);
				
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return serviceList ;
		
		
	}
	public static void main(String args[]) {
		ServiceTypeDAO s = new ServiceTypeDAO();
	//	List<ServiceType> s = new ArrayList<ServiceType>();
		//S = s.getAll();
		System.out.println(s.getAll());
	}
	}

