package com.banorte.ws.esb.reconciliaciones.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.entity.RepOig;
import com.banorte.ws.esb.reconciliaciones.repository.RepOigRepository;
import com.banorte.ws.esb.reconciliaciones.util.Singleton_handling_errors;

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
		
		List<ObtenerInventarioFiltradoOut> returnlist=null;
		try {
			returnlist = repOigRepository.findFullInventory(pUsuario,pTerminal,pVar,pClaveAplicativo);
		} catch(ConstraintViolationException e) {
			System.out.println(e.getMessage());
			Singleton_handling_errors.getInstance().setId("2");
			Singleton_handling_errors.getInstance().setMessange_user("Error en base de datos");
			Singleton_handling_errors.getInstance().setMessage_detail(e.getMessage());
		} catch(JDBCConnectionException e) {
			System.out.println(e.getMessage());
			Singleton_handling_errors.getInstance().setId("2");
			Singleton_handling_errors.getInstance().setMessange_user("Error en base de datos");
			Singleton_handling_errors.getInstance().setMessage_detail(e.getMessage());
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
			Singleton_handling_errors.getInstance().setId("2");
			Singleton_handling_errors.getInstance().setMessange_user("Error en base de datos");
			Singleton_handling_errors.getInstance().setMessage_detail(ex.getMessage());
		}
		
		return (List<ObtenerInventarioFiltradoOut>) returnlist;
	}
	
	@Override
	public String getIdOperation() {
		return repOigRepository.getIdOperation();
	}
	
}
