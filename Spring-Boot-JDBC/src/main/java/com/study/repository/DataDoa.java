package com.study.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

//@Repository
//@Transactional
public class DataDoa {//extends JdbcDaoSupport {

//	@Autowired
//	public DataDoa(DataSource dataSource) {
//		this.setDataSource(dataSource);
//	}
	
//	public List<String> queryDS1(){
//		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
//		String sql= "select * from emp_profile";
//		List<String> list = this.getJdbcTemplate().queryForList(sql,String.class);
//		return list;
//	}
//	public List<String> queryDS2(){
//		String sql= "select * from login";
//		List<String> list = this.getJdbcTemplate().queryForList(sql,String.class);
//		return list;
//	}
//	public List<String> queryDS3(){
//		String sql= "select * from product";
//		List<String> list = this.getJdbcTemplate().queryForList(sql,String.class);
//		return list;
//	}
//	public List<String> queryDS4(){
//		String sql= "select * from employee";
//		List<String> list = this.getJdbcTemplate().queryForList(sql,String.class);
//		return list;
//	}
}
