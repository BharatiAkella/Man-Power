package com.wise.manpower.dao;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.manpower.util.DateUtility;
import com.wise.manpower.dto.Bid;
import com.wise.manpower.dto.SubServiceType;
import com.wise.manpower.dto.User;
import com.wise.manpower.dto.Work;

import com.wise.manpower.dao.DBUtility;
public class WorkDAO {
	public int insert(Work work){
		final String QUERY ="insert into Work(Work_Id,Description,Sub_Service_Type_Id,User_Id,Open,Date) values(?,?,?,?,?,?)";
		int status = 0;
		Connection connection = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 ,work.getWorkId());
			preparedStatement.setString(2 ,work.getDescription());
			preparedStatement.setInt(3 ,work.getSubServiceTypeId());
			preparedStatement.setInt(4, work.getUserId());
			preparedStatement.setString(5,"YES");
			preparedStatement.setDate(6, DBUtility.convertUtilToSql(work.getDate()));
			status = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(connection, preparedStatement);
		}
		return status;
	}	
	public Work get(int workId){
		ResultSet resultSet = null;
		Work work = new Work();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from Work where Work_id = ?";
		Connection connection = DBUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, workId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				work.setWorkId(resultSet.getInt(1));
				work.setDescription(resultSet.getString(2));
				work.setSubServiceTypeId(resultSet.getInt(3));
				work.setUserId(resultSet.getInt(4));
				work.setOpen(resultSet.getString(5));
				work.setDate(resultSet.getDate(6));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return work;
	}
	public List<Work> getAllWorks() {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Work";
		List<Work> workList =  null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery(QUERY);
		
			if(resultSet.next()){
				workList = new ArrayList<Work>();
			do{
				Work work = new Work();
				work.setWorkId(resultSet.getInt(1));
				work.setDescription(resultSet.getString(2));
				work.setSubServiceTypeId(resultSet.getInt(3));
				work.setUserId(resultSet.getInt(4));
				work.setOpen(resultSet.getString(5));
				work.setDate(resultSet.getDate(6));
				workList.add(work);
			}
			while(resultSet.next());
		}
	   } catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
	return workList;
	}
	public List<Work> getWorkByUser(int userId) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Work where User_Id = ? ";
		List<Work> userWorksList = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
		
			if(resultSet.next()){
				userWorksList = new ArrayList<Work>();
			do{
				//while(resultSet.next()){
				Work work = new Work();
				work.setWorkId(resultSet.getInt(1));
				work.setDescription(resultSet.getString(2));
				work.setSubServiceType(new SubServiceTypeDAO().get(resultSet.getInt(3)));
				work.setUserId(resultSet.getInt(4));
				work.setUser(new UserDAO().getByUserId(userId));
				//work.setOpen(resultSet.getString(5));
				work.setDate(DateUtility.convertStringToUtil(resultSet.getString(6)));
				//System.out.println(work.getWorkId());
				//work.setBidsList(new BidDAO().getBidsByWorkId(work.getWorkId()));
				//List<Bid> bid = new BidDAO().getBidsByWorkId(work.getWorkId());
				//System.out.println(new BidDAO().getBidsByWorkId(work.getWorkId()));
				work.setBid(new BidDAO().getBidsByWorkId(work.getWorkId()));
				userWorksList.add(work);
				//System.out.println("hello"+userWorksList+"hello");
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return userWorksList ;
	}	
	public List<Work> getActiveWorks(int subServiceTypeId) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Work where Sub_Service_Type_Id=? and Open = 'Yes'";
		List<Work> activeWorkList = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, subServiceTypeId);
			resultSet = preparedStatement.executeQuery();		
			if(resultSet.next()){
				activeWorkList = new ArrayList<Work>();
			do{
				//while(resultSet.next()){
				Work work = new Work();
				work.setWorkId(resultSet.getInt(1));
				work.setUser(new UserDAO().getByUserId(resultSet.getInt(4)));
				work.setDescription(resultSet.getString(2));
				SubServiceType subServiceType =  new SubServiceType();
				subServiceType.setSubServiceTypeId(resultSet.getInt(3));
				work.setSubServiceType(subServiceType);	
				//System.out.println(resultSet.getInt(4));
				work.setBid(new BidDAO().getBidsByWorkId(resultSet.getInt(1)));
				//work.setOpen(resultSet.getString(5));
				//work.setDate(resultSet.getDate(6));
				//work.setAddress(new AddressDAO().get(resultSet.getInt(4)));
				activeWorkList.add(work);
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return activeWorkList ;
	}
	
	public List<Work> getWorksForce(int subServiceId) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Work where Sub_Service_Type_Id = ?";
		List<Work> workList = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1 ,subServiceId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				workList = new ArrayList<Work>();
			do{
				//while(resultSet.next()){
				Work work = new Work();
				work.setWorkId(resultSet.getInt(1));
				work.setUserId(resultSet.getInt(4));
				work.setUser(new UserDAO().getByUserId(resultSet.getInt(4)));
				work.setAddress(new AddressDAO().get(resultSet.getInt(4)));
				work.setDescription(resultSet.getString(2));						
				workList.add(work);		
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return workList;	
	}
	public int workUpdate(int workId){
		PreparedStatement preparedStatement = null;
		final String QUERY = "update Work set open = 'closed' where Work_Id = ?";
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
	public List<Work> getWorkByWorkId(int workId) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		connection = DBUtility.getConnection();
		final String QUERY = "select * from Work where Work_Id = ? ";
		List<Work> userWorksList = null;
		try {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, workId);
			resultSet = preparedStatement.executeQuery();
		
			if(resultSet.next()){
				userWorksList = new ArrayList<Work>();
			do{
				//while(resultSet.next()){
				Work work = new Work();
				work.setWorkId(workId);
				work.setDescription(resultSet.getString(2));
				work.setSubServiceType(new SubServiceTypeDAO().get(resultSet.getInt(3)));
				work.setUserId(resultSet.getInt(4));
				//User user = new User();
				//work.setUser(new UserDAO().getByUserId(userId));
				work.setOpen(resultSet.getString(5));
				work.setDate(DateUtility.convertStringToUtil(resultSet.getString(6)));
				//System.out.println(work.getWorkId());
				//work.setBidsList(new BidDAO().getBidsByWorkId(work.getWorkId()));
				//List<Bid> bid = new BidDAO().getBidsByWorkId(work.getWorkId());
				//System.out.println(new BidDAO().getBidsByWorkId(work.getWorkId()));
				work.setBid(new BidDAO().getBidsByWorkId(work.getWorkId()));
				userWorksList.add(work);
				System.out.println("hello"+userWorksList+"hello");
			}
			while(resultSet.next());
			}
	   } catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.close(resultSet,connection, preparedStatement);
		}
		return userWorksList ;
	}	
	public static void main(String args[]) {
		WorkDAO s = new WorkDAO();
		System.out.println(s.workUpdate(1));
	}
}
