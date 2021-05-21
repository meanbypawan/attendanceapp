package com.example.attendanceapp.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendanceapp.dao.StudentDAO;
import com.example.attendanceapp.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
  
  @PostMapping("/add")	
  public ResponseEntity<Student> addStudent(@RequestBody Student s){
	  s = StudentDAO.addStudent(s);
	  if(s.getId()!=0)
		return new ResponseEntity<Student>(s,HttpStatus.OK);
	  return new ResponseEntity<Student>(s,HttpStatus.INTERNAL_SERVER_ERROR);
  }
  @GetMapping("/{batchId}")
  public ResponseEntity<ArrayList<Student>> getStudentList(@PathVariable int batchId){
	 ArrayList<Student>al = StudentDAO.getStudentList(batchId);
	 return new ResponseEntity<ArrayList<Student>>(al,HttpStatus.OK);
  }
}
