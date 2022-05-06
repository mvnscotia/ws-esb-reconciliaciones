package com.banorte.ws.esb.reconciliaciones.ws;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema.*;
import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema.ObtenerInventarioFullTypeResponse.Objetos.Objeto;
import com.banorte.ws.esb.reconciliaciones.entity.T_RECORD_REP_OIG;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerInventarioFullOutServiceImpl;
import com.banorte.ws.esb.reconciliaciones.util.Props;


@Endpoint
public class ObtenerInventarioFullEndPoint {

	private static final String NAMESPACE_URI = "http://www.banorte.com/ws/esb/Reconciliaciones";

	@Autowired
	ObtenerInventarioFullOutServiceImpl obtenerInventarioFullOutServiceImpl;
	
	@Autowired
	Props PropsObj;
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerInventarioFullTypeRequest")
	@ResponsePayload
	public JAXBElement<ObtenerInventarioFullTypeResponse> getObtenerInventarioFullInType(
			@RequestPayload JAXBElement<ObtenerInventarioFullTypeRequest> request) throws SQLException {
		
		ObjectFactory objectFactory = new ObjectFactory();
		
		ObtenerInventarioFullTypeResponse InventarioFullResponseFactory = objectFactory.createObtenerInventarioFullTypeResponse();
		
		JAXBElement<ObtenerInventarioFullTypeResponse> InventarioFullResponse = objectFactory.createObtenerInventarioFullTypeResponse(InventarioFullResponseFactory);
		
		ObtenerInventarioFullTypeResponse ObtenerInventarioFullResponseObject= new ObtenerInventarioFullTypeResponse();
		
		ObtenerInventarioFullResponseObject.setObjetos(new ObtenerInventarioFullTypeResponse.Objetos());
		//N1,N2,R1,R3,FULL			
		String pUsuario="";
		String pTerminal="";
		String p_var=request.getValue().getTranTipoObjeto();
		String clave_aplicativo=request.getValue().getTranAplicacion();
		String type_query=PropsObj.find_coincidence(p_var);/* Se busca coincidencia de acuerdo a lo establecido por el cliente*/
		
		if(!type_query.equals("not_found"))
		{	
			List<T_RECORD_REP_OIG> listObtenerInventarioFullOut = obtenerInventarioFullOutServiceImpl.getInventarioFullOut(pUsuario,pTerminal,type_query,clave_aplicativo);
			Objeto listResponseObjects= new Objeto();
			
			if (listObtenerInventarioFullOut != null) {
				for (T_RECORD_REP_OIG objfull : listObtenerInventarioFullOut) {
					
					listResponseObjects= new Objeto();
					listResponseObjects.setTranIdTipoObjeto(objfull.getVAL1());
					ObtenerInventarioFullResponseObject.getObjetos().getObjeto().add(listResponseObjects);
				}
			}
			
			InventarioFullResponse.setValue(ObtenerInventarioFullResponseObject);
			
		}
		return InventarioFullResponse;
		
	}
	
}



















