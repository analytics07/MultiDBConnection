package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.service.DataServicesIMPL;
import com.study.utility.JsonReader;

@RestController
@RequestMapping(value="/data")
public class AppController {
	
	@Autowired
	private DataServicesIMPL service;
	
	@Autowired
	JsonReader reader;
	
	@GetMapping
	public List<String> getData() {
		return service.getData();
	}
	
	@GetMapping(path="/header")
	public String allData() {
		reader.readJson();
		return service.getHeader();
	}
	
	@GetMapping(path="/show")
	public List<String> show(){
		return service.showData();
	}

}
