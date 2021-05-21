package com.example.attendanceapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.attendanceapp.model.Student;
import com.example.attendanceapp.service.GetConnection;

public class StudentDAO {

	public static ArrayList<Student> getStudentList(int batchId){
		Connection con = null;
		ArrayList<Student> al = new ArrayList<>();
		try {
			con = GetConnection.getConnection();
			String sql = "select * from student where batchid = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, batchId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String mobile = rs.getString(3);
				String status = rs.getString(4);
			    Student student = new Student(id, name, mobile, status, batchId);
			    al.add(student);
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
	public static Student addStudent(Student s) {
		Connection con = null;
		try {
		  int maxId = 0;
		  synchronized (StudentDAO.class) {
			 con = GetConnection.getConnection(); 
			 String sql = "select max(id) from student";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 maxId = rs.getInt(1);
			
		     sql = "insert into student(id,name,mobile,status,batchid) values(?,?,?,?,?)";
		     PreparedStatement ps1 = con.prepareStatement(sql);
		     ps1.setInt(1, maxId+1);
		     ps1.setString(2, s.getName());
		     ps1.setString(3, s.getMobile());
		     ps1.setString(4, s.getStatus());
		     ps1.setInt(5, s.getBatchId());
		     int x = ps1.executeUpdate();
		     if(x!=0)
		    	 s.setId(maxId+1);
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
		return s;
	}
}
