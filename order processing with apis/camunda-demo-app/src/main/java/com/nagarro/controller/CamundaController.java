package com.nagarro.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Ticket;
import com.nagarro.services.CamundaService;

@RestController
public class CamundaController {

	@Autowired
	CamundaService camunda_service;

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public void start(@RequestBody String name) {
		camunda_service.startProcess(name);
	}

	@RequestMapping(value = "/welcome/{name}", method = RequestMethod.GET)
	public String getConnector(@PathVariable String name) {
		return "Welcome " + name + "! Book your tickets."+" Maximum tickets availed by one person is 5";
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public int book(@RequestBody Ticket booking) {
		int max_ticket = 5;
		String requested_movie = booking.getMovie().toLowerCase();
		
		List<String> movies = new ArrayList<>();
		movies.add("inception");
		movies.add("kalki");
		movies.add("godfather");
		movies.add("openhiemer");
		
		if(booking.getCount()>max_ticket) {
			return 0;
		}
		
		if (!movies.contains(requested_movie)) {
			return 0;
		}
		return booking.getCount() * 1500;
	}
}
