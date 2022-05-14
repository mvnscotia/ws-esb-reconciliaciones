package com.banorte.ws.esb.reconciliaciones.ws;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;
import org.w3c.dom.Node;

import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObjectFactory;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaInType;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutType;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutType.Objetos.Objeto;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutType.Relaciones;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutType.Relaciones.Relacion;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFiltradoOut;
import com.banorte.ws.esb.reconciliaciones.requestHeaders.schema.HeaderRequestType;
import com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.HeaderResponseType;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerObjetoFilterServiceImpl;
import com.banorte.ws.esb.reconciliaciones.util.Props;




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
	Props PropsObj;
	
	private Map<String, XMLGregorianCalendar> dateList ;
	
	//@SoapAction(value = "http://example.com/TicketAgent/listFlights")
	//@SoapAction(value = "http://www.banorte.com/ws/esb/Reconciliaciones/ObtenerObjetoFiltradaIn")
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerObjetoFiltradaIn")
	@ResponsePayload
	public JAXBElement<ObtenerObjetoFiltradaOutType> getObtenerObjetoFiltradaInType(
			@RequestPayload JAXBElement<ObtenerObjetoFiltradaInType> request, 
			@SoapHeader(value = "{http://www.banorte.com/ws/esb/general/Headers}HeaderReq") SoapHeaderElement soapRequestHeaderElement,
			@SoapHeader(value = "{http://www.banorte.com/ws/esb/general/Headers}HeaderRes") SoapHeaderElement soapResponseHeaderElement) {
		
		String idOperacion = "", tokenOperacion = "";
		JAXBContext responseContext = null;
		
		try {
			JAXBContext requestContext = JAXBContext.newInstance(com.banorte.ws.esb.reconciliaciones.requestHeaders.schema.ObjectFactory.class);
			Unmarshaller requestUnmarshaller = requestContext.createUnmarshaller();
			
			JAXBElement<HeaderRequestType> requestHeaders = (JAXBElement<HeaderRequestType>) requestUnmarshaller.unmarshal(soapRequestHeaderElement.getSource());
			HeaderRequestType requestSoapHeaders = requestHeaders.getValue();
			idOperacion = requestSoapHeaders.getAcceso().getIdOperacion();
			tokenOperacion = requestSoapHeaders.getAcceso().getTokenOperacion();
			
			
			
			com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.ObjectFactory responseHeaderObjFactory = new com.banorte.ws.esb.reconciliaciones.responseHeaders.schema.ObjectFactory();
			
			HeaderResponseType headerResponseType = responseHeaderObjFactory.createHeaderResponseType();
			headerResponseType.setIdOperacion(idOperacion);
			headerResponseType.setTokenOperacion(tokenOperacion);
			
			JAXBElement<HeaderResponseType> headersResResponse = responseHeaderObjFactory.createHeaderRes(headerResponseType);
			
			responseContext = JAXBContext.newInstance(HeaderResponseType.class);
			Marshaller marshaller= responseContext.createMarshaller();
			marshaller.marshal(headersResResponse, soapResponseHeaderElement.getResult());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		/*---------------------------------------------------------------------------------------------------------------*/
		ObjectFactory objectFactory = new ObjectFactory();
		
		ObtenerObjetoFiltradaOutType objetoFiltradaResponseFactory = objectFactory.createObtenerObjetoFiltradaOutType();
		
		JAXBElement<ObtenerObjetoFiltradaOutType> objetoFiltradaRespose = objectFactory.createObtenerObjetoFiltradaOut(objetoFiltradaResponseFactory);
		
		ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject= new ObtenerObjetoFiltradaOutType();
		
		obtenerObjetoFilterResponseObject.setObjetos(new ObtenerObjetoFiltradaOutType.Objetos());
		//N1,N2,R1,R3,FULL			
		String pUsuario="";
		String pTerminal="";
		String pClaveAplicativo=request.getValue().getTranAplicacion();
		String pVar=request.getValue().getTranTipoObjeto();
		String type_query=PropsObj.find_coincidence(pVar);/* Se busca coincidencia de acuerdo a lo establecido por el cliente*/		
		
		/*We need to change this part*/
//		List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut = null;
//		try{
//			listObtenerObjetoFilterOut = obtenerObjetoFilterService.getObjetoFiltradaResponse(pUsuario,pTerminal,p_var);
//		}catch(Exception e) {
//			log.error("ws-esb-reconciliaciones-An exception occurred in service()", e);
//		}
//		
		List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut = obtenerObjetoFilterService.getObjetoFiltradaResponse(pUsuario,pTerminal,pVar, pClaveAplicativo);
		
		//Objeto listResponseObjects= new Objeto();
		
		if (listObtenerObjetoFilterOut != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar cal = new GregorianCalendar();
			dateList = new HashMap<String, XMLGregorianCalendar>();
			
			switch (type_query) {
			case "N1":
				populateN1Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format, cal);
				break;
			case "N2":
				populateN2Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format, cal);
				break;
			case "R1":
				populateR1Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format, cal);
				break;
			case "R3":
				populateR3Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format, cal);
				break;
			default:
				break;
			}
		}
		
		objetoFiltradaRespose.setValue(obtenerObjetoFilterResponseObject);
		
		return objetoFiltradaRespose;
		
	}
	

	
	private void populateN1Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, DateFormat format, GregorianCalendar cal) {
		Objeto listResponseObjects= new Objeto();
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			listResponseObjects= new Objeto();
			listResponseObjects.setTranIdTipoObjeto(fullObj.getVal1());
			listResponseObjects.setTranObjeto(fullObj.getVal2());
			listResponseObjects.setTranDescripcion(fullObj.getVal3());
			listResponseObjects.setTranFechaCreacion(getFechaCreacion(fullObj.getVal5(), format, cal));
			listResponseObjects.setTranIdUsuario(fullObj.getVal6());
			listResponseObjects.setTranFechaModificacion(getFechaCreacion(fullObj.getVal7(), format, cal));
			
			obtenerObjetoFilterResponseObject.getObjetos().getObjeto().add(listResponseObjects);
		}		
	}
	
	private void populateN2Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, DateFormat format, GregorianCalendar cal) {
		Objeto listResponseObjects= new Objeto();
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			listResponseObjects= new Objeto();
			listResponseObjects.setTranIdTipoObjeto(fullObj.getVal1());
			listResponseObjects.setTranObjeto(fullObj.getVal4());
			listResponseObjects.setTranDescripcion(fullObj.getVal5());
			listResponseObjects.setTranTipoOperacion(fullObj.getVal6());
			listResponseObjects.setTranFechaCreacion(getFechaCreacion(fullObj.getVal8(), format, cal));
			listResponseObjects.setTranIdUsuario(fullObj.getVal9());
			listResponseObjects.setTranFechaModificacion(getFechaCreacion(fullObj.getVal10(), format, cal));
			
			obtenerObjetoFilterResponseObject.getObjetos().getObjeto().add(listResponseObjects);
		}
	}
	
	private void populateR1Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, DateFormat format, GregorianCalendar cal) {
		Relacion relacionAttributeList = new Relacion();
		Relaciones relacionesList = new Relaciones();
		
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			relacionAttributeList = new Relacion();
			relacionAttributeList.setTranIdPerfil(fullObj.getVal1());
			relacionAttributeList.setTranIdTransaccion(fullObj.getVal2());  
			relacionAttributeList.setTranTipoOperacion(getFechaCreacionR1(fullObj.getVal3()));
			relacionAttributeList.setTranIdUsuario(fullObj.getVal4());
			relacionAttributeList.setTranFechaOperacion(getFechaCreacion(fullObj.getVal3(), format, cal));
			
			relacionesList = new Relaciones();
			relacionesList.getRelacion().add(relacionAttributeList);
			
			obtenerObjetoFilterResponseObject.getRelaciones().add(relacionesList);
		}
		
	}	
	
	private void populateR3Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, DateFormat format, GregorianCalendar cal) {
		Relacion relacionAttributeList = new Relacion();
		Relaciones relacionesList = new Relaciones();
		
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			relacionAttributeList = new Relacion();
			relacionAttributeList.setTranIdPerfil(fullObj.getVal2()); 
			relacionAttributeList.setTranIdTransaccion("");
			relacionAttributeList.setTranTipoOperacion("Alta");
			relacionAttributeList.setTranIdUsuario(fullObj.getVal1()); 
			relacionAttributeList.setTranFechaOperacion(getFechaCreacion(fullObj.getVal3(), format, cal));
			
			relacionesList = new Relaciones();
			relacionesList.getRelacion().add(relacionAttributeList);
			
			obtenerObjetoFilterResponseObject.getRelaciones().add(relacionesList);
		}		
	}	
	
	private XMLGregorianCalendar getFechaCreacion(String fecha, DateFormat format, GregorianCalendar cal) {
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
				cal.setTime(date);
				xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
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