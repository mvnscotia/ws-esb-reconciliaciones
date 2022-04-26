package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import java.util.Map;

import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.entity.ObtenerInventarioFullOut;

public interface IObtenerInventarioFullOut {

	public List<ObtenerInventarioFullOut> findAll();
	
	public ObtenerInventarioFullOut findByID(int id);
	
	public List<ObtenerInventarioFullOut> getInventarioFullOut();
	
	public Map<String, Object> getInventarioFull(String pUsuario,String pTerminal,String p_var);
	
}
