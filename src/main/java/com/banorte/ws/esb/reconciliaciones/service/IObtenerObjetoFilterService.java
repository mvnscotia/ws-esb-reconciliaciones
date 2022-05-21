package com.banorte.ws.esb.reconciliaciones.service;

import java.sql.Clob;
import java.util.List;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.entity.RepOig;

import oracle.sql.CLOB;

public interface IObtenerObjetoFilterService {

	public List<RepOig> findAll();
	
	public RepOig findByID(Long id);
	
	public List<ObtenerInventarioFiltradoOut> getObjetoFiltradaResponse(String pUsuario,String pTerminal,String json1,String json2,String json3,String pVar,String pClaveAplicativo);
	
	public String getIdOperation();
}
