package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.entity.RepOig;

public interface IObtenerInventarioFullOut {

	public List<RepOig> findAll();
	
	public RepOig findByID(Long id);
	
	public List<ObtenerInventarioFiltradoOut> getInventarioFullOut(String pUsuario,String pTerminal,String pVar,String pClaveAplicativo);
	
	public String getIdOperation();
}
