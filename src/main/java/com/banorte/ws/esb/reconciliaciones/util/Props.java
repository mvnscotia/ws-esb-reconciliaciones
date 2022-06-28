package com.banorte.ws.esb.reconciliaciones.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.banorte.ws.esb.reconciliaciones.requestHeaders.schema.HeaderRequestType;
import com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.EstadoRespuestaType;
import com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.HeaderResponseType;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerObjetoFilterServiceImpl;

@Component
public class Props {
	
	@Autowired
	private ObtenerObjetoFilterServiceImpl obtenerObjetoFilterService;
	
	
	public String find_coincidence(String to_find)
	{
		String found="";
		switch(to_find)
		{
			case "perfil": 
							found="N1";break;
			case "transaccion": 
							found="N2";break;
			case "relacion-perfil-transaccion": 
							found="R1";break;
			case "relacion-usuario-perfil": 
							found="R3";break;
			default:found="not_found";break;
		}
		
		return found;
	}
	
	public String removeSpaceInString(String parameter) {
		parameter = parameter.replaceAll(" ", "");
		parameter = !parameter.isEmpty() ? parameter : "";
		return parameter;
	}
	
	public Map<String,Object> getRequestHeaders(MessageContext messageContext){
		
		Map<String,Object> result = new HashMap<>();
	    /*
	     * We need to create JAXB element object to parse the header that we got from request message
	    */
	    JAXBElement<HeaderRequestType> headersRequestType = null;		
		String idOperacion = "", tokenOperacion = "";
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
			     * We create an instance of JAXBContext base on the header request factory created by JAXB
			     * this is important because this indicate the base structure of your header request element
			    */
				JAXBContext jaxbContext = JAXBContext.newInstance(com.banorte.ws.esb.reconciliaciones.requestHeaders.schema.ObjectFactory.class);
			        
		        Iterator<SoapHeaderElement> itr = soapRequestHeader.examineAllHeaderElements();
			    while (itr.hasNext()) {
			    	SoapHeaderElement ele = itr.next();
			        headersRequestType = (JAXBElement<HeaderRequestType>)jaxbContext.createUnmarshaller().unmarshal(ele.getSource());
			    }
			    
			    result.put("messageContext", messageContext);
			    result.put("jaxbContext", jaxbContext);
			    result.put("headersRequestType", headersRequestType);
	
		}catch(JAXBException jb){
			Singleton_handling_errors.getInstance().setId("3");
			Singleton_handling_errors.getInstance().setMessange_user("Error ESB");
			Singleton_handling_errors.getInstance().setMessage_detail(jb.getMessage());
		}
				
		
		return result;
	}
	
	
	public void setResponseHeaders(Map<String,Object> requestHeadersParams ){
		
		String idOperacion = "", tokenOperacion = "";
		try {
			try {
				
				MessageContext messageContext = (MessageContext) requestHeadersParams.get("messageContext");
				JAXBContext jaxbContext = (JAXBContext) requestHeadersParams.get("jaxbContext");
				JAXBElement<HeaderRequestType> headersRequestType = (JAXBElement<HeaderRequestType>) requestHeadersParams.get("headersRequestType");
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
				idOperacion = obtenerObjetoFilterService.getIdOperation();
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
	}
	
}
	