package com.nagarro.services;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CamundaService {

	private final RuntimeService runtimeService;

    @Autowired
    public CamundaService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void startProcess(String user) {
        runtimeService
            .createMessageCorrelation("startEvent")
            .setVariable("name", user)
            .correlate();
    }

}