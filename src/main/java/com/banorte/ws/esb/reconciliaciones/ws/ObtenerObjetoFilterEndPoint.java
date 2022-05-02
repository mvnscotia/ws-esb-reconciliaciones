package com.banorte.ws.esb.reconciliaciones.ws;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObjectFactory;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaInTypeRequest;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutTypeResponse;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutTypeResponse.Objetos.Objeto;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutTypeResponse.Relaciones;
import com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema.ObtenerObjetoFiltradaOutTypeResponse.Relaciones.Relacion;
import com.banorte.ws.esb.reconciliaciones.entity.ObtenerInventarioFullOut;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerObjetoFilterServiceImpl;

@Endpoint
public class ObtenerObjetoFilterEndPoint {

	private static final String NAMESPACE_URI = "http://www.banorte.com/ws/esb/Reconciliaciones";
	private static final String ALTA = "Alta";
	private static final String MODIFICACION = "Modificacion";
	private static final String INIT_DATE = "0001-01-01";

	@Autowired
	private ObtenerObjetoFilterServiceImpl obtenerObjetoFilterService;
	
	private Map<String, XMLGregorianCalendar> dateList ;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerObjetoFiltradaInTypeRequest")
	@ResponsePayload
	public JAXBElement<ObtenerObjetoFiltradaOutTypeResponse> getObtenerObjetoFiltradaInType(
			@RequestPayload JAXBElement<ObtenerObjetoFiltradaInTypeRequest> request) {
		
		ObjectFactory objectFactory = new ObjectFactory();
		
		ObtenerObjetoFiltradaOutTypeResponse objetoFiltradaResponseFactory = objectFactory.createObtenerObjetoFiltradaOutTypeResponse();
		
		JAXBElement<ObtenerObjetoFiltradaOutTypeResponse> objetoFiltradaRespose = objectFactory.createObtenerObjetoFiltradaOutTypeResponse(objetoFiltradaResponseFactory);
		
		ObtenerObjetoFiltradaOutTypeResponse obtenerObjetoFilterResponseObject= new ObtenerObjetoFiltradaOutTypeResponse();
		
		obtenerObjetoFilterResponseObject.setObjetos(new ObtenerObjetoFiltradaOutTypeResponse.Objetos());
		//N1,N2,R1,R3,FULL			
		String pUsuario="";
		String pTerminal="";//request.getValue().getTranAplicacion();
		String p_var=request.getValue().getTranTipoObjeto();
		/*We need to change this part*/
		List<ObtenerInventarioFullOut> listObtenerObjetoFilterOut = obtenerObjetoFilterService.getObjetoFiltradaResponse(pUsuario,pTerminal,p_var);
		//Objeto listResponseObjects= new Objeto();
		
		if (listObtenerObjetoFilterOut != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar cal = new GregorianCalendar();
			dateList = new HashMap<String, XMLGregorianCalendar>();
			
			switch (p_var) {
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
	

	
	private void populateN1Response(List<ObtenerInventarioFullOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutTypeResponse obtenerObjetoFilterResponseObject, DateFormat format, GregorianCalendar cal) {
		Objeto listResponseObjects= new Objeto();
		for (ObtenerInventarioFullOut fullObj : listObtenerObjetoFilterOut) {
			
			listResponseObjects.setTranIdTipoObjeto(fullObj.getVal1());
			listResponseObjects.setTranObjeto(fullObj.getVal2());
			listResponseObjects.setTranDescripcion(fullObj.getVal3());
			listResponseObjects.setTranFechaCreacion(getFechaCreacion(fullObj.getVal5(), format, cal));
			listResponseObjects.setTranIdUsuario(fullObj.getVal6());
			listResponseObjects.setTranFechaModificacion(getFechaCreacion(fullObj.getVal7(), format, cal));
			
			obtenerObjetoFilterResponseObject.getObjetos().getObjeto().add(listResponseObjects);
		}		
	}
	
	private void populateN2Response(List<ObtenerInventarioFullOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutTypeResponse obtenerObjetoFilterResponseObject, DateFormat format, GregorianCalendar cal) {
		Objeto listResponseObjects= new Objeto();
		for (ObtenerInventarioFullOut fullObj : listObtenerObjetoFilterOut) {
			
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
	
	private void populateR1Response(List<ObtenerInventarioFullOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutTypeResponse obtenerObjetoFilterResponseObject, DateFormat format, GregorianCalendar cal) {
		Relaciones relaciones = new Relaciones();
		Relacion relacionObj = new Relacion();
		for (ObtenerInventarioFullOut fullObj : listObtenerObjetoFilterOut) {
			
			relacionObj.setTranIdPerfil(fullObj.getVal1());
			relacionObj.setTranIdTransaccion(fullObj.getVal2());  
			//relacionObj.setTranTipoOperacion(getFechaCreacionR1(fullObj.getVal3()));
			relacionObj.setTranIdUsuario(fullObj.getVal4());
			relacionObj.setTranFechaOperacion(getFechaCreacion(fullObj.getVal3(), format, cal));
			relaciones.getRelacion().add(relacionObj);
			
			obtenerObjetoFilterResponseObject.getRelaciones().add(relaciones);
		}
		
	}	
	
	private void populateR3Response(List<ObtenerInventarioFullOut> listObtenerObjetoFilterOut, ObtenerObjetoFiltradaOutTypeResponse obtenerObjetoFilterResponseObject, DateFormat format, GregorianCalendar cal) {
		Relaciones relaciones = new Relaciones();
		Relacion relacionObj = new Relacion();
		for (ObtenerInventarioFullOut fullObj : listObtenerObjetoFilterOut) {
			
			relacionObj.setTranIdPerfil(fullObj.getVal2()); 
			relacionObj.setTranIdTransaccion("");
			relacionObj.setTranTipoOperacion("Alta");
			relacionObj.setTranIdUsuario(fullObj.getVal1()); 
			relacionObj.setTranFechaOperacion(getFechaCreacion(fullObj.getVal3(), format, cal));
			relaciones.getRelacion().add(relacionObj);
			
			obtenerObjetoFilterResponseObject.getRelaciones().add(relaciones);
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