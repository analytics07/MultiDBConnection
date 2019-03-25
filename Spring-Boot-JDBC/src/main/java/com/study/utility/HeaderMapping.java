package com.study.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.study.exception.DataExceptionHandler;

@Component
@PropertySource("classpath:query.config")
public class HeaderMapping {
	private static final String path = "E:/Eclipse-Workbench/Spring-Boot-JDBC/src/main/resources/header_mapping.properties";

//	@Autowired
//	JdbcTemplate jdbc;

	@Autowired
	public Map<String, DriverManagerDataSource> dsMap;
	
	@Value("${select.query}")
	private String selectAll;

	Map<String, String> map;
	String ary[];
	String line = null;
	//jdbc.execute("insert into emp_profile(emp_id,emp_name,emp_email,emp_mobile,emp_city,emp_address,emp_dob,emp_doj,emp_probation,emp_career_level)values(101,'test','java@Test.com',9999,'new d','ggn','2019-02-22','2019-02-22','yes',8)");

	public Map<String, Object> getHeader() {
		//read cnn from json
		return new JdbcTemplate(dsMap.get("datasource1")).query(selectAll, new ResultSetExtractor<Map<String, Object>>() {
			@Override
			public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				int count = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= count; i++) {
					map.put(rs.getMetaData().getColumnName(i), rs.getMetaData().getColumnTypeName(i).toLowerCase());
				}
				try {
					BufferedReader br = new BufferedReader(new FileReader(path));
					try {
						while ((line = br.readLine()) != null) {
							ary = line.split("=");
							map.forEach((key, value) -> {
								if (ary[0].toLowerCase().equals(value)) {
									map.put(key, ary[1].toLowerCase());
								}
							});
						}
					} catch (IOException iox) {
						throw new DataExceptionHandler("error during reading data header", iox);
					}
				} catch (IOException eox) {
					throw new DataExceptionHandler("Header Mapping file not found", eox);
				}
				return map;
			}
		});
	}

}
