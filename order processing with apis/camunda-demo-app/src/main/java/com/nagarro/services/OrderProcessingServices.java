package com.nagarro.services;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingServices {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	public String startProcess() {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("OrderProcessing");
		String processInstanceId = processInstance.getId();
		return processInstanceId;
	}

	public ResponseEntity<String> reviewOrder(String orderApproval, String approval) {

		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processDefinitionKey("OrderProcessing").active().singleResult();
		if (processInstance != null) {
			runtimeService.setVariable(processInstance.getId(), orderApproval, approval);

			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

			if (task != null) {
				taskService.complete(task.getId());
				return ResponseEntity.ok("Approval is set and task completed successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No task found for the process instance.");
			}
		}
		return ResponseEntity.ok("No active process instance exists");
	}

	public ResponseEntity<String> stockAvailability(String availability) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processDefinitionKey("StockAvailability").active().singleResult();
		if (processInstance != null) {
			runtimeService.setVariable(processInstance.getId(), "available", availability);

			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

			if (task != null) {
				taskService.complete(task.getId());
				return ResponseEntity.ok("availability is set and task completed successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No task found for the process instance.");
			}
		}
		return ResponseEntity.ok("No active process instance exists");
	}

	public ResponseEntity<String> payment(String paymentMode) {
		String message = "";
		if (paymentMode == "cash") {
			message = "payment through cash";
		}
		if (paymentMode == "card") {
			message = "payment through card";
		}
		try {
			runtimeService.createMessageCorrelation(paymentMode).setVariable(paymentMode, message).correlate();
			return ResponseEntity.ok("payment Mode : " + paymentMode);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("payment failed ! " + e.getMessage());
		}
	}

	public ResponseEntity<String> approveShipment(String approval) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processDefinitionKey("FulfillOrder").active().singleResult();
		if (processInstance != null) {
			runtimeService.setVariable(processInstance.getId(), "approveShipment", approval);

			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

			if (task != null) {
				taskService.complete(task.getId());
				return ResponseEntity.ok("Shipment is set and approved successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Shipment found for the process instance.");
			}
		}
		return ResponseEntity.ok("No active Shipment instance exists");
	}

	public ResponseEntity<String> approvePickup(String approvePickup) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processDefinitionKey("FulfillOrder").active().singleResult();
		if (processInstance != null) {
			runtimeService.setVariable(processInstance.getId(), "approvePickup", approvePickup);

			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

			if (task != null) {
				taskService.complete(task.getId());
				return ResponseEntity.ok("pick up available : " + approvePickup);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Pick up found for the process instance.");
			}
		}
		return ResponseEntity.ok("No active Pickup instance exists");
	}

	public ResponseEntity<String> customerCare(String complaint) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processDefinitionKey("CustomerSupport").active().singleResult();
		if (processInstance != null) {
			runtimeService.setVariable(processInstance.getId(), "complaint", complaint);

			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

			if (task != null) {
				taskService.complete(task.getId());
				return ResponseEntity.ok("filed complaint : " + complaint);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No complaint up found for the process instance.");
			}
		}
		return ResponseEntity.ok("No complaints available");
	}

}
