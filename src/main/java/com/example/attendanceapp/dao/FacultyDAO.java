package com.example.attendanceapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.attendanceapp.model.Faculty;
import com.example.attendanceapp.service.GetConnection;

public class FacultyDAO {
	
  public static Faculty login(Faculty f) {
	  Connection con = null;
	  Faculty faculty = null;
	  try {
		  con  = GetConnection.getConnection();
		  String sql = "select * from faculty where mobile = ? and password = ?";
		  PreparedStatement ps = con.prepareStatement(sql);
		  ps.setString(1, f.getMobile());
		  ps.setString(2, f.getPassword());
		  ResultSet rs = ps.executeQuery();
		  if(rs.next()) {
			  faculty = new Faculty();
			  faculty.setId(rs.getInt(1));
			  faculty.setName(rs.getString(2));
			  faculty.setMobile(rs.getString(3));
			  faculty.setPassword(rs.getString(4));
		  }
	  }
	  catch (Exception e) {
		// TODO: handle exception
	  }
	  finally {
		try {
			con.close();
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	  return faculty;
  }
  public static Faculty update(Faculty f) {
	  Connection con = null;
	  try {
		  con = GetConnection.getConnection();
		  String sql = "update faculty set name=?,mobile=?,password=? where id=?";
		  PreparedStatement ps = con.prepareStatement(sql);
		  ps.setString(1, f.getName());
		  ps.setString(2, f.getMobile());
		  ps.setString(3, f.getPassword());
		  ps.setInt(4, f.getId());
		  int x = ps.executeUpdate();
		    
	  }
	  catch (Exception e) {
		 e.printStackTrace();
	  }
	  finally {
		  try {
			  con.close();
		  }
		  catch (Exception e) {
			e.printStackTrace();
		  }
	  }
	  return f;
  }
  public static Faculty create(Faculty f) {
	Connection con = null;
	 int maxId = 0;
		
	try {
		con = GetConnection.getConnection();
		synchronized (Faculty.class) {
		  String sql = "select max(id) from faculty";
		  PreparedStatement ps = con.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  if(rs.next())
			  maxId  = rs.getInt(1);
		}
		String sql = "insert into faculty(id,name,mobile,password) values(?,?,?,?)";
		PreparedStatement ps2 = con.prepareStatement(sql);
		ps2.setInt(1, maxId+1);
		ps2.setString(2, f.getName());
		ps2.setString(3, f.getMobile());
		ps2.setString(4, f.getPassword());
		int x = ps2.executeUpdate();
		if(x!=0)
			f.setId(maxId+1);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	return f;
  }
  public static Faculty getFacultyById(int id) {
	  Connection con = null;
	  Faculty f = null;
	  try {
		con = GetConnection.getConnection();  
		String sql = "select * from faculty where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    if(rs.next()) {
	    	String name = rs.getString(2);
	    	String mobile = rs.getString(3);
	    	String password = rs.getString(4);
	    	f = new Faculty(id,name,mobile,password);
	    }
	  }
	  catch (Exception e) {
		// TODO: handle exception
	  }
	  finally {
		  try {
			  con.close();
		  }
		  catch (Exception e) {
			e.printStackTrace();
		  }
	  }
	  return f;
  }
  
}
