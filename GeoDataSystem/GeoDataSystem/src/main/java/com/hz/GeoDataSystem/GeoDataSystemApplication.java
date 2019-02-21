package com.hz.GeoDataSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.hz.GeoDataSystem.util.dynamicDataSource.DataSourceConfig;
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication
public class GeoDataSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeoDataSystemApplication.class, args);
	}
}
