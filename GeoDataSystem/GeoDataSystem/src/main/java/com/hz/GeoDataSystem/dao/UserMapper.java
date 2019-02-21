package com.hz.GeoDataSystem.dao;

import java.util.List;
import com.hz.GeoDataSystem.model.User;

public interface UserMapper{
	
 List<User> listAll();

 int insert(User user);
 
}
