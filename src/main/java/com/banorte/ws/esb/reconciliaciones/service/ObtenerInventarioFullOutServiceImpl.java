package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banorte.ws.esb.reconciliaciones.dao.ObtenerInventarioFullOutDao;
import com.banorte.ws.esb.reconciliaciones.entity.T_RECORD_REP_OIG;

@Service("ObtenerInventarioFullOutService")
public class ObtenerInventarioFullOutServiceImpl implements IObtenerInventarioFullOut{

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private ObtenerInventarioFullOutDao obtenerInventarioFullOutDao;
	
	@Override
	public List<T_RECORD_REP_OIG> findAll() {
		return (List<T_RECORD_REP_OIG>) obtenerInventarioFullOutDao.findAll();
	}

	@Override
	public T_RECORD_REP_OIG findByID(Long id) {
		return obtenerInventarioFullOutDao.findById(id).get();
	}
	
	public List<T_RECORD_REP_OIG> getInventarioFullOut(String pUsuario,String pTerminal,String p_var,String clave_aplicativo) {
		return (List<T_RECORD_REP_OIG>) obtenerInventarioFullOutDao.getInventarioFullOut(pUsuario,pTerminal,p_var,clave_aplicativo);
	}
	
}
