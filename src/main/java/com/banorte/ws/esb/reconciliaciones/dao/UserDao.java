package com.banorte.ws.esb.reconciliaciones.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banorte.ws.esb.reconciliaciones.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
