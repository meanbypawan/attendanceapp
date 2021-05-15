package com.example.attendanceapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.attendanceapp.model.Batch;
import com.example.attendanceapp.service.GetConnection;

public class BatchDAO {

  public static ArrayList<Batch> getBatchList(int fid,String status){
	Connection con = null;
	ArrayList<Batch> al = new ArrayList<>();
	try {
		con = GetConnection.getConnection();
		String sql = "select * from batch where fid = ? and status=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, fid);
		ps.setString(2, status);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int id = rs.getInt(1);
			String batchTime = rs.getString(2);
			String subject = rs.getString(3);
			String toTime = rs.getString(4);
			String fromTime = rs.getString(5);
			String startDate = rs.getString(6);
			String endDate = rs.getString(7);
		    Batch b = new Batch(id, batchTime, subject, toTime, fromTime, startDate, endDate, fid);
		    b.setStatus(status);
		    al.add(b);
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
	return al;
  }
  
  public static Batch createBatch(Batch b) {
	  Connection con = null;
	  try {
	    synchronized (BatchDAO.class) {
	  	  con = GetConnection.getConnection();
		  String sql = "select max(id) from batch";
		  PreparedStatement ps = con.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
	      int maxId = 0;
		  if(rs.next())
			  maxId = rs.getInt(1);
	      sql = "insert into batch(id,batch_time,batch_subject,to_time,from_time,start_date,end_date,fid,status) values(?,?,?,?,?,?,?,?,?)";
	      PreparedStatement ps2= con.prepareStatement(sql);
	      ps2.setInt(1, maxId+1);
	      ps2.setString(2, b.getBatchTime());
	      ps2.setString(3, b.getBatchSubject());
	      ps2.setString(4, b.getToTime());
	      ps2.setString(5, b.getFromTime());
	      ps2.setString(6, b.getStartDate());
	      ps2.setString(7, b.getEndDate());
	      ps2.setInt(8, b.getFid());
	      ps2.setString(9, "Active");
	      int x = ps2.executeUpdate();
	      if(x!=0)
	    	  b.setId(maxId+1);
	    }
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
	  return b;
  }
}
