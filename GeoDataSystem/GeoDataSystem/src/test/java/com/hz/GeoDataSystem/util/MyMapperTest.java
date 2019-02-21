package com.hz.GeoDataSystem.util;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import com.hz.GeoDataSystem.application.service.UserService;
import com.hz.GeoDataSystem.dao.UserMapper;
import com.hz.GeoDataSystem.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages ="com.hz.GeoDataSystem.dao")
public class MyMapperTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

	 @Test
	 public void selectByName() {		 
	 List<User> users = userService.listAll();
	 for(int i=0;i<users.size();i++){
		 System.out.println("名字："+users.get(i).getUsername());
		 logger.info("名字："+users.get(i).getUsername());
	 }
	 }

}
