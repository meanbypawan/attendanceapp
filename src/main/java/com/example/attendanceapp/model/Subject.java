package com.example.attendanceapp.model;

public class Subject {
	private int id;
	private String subjectTitle;

	public Subject(int id, String subjectTitle) {
		super();
		this.id = id;
		this.subjectTitle = subjectTitle;
	}

	public Subject() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

}
