package com.lin.service;

import com.lin.domain.User;
import com.lin.domain.UserExample;

public interface IRegisterService {
	
	public int insert(User record);
	
	public int countByExample(UserExample example);

}
