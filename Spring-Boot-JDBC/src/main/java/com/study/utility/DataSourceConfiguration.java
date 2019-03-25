package com.study.utility;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:datasource_config.properties")
public class DataSourceConfiguration {

	@Autowired
	Environment env;

	@Bean("dsMap")
	public Map<String, DriverManagerDataSource> getDSMap() {
		Map<String, DriverManagerDataSource> dsMap = new HashMap<>();
		for (int i = 1; i < 100; i++) {
			String driver = env.getProperty("datasource" + i + ".driverClassName");
			if (driver != null) {
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName(env.getProperty("datasource" + i + ".driverClassName"));
				dataSource.setUrl(env.getProperty("datasource" + i + ".url"));
				dataSource.setUsername(env.getProperty("datasource" + i + ".username"));
				dataSource.setPassword(env.getProperty("datasource" + i + ".password"));
				dsMap.put("datasource" + i, dataSource);
			} else {
				break;
			}
		}
		return dsMap;
	}

//	@Bean("dataSource2")
//	public DataSource getDataSource2() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(env.getProperty("spring.datasource2.driverClassName"));
//		dataSource.setUrl(env.getProperty("spring.datasource2.url"));
//		dataSource.setUsername(env.getProperty("spring.datasource2.username"));
//		dataSource.setPassword(env.getProperty("spring.datasource2.password"));
//		return dataSource;
//	}

}
