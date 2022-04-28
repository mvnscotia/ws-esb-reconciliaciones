package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banorte.ws.esb.reconciliaciones.dao.ObtenerInventarioFullOutDao;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFullOut;

@Service("ObtenerInventarioFullOutService")
public class ObtenerInventarioFullOutServiceImpl implements IObtenerInventarioFullOut{

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private ObtenerInventarioFullOutDao obtenerInventarioFullOutDao;
	
	@Override
	public List<ObtenerInventarioFullOut> findAll() {
		return (List<ObtenerInventarioFullOut>) obtenerInventarioFullOutDao.findAll();
	}

	@Override
	public ObtenerInventarioFullOut findByID(int id) {
		return obtenerInventarioFullOutDao.findById(id).get();
	}
	
	public List<ObtenerInventarioFullOut> getInventarioFullOut(String pUsuario,String pTerminal,String p_var) {
		return (List<ObtenerInventarioFullOut>) obtenerInventarioFullOutDao.getInventarioFullOut(pUsuario,pTerminal,p_var);
	}
	
}

