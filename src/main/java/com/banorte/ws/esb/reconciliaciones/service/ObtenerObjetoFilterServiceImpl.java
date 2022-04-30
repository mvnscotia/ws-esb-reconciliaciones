package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banorte.ws.esb.reconciliaciones.dao.ObtenerInventarioFullOutDao;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFullOut;

@Service("ObtenerObjetoFilterService")
public class ObtenerObjetoFilterServiceImpl implements IObtenerObjetoFilterService{

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private ObtenerInventarioFullOutDao obtenerInventarioFullOutDao;
	
	@Override
	public List<ObtenerInventarioFullOut> findAll() {
		return (List<ObtenerInventarioFullOut>) obtenerInventarioFullOutDao.findAll();
	}

	@Override
	public ObtenerInventarioFullOut findByID(Long id) {
		return obtenerInventarioFullOutDao.findById(id).get();
	}
	
	public List<ObtenerInventarioFullOut> getObjetoFiltradaResponse(String pUsuario,String pTerminal,String p_var) {
		return (List<ObtenerInventarioFullOut>) obtenerInventarioFullOutDao.getInventarioFullOut(pUsuario,pTerminal,p_var);
	}
	
}

