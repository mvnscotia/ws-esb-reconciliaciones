package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.banorte.ws.esb.reconciliaciones.dao.UserDao;
import com.banorte.ws.esb.reconciliaciones.entity.User;

@Service("userService")
public class UserServiceImpl implements IUserService{

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User findByID(int id) {
		return userDao.findById(id).get();
	}

}
