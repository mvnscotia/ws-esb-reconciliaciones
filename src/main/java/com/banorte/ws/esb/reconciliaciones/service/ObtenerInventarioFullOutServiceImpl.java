package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.entity.RepOig;
import com.banorte.ws.esb.reconciliaciones.repository.RepOigRepository;

@Service("ObtenerInventarioFullOutService")
public class ObtenerInventarioFullOutServiceImpl implements IObtenerInventarioFullOut{

	
	@Autowired
	private RepOigRepository repOigRepository;
	
	@Override
	public List<RepOig> findAll() {
		return (List<RepOig>) repOigRepository.findAll();
	}

	@Override
	public RepOig findByID(Long id) {
		return repOigRepository.findById(id).get();
	}
	
	@Override
	public List<ObtenerInventarioFiltradoOut> getInventarioFullOut(String pUsuario,String pTerminal,String pVar,String pClaveAplicativo) {
		return (List<ObtenerInventarioFiltradoOut>) repOigRepository.findFullInventory(pUsuario,pTerminal,pVar,pClaveAplicativo);
	}
	
}
