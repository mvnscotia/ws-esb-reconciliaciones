package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import java.util.Map;

import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFullOut;

public interface IObtenerInventarioFullOut {

	public List<ObtenerInventarioFullOut> findAll();
	
	public ObtenerInventarioFullOut findByID(Long id);
	
	public List<ObtenerInventarioFullOut> getInventarioFullOut(String pUsuario,String pTerminal,String p_var);
	
}
