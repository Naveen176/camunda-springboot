package com.nagarro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.services.OrderProcessingServices;

@RestController
@RequestMapping("/api/custom")
public class OrderProcessing {
	
	@Autowired
	OrderProcessingServices orderProcessing;
	
	@PostMapping("/start-process")
	ResponseEntity<String> startProcess() {
		String processId = orderProcessing.startProcess();
		return ResponseEntity.ok("process started with : "+processId);
	}
	
	@PostMapping("/review-order")
	public ResponseEntity<String> reviewOrder(@RequestBody String approval) {
	    return orderProcessing.reviewOrder("Orderapproval", approval);
	}

	@PostMapping("/stock-availability")
	public ResponseEntity<String> checkStockAvailability(@RequestBody String availability){
		return orderProcessing.stockAvailability(availability);
	}
	
	@PostMapping("/payment-method")
	public ResponseEntity<String> payment(@RequestBody String paymentType){
		return orderProcessing.payment(paymentType);
	}
	
	@PostMapping("/approve-shipment")
	public ResponseEntity<String> shipment(@RequestBody String approval){
		return orderProcessing.approveShipment(approval);
	}
	
	@PostMapping("/approve-pickup")
	public ResponseEntity<String> pickup(@RequestBody String approvePickup){
		return orderProcessing.approvePickup(approvePickup);
	}
	
	@PostMapping("/customer-care")
	public ResponseEntity<String> customerService(@RequestBody String complaint){
		return orderProcessing.customerCare(complaint);
	}
}