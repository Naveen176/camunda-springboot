package com.nagarro.services;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {

	@Autowired
	
    private RuntimeService runtimeService;

    public void startProcessInstance(String name) {
//    	System.out.println("Welcome "+name);
    	runtimeService.startProcessInstanceByKey("OfficeBreak");
    }
}
