package com.nagarro.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class GetOpponent implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("Get New Opponent !");
	}

}
