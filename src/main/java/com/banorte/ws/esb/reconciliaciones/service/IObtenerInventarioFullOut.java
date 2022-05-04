package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import java.util.Map;

import com.banorte.ws.esb.reconciliaciones.entity.T_RECORD_REP_OIG;

public interface IObtenerInventarioFullOut {

	public List<T_RECORD_REP_OIG> findAll();
	
	public T_RECORD_REP_OIG findByID(Long id);
	
	public List<T_RECORD_REP_OIG> getInventarioFullOut(String pUsuario,String pTerminal,String p_var,String clave_aplicativo);
	
}
