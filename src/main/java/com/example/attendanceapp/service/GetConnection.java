package com.example.attendanceapp.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
  private static Connection con = null;
  public static Connection getConnection() {
	  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://mysql-30877-0.cloudclusters.net:30877/attendanceapp","root","root@root");
	  }
	  catch (Exception e) {
		e.printStackTrace();
	  }
	  return con;
  }
}
