package com.example.attendanceapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.attendanceapp.dao.FacultyDAO;
import com.example.attendanceapp.model.Faculty;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
 
 @PostMapping("/save")	
 public ResponseEntity<Faculty> create(@RequestBody Faculty f){
	 f = FacultyDAO.create(f);
     return new ResponseEntity<Faculty>(f,HttpStatus.OK);
 }
 @GetMapping("/{id}")
 public ResponseEntity<Faculty> getFacultyById(@PathVariable("id") int id){
	 Faculty f = FacultyDAO.getFacultyById(id);
	 return new ResponseEntity<Faculty>(f,HttpStatus.OK);
 }
 @PostMapping("/update")
 public ResponseEntity<Faculty> update(@RequestBody Faculty f){
	 f = FacultyDAO.update(f);
     return new ResponseEntity<Faculty>(f,HttpStatus.OK);
 }
 
 @PostMapping("/login")
 public ResponseEntity<Faculty> login(@RequestBody Faculty f){
	 f = FacultyDAO.login(f);
	 if(f!=null)
		 return new ResponseEntity<Faculty>(f,HttpStatus.OK);
	 return new ResponseEntity<Faculty>(f,HttpStatus.NOT_FOUND);
 }
}
