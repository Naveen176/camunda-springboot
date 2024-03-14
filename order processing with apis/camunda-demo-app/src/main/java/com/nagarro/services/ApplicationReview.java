package com.nagarro.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReview implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		//execution.setVariable("applicationstatus", "valid");
		System.out.println("application is verified ! ");
	}

}