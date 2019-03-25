package com.study.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.study.utility.HeaderMapping;

@Repository
@PropertySource("classpath:query.config")
public class DataRepository {

	@Autowired
	HeaderMapping header;

	@Autowired
	public Map<String, DriverManagerDataSource> dsMap;

	@Value("${query}")
	private String sql;

	@Value("${select.query}")
	private String selectAll;

	public List<String> getData() {
		String cnn = "datasource2";// read value form json
		String query = "select role from login";
		JdbcTemplate jdbcTemplate = getTemplate(cnn);

		List<String> list = new ArrayList<String>();
		list.addAll(jdbcTemplate.queryForList(query, String.class));
		return list;
	}
	public List<String> getData1(String connection, String query) {
		JdbcTemplate jdbcTemplate = getTemplate(connection);

		List<String> list = new ArrayList<String>();
		list.addAll(jdbcTemplate.queryForList(query, String.class));
		return list;
	}

	private JdbcTemplate getTemplate(String cnn) {
		JdbcTemplate jdbcTemplate = null;
		jdbcTemplate = new JdbcTemplate(dsMap.get(cnn));
		return jdbcTemplate;
	}

	public Map<String, Object> getHeader() {
		return header.getHeader();
	}

}
