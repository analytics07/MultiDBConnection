package com.study.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.study.repository.DataRepository;


@Component
public class JsonReader {

	private static final String fileName = "E:/Eclipse-Workbench/Spring-Boot-JDBC/src/main/resources/input_json.config";
	
	@Autowired
	DataRepository repo;

	public Map<Integer,List<String>> readJson() {
		JSONParser parser = new JSONParser();
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("DETAIL_DATA");
            int i=0;
            for(Object o : array) {
            	i++;
            	JSONObject jo = (JSONObject) o;
            	map.put(i, repo.getData1(jo.get("CONNECTION").toString(), jo.get("QUERY").toString()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
	}
}
