package com.nagarro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.services.Migration;

@RestController
public class ApiController {

	@Autowired
	Migration order;
	
	@PostMapping("/start")
	ResponseEntity<String> startProcess() {
		String processId = order.startProcess();
		return ResponseEntity.ok("process started with : "+processId);
	}
	
	@GetMapping("/processId")
	ResponseEntity<String> getProcessId(){
		order.sourceAndTargetId();
		return ResponseEntity.ok("started ");
	}
}
