package com.hz.GeoDataSystem.application.service;

import java.util.List;

import com.hz.GeoDataSystem.model.User;
import com.hz.GeoDataSystem.util.aop.TargetDataSource;

public interface UserService {
	
	 List<User> listAll();
	 
	 int insert(User user);
}
