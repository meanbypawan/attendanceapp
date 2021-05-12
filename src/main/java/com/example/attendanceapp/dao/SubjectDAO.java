package com.example.attendanceapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.attendanceapp.model.Subject;
import com.example.attendanceapp.service.GetConnection;

public class SubjectDAO {
  public static Subject saveSubject(Subject s) {
	  Connection con = null;
	  try {
		  synchronized (SubjectDAO.class) {
			String sql = "select max(id) from subject";
			con = GetConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int maxId = 0;
			if(rs.next())
				maxId = rs.getInt(1);
			sql = "insert into subject(id,subject_title) values(?,?)";
			PreparedStatement ps2 = con.prepareStatement(sql);
			ps2.setInt(1,maxId+1);
			ps2.setString(2, s.getSubjectTitle());
			int x = ps2.executeUpdate();
			if(x!=0)
				s.setId(maxId+1);
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
	  return s;
  }
  public static ArrayList<Subject> getSubjectList(){
	  ArrayList<Subject> al = new ArrayList<Subject>();
	  Connection con = null;
	  try {
		  con = GetConnection.getConnection();
		  String sql = "select * from subject";
		  PreparedStatement ps = con.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  while(rs.next()) {
			  int id = rs.getInt(1);
			  String subjectTitle = rs.getString(2);
		      Subject s = new Subject(id,subjectTitle);
		      al.add(s);
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
	  return al;
  }
}
