package com.banorte.ws.esb.reconciliaciones.ws;

import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema.*;
import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema.ObtenerInventarioFullTypeResponse.Objetos.Objeto;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerInventarioFullOutServiceImpl;
import com.banorte.ws.esb.reconciliaciones.util.Props;


@Endpoint
public class ObtenerInventarioFullEndPoint {

	private static final String NAMESPACE_URI = "http://www.banorte.com/ws/esb/Reconciliaciones";

	@Autowired
	ObtenerInventarioFullOutServiceImpl obtenerInventarioFullOutServiceImpl;
	
	@Autowired
	Props propsObj;
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerInventarioFullIn")
	@ResponsePayload
	public JAXBElement<ObtenerInventarioFullTypeResponse> getObtenerInventarioFullInType(
			@RequestPayload JAXBElement<ObtenerInventarioFullTypeRequest> request) throws SQLException {
		
		ObjectFactory objectFactory = new ObjectFactory();
		
		ObtenerInventarioFullTypeResponse InventarioFullResponseFactory = objectFactory.createObtenerInventarioFullTypeResponse();
		
		JAXBElement<ObtenerInventarioFullTypeResponse> InventarioFullResponse = objectFactory.createObtenerInventarioFullOut(InventarioFullResponseFactory);
		
		ObtenerInventarioFullTypeResponse ObtenerInventarioFullResponseObject= new ObtenerInventarioFullTypeResponse();
		
		ObtenerInventarioFullResponseObject.setObjetos(new ObtenerInventarioFullTypeResponse.Objetos());
		//N1,N2,R1,R3,FULL			
		String pUsuario="";
		String pTerminal="";
		String pVar=request.getValue().getTranTipoObjeto();
		String pClaveAplicativo=request.getValue().getTranAplicacion();
		String type_query=propsObj.find_coincidence(pVar);/* Se busca coincidencia de acuerdo a lo establecido por el cliente*/
		
		if(!type_query.equals("not_found"))
		{	
			List<ObtenerInventarioFiltradoOut> listObtenerInventarioFullOut = obtenerInventarioFullOutServiceImpl.getInventarioFullOut(pUsuario,pTerminal,type_query,pClaveAplicativo);
			Objeto listResponseObjects= new Objeto();
			
			if (listObtenerInventarioFullOut != null) {
				for (ObtenerInventarioFiltradoOut objfull : listObtenerInventarioFullOut) {
					
					listResponseObjects= new Objeto();
					listResponseObjects.setTranIdTipoObjeto(objfull.getVal1());
					ObtenerInventarioFullResponseObject.getObjetos().getObjeto().add(listResponseObjects);
				}
			}
			
			InventarioFullResponse.setValue(ObtenerInventarioFullResponseObject);
			
		}
		return InventarioFullResponse;
		
	}
	
}



















