package com.banorte.ws.esb.reconciliaciones.ws;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema.*;
import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema.ObtenerInventarioFullTypeResponse.Objetos.Objeto;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFullOut;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerInventarioFullOutServiceImpl;

@Endpoint
public class ObtenerInventarioFullEndPoint {

	private static final String NAMESPACE_URI = "http://www.banorte.com/ws/esb/Reconciliaciones";

	@Autowired
	ObtenerInventarioFullOutServiceImpl obtenerInventarioFullOutServiceImpl;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerInventarioFullTypeRequest")
	@ResponsePayload
	public JAXBElement<ObtenerInventarioFullTypeResponse> getObtenerInventarioFullInType(
			@RequestPayload JAXBElement<ObtenerInventarioFullTypeRequest> request) {
			
			ObjectFactory objectFactory = new ObjectFactory();
			
			ObtenerInventarioFullTypeResponse InventarioFullResponseFactory = objectFactory.createObtenerInventarioFullTypeResponse();
			
			JAXBElement<ObtenerInventarioFullTypeResponse> InventarioFullResponse = objectFactory.createObtenerInventarioFullTypeResponse(InventarioFullResponseFactory);
			
			ObtenerInventarioFullTypeResponse ObtenerInventarioFullResponseObject= new ObtenerInventarioFullTypeResponse();
			
			ObtenerInventarioFullResponseObject.setObjetos(new ObtenerInventarioFullTypeResponse.Objetos());
			
			System.out.println("------>");
			/*We need to change this part*/
			List<ObtenerInventarioFullOut> listObtenerInventarioFullOut = obtenerInventarioFullOutServiceImpl.getInventarioFullOut();
			
			//Map<String, Object> ObjFullOut=obtenerInventarioFullOutServiceImpl.getInventarioFull("", "", "N1");
			
			Objeto listResponseObjects= new Objeto();
			
			/*int Numrandom;
			Random random = new Random();
			
			for (int i=0;i<=20;i++) {
				
				listResponseObjects= new Objeto();				
				
				Numrandom = random.nextInt(2000 + 1000) + 1000;
				
				listResponseObjects.setTranIdTipoObjeto(Numrandom+"");
				
				ObtenerInventarioFullResponseObject.getObjetos().getObjeto().add(listResponseObjects);
			}*/
			
			
			if (listObtenerInventarioFullOut != null) {
				for (ObtenerInventarioFullOut objfull : listObtenerInventarioFullOut) {
					
					listResponseObjects= new Objeto();
					listResponseObjects.setTranIdTipoObjeto(objfull.getREPOIGID());
					ObtenerInventarioFullResponseObject.getObjetos().getObjeto().add(listResponseObjects);
					
				}
			}
			
			InventarioFullResponse.setValue(ObtenerInventarioFullResponseObject);
			
			return InventarioFullResponse;
			
	}
	
}




















