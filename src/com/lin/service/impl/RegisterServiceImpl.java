package com.lin.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lin.dao.UserDao;
import com.lin.domain.User;
import com.lin.domain.UserExample;
import com.lin.service.IRegisterService;
@Service("registerService")
public class RegisterServiceImpl implements IRegisterService{
	private static Logger logger = Logger.getLogger(RegisterServiceImpl.class);  
	@Resource
	private UserDao userDao;

	@Override
	public int insert(User record) {	
		try {
			return userDao.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countByExample(UserExample example) {
		try {
			return userDao.countByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
