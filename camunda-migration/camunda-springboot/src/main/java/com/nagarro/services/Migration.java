package com.nagarro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;


@Service
public class Migration {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ProcessEngine processEngine;

	public String startProcess() {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("order");
		String processInstanceId = processInstance.getId();
//		System.out.println(processInstanceId);

		return processInstanceId;
	}

	private String getSourceDefinitionId() {
	    ProcessDefinitionQuery query = processEngine.getRepositoryService()
	        .createProcessDefinitionQuery()
	        .processDefinitionKey("order")
	        .latestVersion();
	    
	    ProcessDefinition sourceDefinition = query.singleResult();
	    return sourceDefinition != null ? sourceDefinition.getId() : null;
	}

	private String getTargetDefinitionId() {
	    ProcessDefinitionQuery query = processEngine.getRepositoryService()
	        .createProcessDefinitionQuery()
	        .processDefinitionKey("order")
	        .processDefinitionVersion(2);
	    
	    ProcessDefinition targetDefinition = query.singleResult();
	    return targetDefinition != null ? targetDefinition.getId() : null;
	}


	public String sourceAndTargetId() {
		String sourceDefinitionId = getSourceDefinitionId();
		String targetDefinitionId = getTargetDefinitionId();
		
		System.out.println("sourceDefinitionId : "+sourceDefinitionId);
		System.out.println("targetDefinitionId : "+targetDefinitionId);
		return "";
	}

//	public boolean validateMigration(String processInstanceId) {
//        String sourceDefinitionId = getSourceDefinitionId();
//        String targetDefinitionId = getTargetDefinitionId();
//
//        if (sourceDefinitionId == null || targetDefinitionId == null) {
//            return false; // or handle the error as needed
//        }
//
//        ProcessInstanceMigrationValidationBuilder validation = runtimeService.createProcessInstanceMigrationValidation(processInstanceId)
//            .sourceProcessDefinitionId(sourceDefinitionId)
//            .targetProcessDefinitionId(targetDefinitionId);
//
//        MigrationPlanValidationReport validationReport = validation.validateMigration();
//
//        return validationReport.isValid();
//    }
}
