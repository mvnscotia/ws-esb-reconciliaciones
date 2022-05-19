package com.banorte.ws.esb.reconciliaciones.ws;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema.*;
import com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema.ObtenerInventarioFullTypeResponse.Objetos.Objeto;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.requestHeaders.schema.HeaderRequestType;
import com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.EstadoRespuestaType;
import com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.HeaderResponseType;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerInventarioFullOutServiceImpl;
import com.banorte.ws.esb.reconciliaciones.util.Props;
import com.banorte.ws.esb.reconciliaciones.util.Singleton_handling_errors;


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
			@RequestPayload JAXBElement<ObtenerInventarioFullTypeRequest> request,
			MessageContext messageContext){
		
		
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
		String type_query=propsObj.find_coincidence(pVar);/* Se busca coincidencia de acuerdo a lo establecido por el cliente */
		
		if(!type_query.equals("not_found"))
		{	
			List<ObtenerInventarioFiltradoOut> listObtenerInventarioFullOut = obtenerInventarioFullOutServiceImpl.getInventarioFullOut(pUsuario,pTerminal,type_query,pClaveAplicativo);
			if(Singleton_handling_errors.getInstance().getId().equals("1"))
			{
				Objeto listResponseObjects= new Objeto();
				
				if( listObtenerInventarioFullOut.size()==0)
				{
					System.out.println("-----++++++----");
					Singleton_handling_errors.getInstance().setId("4");
					Singleton_handling_errors.getInstance().setMessange_user("Error en la logica de negocio");
					Singleton_handling_errors.getInstance().setMessage_detail("Peticion incorrecta");
				}
				
				if (listObtenerInventarioFullOut != null) {
					for (ObtenerInventarioFiltradoOut objfull : listObtenerInventarioFullOut) {
						
						listResponseObjects= new Objeto();
						listResponseObjects.setTranIdTipoObjeto(objfull.getVal1());
						ObtenerInventarioFullResponseObject.getObjetos().getObjeto().add(listResponseObjects);
					}
				}
			
				InventarioFullResponse.setValue(ObtenerInventarioFullResponseObject);
			}
		}
		else
		{
			System.out.println("----------");
			Singleton_handling_errors.getInstance().setId("4");
			Singleton_handling_errors.getInstance().setMessange_user("Error en la logica de negocio");
			Singleton_handling_errors.getInstance().setMessage_detail("Peticion incorrecta");
		}
		
		
		
		
		/**************************************************************************************************/
		
		String idOperacion = "", tokenOperacion = "";
		try {
			try {
		        /*
		          Get MessageContext to get request soap message
		          defined in ObtenerObjetoFiltradaRequestMsg message from WSDL
		        */
				SaajSoapMessage soapRequest = (SaajSoapMessage) messageContext.getRequest();
	
		        /*
		          Read request Header
		          requestHeader is defined in ObtenerObjetoFiltradaRequestMsg message
		          that make reference to head:HeaderReq element in WSLD definition
		        */			 
			    org.springframework.ws.soap.SoapHeader soapRequestHeader = soapRequest.getSoapHeader();
	
		        /*
		          Get MessageContext to get response soap message
		          defined in ObtenerObjetoFiltradaResponseMsg message from WSDL
		        */
			    SaajSoapMessage soapResponse = (SaajSoapMessage) messageContext.getResponse();
			     
			    /*
		          Read response Header
		          responseHeader is defined in ObtenerObjetoFiltradaRequestMsg message
		          that make reference to head:HeaderRes element in WSDL definition
		        */			     
			    org.springframework.ws.soap.SoapHeader soapResponseHeader = soapResponse.getSoapHeader();        
	
			    /*
			     * We need to create JAXB element object to parse the header that we got from request message
			    */
			    JAXBElement<HeaderRequestType> headersRequestType = null;
			     
			    /*
			     * We create an instance of JAXBContest base on the header request factory created by JAXB
			     * this is important because this indicate the base structure of your header request element
			    */
				JAXBContext jaxbContext = JAXBContext.newInstance(com.banorte.ws.esb.reconciliaciones.requestHeaders.schema.ObjectFactory.class);
			        
		        Iterator<SoapHeaderElement> itr = soapRequestHeader.examineAllHeaderElements();
			    while (itr.hasNext()) {
			    	SoapHeaderElement ele = itr.next();
			        headersRequestType = (JAXBElement<HeaderRequestType>)jaxbContext.createUnmarshaller().unmarshal(ele.getSource());
			    }
	
			    /*
			     * Create response header
			     * And send back
			     * The process in the same like when we send a nomar response object
			     * but now we will send the response object into the soapResponseHeader object
			     * calling the result method
			    */       
				com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.ObjectFactory responseHeaderObjFactory = new com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.ObjectFactory();
					
				HeaderResponseType headerResponseFactory = responseHeaderObjFactory.createHeaderResponseType();
				JAXBElement<HeaderResponseType> headerResResponse = responseHeaderObjFactory.createHeaderRes(headerResponseFactory);
					
				HeaderResponseType headerResponseTypeObject = new HeaderResponseType();
				headerResponseTypeObject.setEstadoRespuesta(new EstadoRespuestaType());
				
				//idOperacion = headersRequestType.getValue().getAcceso().getIdOperacion();
				/* Calculate of idOperation */
				idOperacion = obtenerInventarioFullOutServiceImpl.getIdOperation();
				tokenOperacion = headersRequestType.getValue().getAcceso().getTokenOperacion();
					
				headerResponseTypeObject.setIdOperacion(idOperacion);
				headerResponseTypeObject.setTokenOperacion(tokenOperacion);
				
				headerResponseTypeObject.getEstadoRespuesta().setId(Singleton_handling_errors.getInstance().getId());
				headerResponseTypeObject.getEstadoRespuesta().setMensajeAUsuario(Singleton_handling_errors.getInstance().getMessange_user());
				headerResponseTypeObject.getEstadoRespuesta().setMensajeDetallado(Singleton_handling_errors.getInstance().getMessage_detail());
				 
				 
				headerResResponse.setValue(headerResponseTypeObject);
				 
			    /*
			    * Send response back
			    * It is important to make sure create a new JAXB instance from
			    * existing jaxbContext variable that we created at the beginning to parse the request headers,
			    * and also make sure that in this new instance we pass the ObjectFactory
			    * that belong to response Headers object to be able to marshal it in a right way
			   */
			    jaxbContext = JAXBContext.newInstance(com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.ObjectFactory.class);
			    jaxbContext.createMarshaller().marshal(headerResResponse, soapResponseHeader.getResult());
			  }finally {
				  
			  }
		}catch(JAXBException jb){
			Singleton_handling_errors.getInstance().setId("3");
			Singleton_handling_errors.getInstance().setMessange_user("Error ESB");
			Singleton_handling_errors.getInstance().setMessage_detail(jb.getMessage());
		}
		/**************************************************************************************************/
		
		
		
		return InventarioFullResponse;
		
	}
	
}



















