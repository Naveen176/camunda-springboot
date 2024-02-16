package com.nagarro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.services.OfficeService;

@RestController
public class OfficeController {
	@Autowired
	OfficeService officeService;

	@RequestMapping(value = "/startOffice", method = RequestMethod.POST)
	public void start(@RequestBody String name) {
		officeService.startProcessInstance(name);
	}
}
