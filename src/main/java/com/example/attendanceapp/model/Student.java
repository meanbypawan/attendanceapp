package com.example.attendanceapp.model;

public class Student {
	private int id;
	private String name;
	private String mobile;
	private String status;
	private int batchId;

	public Student(int id, String name, String mobile, String status, int batchId) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.status = status;
		this.batchId = batchId;
	}

	public Student() {
	}

	public Student(String name, String mobile, String status, int batchId) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.status = status;
		this.batchId = batchId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

}
