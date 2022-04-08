package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;

import com.banorte.ws.esb.reconciliaciones.entity.User;

public interface IUserService {

	public List<User> findAll();
	
	public User findByID(int id);
}
