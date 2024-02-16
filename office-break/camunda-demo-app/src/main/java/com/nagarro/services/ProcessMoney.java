package com.nagarro.services;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ProcessMoney implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		try {
			// System.out.println("Order Placed Successfully !");
			throw new Exception("Error");
		} catch (Exception e) {
			System.out.println("Error Occured During Payment !");
			throw new BpmnError("InsufficientCash");
		}
	}
}
