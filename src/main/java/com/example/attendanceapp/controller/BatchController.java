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

import com.example.attendanceapp.dao.BatchDAO;
import com.example.attendanceapp.model.Batch;

@Controller
@RequestMapping("/batch")
public class BatchController {

	@PostMapping("/create")
	public ResponseEntity<Batch> createBatch(@RequestBody Batch b){
		b = BatchDAO.createBatch(b);
	    if(b.getId()!=0) {
	    	return new ResponseEntity<Batch>(b,HttpStatus.OK);
	    }
	    return new ResponseEntity<Batch>(b,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/{fid}/{status}")
	public ResponseEntity<ArrayList<Batch>> getActiveBatchList(@PathVariable("fid")int fid,@PathVariable("status") String status){
		ArrayList al = BatchDAO.getBatchList(fid, status);
	    return new ResponseEntity<ArrayList<Batch>>(al,HttpStatus.OK);
	}
}
