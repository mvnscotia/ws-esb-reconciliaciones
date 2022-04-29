package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;

import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFullOut;

public interface IObtenerObjetoFilterService {

	public List<ObtenerInventarioFullOut> findAll();
	
	public ObtenerInventarioFullOut findByID(int id);
	
	public List<ObtenerInventarioFullOut> getObjetoFiltradaResponse(String pUsuario,String pTerminal,String p_var);
	
}
