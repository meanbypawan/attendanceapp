package com.example.attendanceapp.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendanceapp.dao.SubjectDAO;
import com.example.attendanceapp.model.Subject;

@Controller
@RequestMapping("/subject")
public class SubjectController {
  @GetMapping("/list")	
  public ResponseEntity<ArrayList<Subject>> getSubjectList() {
	  ArrayList<Subject>al = SubjectDAO.getSubjectList();
      return new ResponseEntity<ArrayList<Subject>>(al,HttpStatus.OK);
  }
  
  @PostMapping("/save")
  public ResponseEntity<Subject> saveSubject(@RequestBody Subject s){
	  s = SubjectDAO.saveSubject(s);
      return new ResponseEntity<Subject>(s,HttpStatus.OK);
  }
}
