package com.hz.GeoDataSystem.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hz.GeoDataSystem.application.service.UserService;
import com.hz.GeoDataSystem.dao.UserMapper;
import com.hz.GeoDataSystem.model.User;
import com.hz.GeoDataSystem.util.aop.TargetDataSource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    
    @TargetDataSource(value = "primaryDataSource")
    //@TargetDataSource(value = "secondaryDataSource")
	@Override
	public List<User> listAll() {
		return userMapper.listAll();
	}
	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}
}
