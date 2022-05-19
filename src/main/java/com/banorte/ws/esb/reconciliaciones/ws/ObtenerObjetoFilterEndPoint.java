package com.banorte.ws.esb.reconciliaciones.ws;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObjectFactory;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaInType;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutType;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutType.Objetos.Objeto;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutType.Relaciones;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutType.Relaciones.Relacion;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.requestHeaders.schema.HeaderRequestType;
import com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.EstadoRespuestaType;
import com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.HeaderResponseType;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerObjetoFilterServiceImpl;
import com.banorte.ws.esb.reconciliaciones.util.Props;
import com.banorte.ws.esb.reconciliaciones.util.Singleton_handling_errors;
import com.google.gson.Gson;




//import weblogic.logging.NonCatalogLogger;

@Endpoint
public class ObtenerObjetoFilterEndPoint {

	private static final String NAMESPACE_URI = "http://www.banorte.com/ws/esb/Reconciliaciones";
	private static final String ALTA = "Alta";
	private static final String MODIFICACION = "Modificacion";
	private static final String INIT_DATE = "0001-01-01";

	@Autowired
	private ObtenerObjetoFilterServiceImpl obtenerObjetoFilterService;
	
	@Autowired
	Props propsObj;
	
	private Map<String, XMLGregorianCalendar> dateList ;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerObjetoFiltradaIn")
	@ResponsePayload
	public JAXBElement<ObtenerObjetoFiltradaOutType> getObtenerObjetoFiltradaInType(
			@RequestPayload JAXBElement<ObtenerObjetoFiltradaInType> request,
			MessageContext messageContext)throws JAXBException, TransformerException {
		
		/* Structure to send response body*/
		ObjectFactory objectFactory = new ObjectFactory();
		
		ObtenerObjetoFiltradaOutType objetoFiltradaResponseFactory = objectFactory.createObtenerObjetoFiltradaOutType();
		
		JAXBElement<ObtenerObjetoFiltradaOutType> objetoFiltradaRespose = objectFactory.createObtenerObjetoFiltradaOut(objetoFiltradaResponseFactory);
		
		ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject = new ObtenerObjetoFiltradaOutType();
		
		//obtenerObjetoFilterResponseObject.setObjetos(new ObtenerObjetoFiltradaOutType.Objetos());
		
		/*@Daniel We need to improve this variable assignment*/
		//N1,N2,R1,R3,FULL			
		String pUsuario="";
		String pTerminal="";
		String pClaveAplicativo=request.getValue().getTranAplicacion();
		String pVar=request.getValue().getTranTipoObjeto();
		
		String json = new Gson().toJson( request.getValue().getObjetos().getObjeto() );
		json=json.toLowerCase();
		
		String type_query=propsObj.find_coincidence(pVar);/* Se busca coincidencia de acuerdo a lo establecido por el cliente*/		
		
		List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut = null;
		
		if( json.equals("[{}]") )//No hay valores
		{
			Singleton_handling_errors.getInstance().setId("4");
			Singleton_handling_errors.getInstance().setMessange_user("Error en la logica de negocio");
			Singleton_handling_errors.getInstance().setMessage_detail("Peticion incorrecta");
		}
		else
		{
			listObtenerObjetoFilterOut = obtenerObjetoFilterService.getObjetoFiltradaResponse(pUsuario,pTerminal,type_query,pVar, pClaveAplicativo);
		}
		
		if(Singleton_handling_errors.getInstance().getId().equals("1"))
		{
			if(!type_query.equals("not_found"))
			{
				if (listObtenerObjetoFilterOut != null) {
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					dateList = new HashMap<String, XMLGregorianCalendar>();
					
					switch (type_query) {
					case "N1":
						obtenerObjetoFilterResponseObject.setObjetos(new ObtenerObjetoFiltradaOutType.Objetos());
						populateN1Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format);
						break;
					case "N2":
						obtenerObjetoFilterResponseObject.setObjetos(new ObtenerObjetoFiltradaOutType.Objetos());
						populateN2Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format);
						break;
					case "R1":
						populateR1Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format);
						break;
					case "R3":
						populateR3Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format);
						break;
					default:
						break;
					}
				}
				objetoFiltradaRespose.setValue(obtenerObjetoFilterResponseObject);
			}
			else
			{
				Singleton_handling_errors.getInstance().setId("4");
				Singleton_handling_errors.getInstance().setMessange_user("Error en la logica de negocio");
				Singleton_handling_errors.getInstance().setMessage_detail("Peticion incorrecta");
			}
		}
		
		/********************************************************************/
		
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
		
		/********************************************************************/
		
		return objetoFiltradaRespose;
		
	}
	

	
	private void populateN1Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, DateFormat format) {
		Objeto listResponseObjects= new Objeto();
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			listResponseObjects= new Objeto();
			listResponseObjects.setTranIdGrupo("");
			listResponseObjects.setTranNombreGrupo("");
			listResponseObjects.setTranIdTipoObjeto(fullObj.getVal1());
			listResponseObjects.setTranObjeto(fullObj.getVal2());
			listResponseObjects.setTranDescripcion(fullObj.getVal3());
			listResponseObjects.setTranFechaCreacion(getFechaCreacion(fullObj.getVal5(), format));
			listResponseObjects.setTranIdUsuario(propsObj.removeSpaceInString(fullObj.getVal6()));
			listResponseObjects.setTranFechaModificacion(getFechaCreacion(fullObj.getVal7(), format));
			listResponseObjects.setTranExtension("");
			
			obtenerObjetoFilterResponseObject.getObjetos().getObjeto().add(listResponseObjects);
		}		
	}
	
	private void populateN2Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, DateFormat format) {
		Objeto listResponseObjects= new Objeto();
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			listResponseObjects= new Objeto();
			listResponseObjects.setTranIdGrupo("");
			listResponseObjects.setTranNombreGrupo("");
			listResponseObjects.setTranIdTipoObjeto(fullObj.getVal1());
			listResponseObjects.setTranObjeto(fullObj.getVal4());
			listResponseObjects.setTranDescripcion(fullObj.getVal5());
			listResponseObjects.setTranTipoOperacion(fullObj.getVal6());
			listResponseObjects.setTranFechaCreacion(getFechaCreacion(fullObj.getVal8(), format));
			listResponseObjects.setTranIdUsuario(propsObj.removeSpaceInString(fullObj.getVal9()));
			listResponseObjects.setTranFechaModificacion(getFechaCreacion(fullObj.getVal10(), format));
			listResponseObjects.setTranExtension("");
			
			obtenerObjetoFilterResponseObject.getObjetos().getObjeto().add(listResponseObjects);
		}
	}
	
	private void populateR1Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, DateFormat format) {
		Relacion relacionAttributeList = new Relacion();
		Relaciones relacionesList = new Relaciones();
		
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			relacionAttributeList = new Relacion();
			relacionAttributeList.setTranIdPerfil(fullObj.getVal1());
			relacionAttributeList.setTranIdTransaccion(fullObj.getVal2());  
			relacionAttributeList.setTranTipoOperacion(getFechaCreacionR1(fullObj.getVal3()));
			relacionAttributeList.setTranIdUsuario(fullObj.getVal4());
			relacionAttributeList.setTranFechaOperacion(getFechaCreacion(fullObj.getVal3(), format));
			
			relacionesList = new Relaciones();
			relacionesList.setTranIdTipoObjeto("");
			relacionesList.getRelacion().add(relacionAttributeList);
			
			obtenerObjetoFilterResponseObject.getRelaciones().add(relacionesList);
		}
		
	}	
	
	private void populateR3Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, DateFormat format) {
		Relacion relacionAttributeList = new Relacion();
		Relaciones relacionesList = new Relaciones();
		
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			relacionAttributeList = new Relacion();
			relacionAttributeList.setTranIdPerfil(fullObj.getVal2()); 
			relacionAttributeList.setTranIdTransaccion("");
			relacionAttributeList.setTranTipoOperacion("Alta");
			relacionAttributeList.setTranIdUsuario(fullObj.getVal1()); 
			relacionAttributeList.setTranFechaOperacion(getFechaCreacion(fullObj.getVal3(), format));
			
			relacionesList = new Relaciones();
			relacionesList.setTranIdTipoObjeto("");
			relacionesList.getRelacion().add(relacionAttributeList);
			
			obtenerObjetoFilterResponseObject.getRelaciones().add(relacionesList);
		}		
	}	
	
	private XMLGregorianCalendar getFechaCreacion(String fecha, DateFormat format) {
		//System.out.println("getFechaCreacion-XMLGregorianCalendar::Before parse date: " + fecha);	
		//DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		XMLGregorianCalendar xmlGregCal = null;
		if(dateList.containsKey(fecha))
		{
			xmlGregCal = dateList.get(fecha);
		}
		else {
		
			try {
				date = format.parse(fecha);
				xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
				dateList.put(fecha, xmlGregCal);
				System.out.println("getFechaCreacion-XMLGregorianCalendar::Alfer parse date and Add: " + xmlGregCal);
			} catch (ParseException | DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return xmlGregCal;
	}	
	
	
	private String getFechaCreacionR1(String fecha) {
		//System.out.println("getFechaCreacionR1::Before parse date: " + fecha);	
		if( (null == fecha) || (fecha.equals(INIT_DATE)) ) {
			return ALTA;
		}
		return MODIFICACION;
	}
	
}