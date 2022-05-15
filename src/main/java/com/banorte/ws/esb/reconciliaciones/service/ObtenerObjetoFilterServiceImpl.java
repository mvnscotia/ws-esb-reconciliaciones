package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.entity.RepOig;
import com.banorte.ws.esb.reconciliaciones.repository.RepOigRepository;

@Service("ObtenerObjetoFilterService")
public class ObtenerObjetoFilterServiceImpl implements IObtenerObjetoFilterService{

	
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
	public List<ObtenerInventarioFiltradoOut> getObjetoFiltradaResponse(String pUsuario,String pTerminal,String pjson,String pVar,String pClaveAplicativo) {
		return (List<ObtenerInventarioFiltradoOut>) repOigRepository.findFilteredInventoryByObjectType(pUsuario,pTerminal,pjson,pVar,pClaveAplicativo);
	}
	
}

