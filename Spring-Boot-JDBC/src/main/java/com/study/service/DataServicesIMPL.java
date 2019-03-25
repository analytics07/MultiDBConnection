package com.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.exception.DataExceptionHandler;
import com.study.repository.DataRepository;
import com.study.utility.JsonReader;

@Service
public class DataServicesIMPL implements DataServices {

	@Autowired
	DataRepository repo;
	
	@Autowired
	JsonReader reader;

	@Override
	public List<String> getData() {
		// TODO Auto-generated method stub
		return repo.getData();
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		Map<String, Object> map = repo.getHeader();
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(map);
		}catch(Exception ex) {
			throw new DataExceptionHandler("error during map convert into json string", ex);
		}
		return json;
	}
	
	public List<String> showData(){
		Map<Integer,List<String>> map = reader.readJson();
		List<String>list = new ArrayList<String>();
		map.forEach((key,value)->{
			//System.out.println(value);
			list.add(value.toString());
		});
		return list;
	}

}
