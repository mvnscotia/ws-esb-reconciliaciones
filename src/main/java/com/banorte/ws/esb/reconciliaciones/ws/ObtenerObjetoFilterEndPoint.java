package com.banorte.ws.esb.reconciliaciones.ws;

import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;

import org.hibernate.engine.jdbc.NonContextualLobCreator;
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
import com.banorte.ws.esb.reconciliaciones.service.IObtenerObjetoFilterService;
import com.banorte.ws.esb.reconciliaciones.service.ObtenerObjetoFilterServiceImpl;
import com.banorte.ws.esb.reconciliaciones.util.Props;
import com.banorte.ws.esb.reconciliaciones.util.Tranidtipoobjeto;
import com.banorte.ws.esb.reconciliaciones.util.Singleton_handling_errors;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


//import weblogic.logging.NonCatalogLogger;

@Endpoint
public class ObtenerObjetoFilterEndPoint {

	private static final String NAMESPACE_URI = "http://www.banorte.com/ws/esb/Reconciliaciones";
	private static final String ALTA = "Alta";
	private static final String MODIFICACION = "Modificacion";
	private static final String INIT_DATE = "0001-01-01";

	@Autowired
	private IObtenerObjetoFilterService obtenerObjetoFilterService;
	
	@Autowired
	Props propsObj;
	
	private Map<String, XMLGregorianCalendar> dateList ;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerObjetoFiltradaIn")
	@ResponsePayload
	public JAXBElement<ObtenerObjetoFiltradaOutType> getObtenerObjetoFiltradaInType(
			@RequestPayload JAXBElement<ObtenerObjetoFiltradaInType> request,
			MessageContext messageContext)throws JAXBException, TransformerException, SerialException, SQLException {
		
		/*Initialize error handling*/
		Singleton_handling_errors.getInstance().setId("1");
		Singleton_handling_errors.getInstance().setMessange_user("Exitosa");
		Singleton_handling_errors.getInstance().setMessage_detail("Solicitud Exitosa");
		
		/*Get request headers from message*/
		Map<String,Object> requestHeadersParams = propsObj.getRequestHeaders(messageContext);
		
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
		
		System.out.println("ObtenerObjetoFiltrada - getTranTipoObjeto: "+pVar+" getTranAplicacion: "+pClaveAplicativo);
		
		StringBuilder stringBjson=new StringBuilder(request.getValue().getObjetos().getObjeto().size());
		stringBjson.append(new Gson().toJson( request.getValue().getObjetos().getObjeto() ).toLowerCase());
		
		Gson gson = new Gson();
		JsonElement json = gson.fromJson( stringBjson.toString() , JsonElement.class);
		Type listType = new TypeToken<List<Tranidtipoobjeto>>() {}.getType();

		List<Tranidtipoobjeto> obj_trantipoobjeto = new Gson().fromJson(json, listType);
		
		
		//List<Tranidtipoobjeto> obj_trantipoobjeto = gson.fromJson(stringBjson.toString().toLowerCase(), new TypeToken<List<Tranidtipoobjeto>>(){}.getType());
		
		//ArrayList<Tranidtipoobjeto> obj_trantipoobjeto= (ArrayList<Tranidtipoobjeto>) new Gson().fromJson(stringBjson.toString().toLowerCase(),new TypeToken<ArrayList<Tranidtipoobjeto>>() {}.getType());
		
		//List<Tranidtipoobjeto> obj_trantipoobjeto = new Gson().fromJson(stringBjson.toString().toLowerCase(),Tranidtipoobjeto.class);
		System.out.println(stringBjson.toString());
		
		System.out.println(obj_trantipoobjeto.size());
		
		int tamanio=obj_trantipoobjeto.size()/3;
		String json1 ="[{\"tranidtipoobjeto\":\"\"}]";
		String json2 ="[{\"tranidtipoobjeto\":\"\"}]";;
		String json3 ="[{\"tranidtipoobjeto\":\"\"}]";;
		System.out.println("El tamaño de tocken "+tamanio);
		if(tamanio>20)
		{
			System.out.println("El tamaño de tocken "+tamanio);
			json1 = new Gson().toJson(obj_trantipoobjeto.subList(0, tamanio));
			json2 = new Gson().toJson(obj_trantipoobjeto.subList(tamanio, (tamanio+tamanio) ));
			json3 = new Gson().toJson(obj_trantipoobjeto.subList((tamanio+tamanio), obj_trantipoobjeto.size()));
		}
		else
		{
			json1 = stringBjson.toString();
		}

		System.out.println("\n******"+json1);
		System.out.println("\n\n******"+json2);
		System.out.println("\n\n******"+json3);
		//List<Tranidtipoobjeto> = new ArrayList<>(obj_trantipoobjeto.getWorkers().values());		
		//stringBjson.append(new Gson().toJson( request.getValue().getObjetos().getObjeto() ).toLowerCase());		
		//Clob myClob = new javax.sql.rowset.serial.SerialClob(stringBjson.toString().toCharArray());		
		//String json = new Gson().toJson( requestx.getValue().getObjetos().getObjeto() );
		//json=json.toLowerCase();
		
		String type_query=propsObj.find_coincidence(pVar);/* Se busca coincidencia de acuerdo a lo establecido por el cliente*/		
		
		List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut = null;
		Clob myClob = NonContextualLobCreator.INSTANCE.createClob(stringBjson.toString());
		
		//System.out.println("******"+stringBjson.toString());
		
		//System.out.println("**///////////////////////////////////////***"+myClob);
		
		if( stringBjson.toString().equals("[{}]") )//No hay valores
		{
			Singleton_handling_errors.getInstance().setId("4");
			Singleton_handling_errors.getInstance().setMessange_user("Error en la logica de negocio");
			Singleton_handling_errors.getInstance().setMessage_detail("Peticion incorrecta");
		}
		else
		{
			listObtenerObjetoFilterOut = obtenerObjetoFilterService.getObjetoFiltradaResponse(pUsuario,pTerminal,json1,json2,json3,type_query, pClaveAplicativo);
		}
		
		if(Singleton_handling_errors.getInstance().getId().equals("1"))
		{
			if(!type_query.equals("not_found"))
			{
				if (!listObtenerObjetoFilterOut.isEmpty()) {
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					dateList = new HashMap<String, XMLGregorianCalendar>();
					
					switch (type_query) {
						case "N1":
							obtenerObjetoFilterResponseObject.setObjetos(new ObtenerObjetoFiltradaOutType.Objetos());
							populateN1Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format, request);
							break;
						case "N2":
							obtenerObjetoFilterResponseObject.setObjetos(new ObtenerObjetoFiltradaOutType.Objetos());
							populateN2Response(listObtenerObjetoFilterOut, obtenerObjetoFilterResponseObject, format, request);
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
				else {
					Singleton_handling_errors.getInstance().setId("4");
					Singleton_handling_errors.getInstance().setMessange_user("Error en la logica de negocio");
					Singleton_handling_errors.getInstance().setMessage_detail("Peticion incorrecta");					
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
		/*Set response headers*/
		propsObj.setResponseHeaders(requestHeadersParams);
		/********************************************************************/
		
		return objetoFiltradaRespose;
		
	}
	

	
	private void populateN1Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut,
									ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, 
									DateFormat format,
									JAXBElement<ObtenerObjetoFiltradaInType> request) {
		Objeto listResponseObjects= new Objeto();
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			listResponseObjects= new Objeto();
			listResponseObjects.setTranIdGrupo(request.getValue().getTranTipoObjeto());
			listResponseObjects.setTranNombreGrupo("");
			listResponseObjects.setTranIdTipoObjeto(fullObj.getVal1());
			listResponseObjects.setTranObjeto(fullObj.getVal2());
			listResponseObjects.setTranDescripcion(fullObj.getVal3());
			listResponseObjects.setTranTipoOperacion(getFechaCreacionR1(propsObj.removeSpaceInString(fullObj.getVal7())));
			listResponseObjects.setTranFechaCreacion(convertStringToGregrianCalendar(propsObj.removeSpaceInString(fullObj.getVal5()), format));
			listResponseObjects.setTranIdUsuario(propsObj.removeSpaceInString(fullObj.getVal6()));
			listResponseObjects.setTranFechaModificacion(convertStringToGregrianCalendar(propsObj.removeSpaceInString(fullObj.getVal7()), format));
			listResponseObjects.setTranExtension("");
			
			obtenerObjetoFilterResponseObject.getObjetos().getObjeto().add(listResponseObjects);
		}		
	}
	
	private void populateN2Response(List<ObtenerInventarioFiltradoOut> listObtenerObjetoFilterOut, 
									ObtenerObjetoFiltradaOutType obtenerObjetoFilterResponseObject, 
									DateFormat format,
									JAXBElement<ObtenerObjetoFiltradaInType> request) {
		Objeto listResponseObjects= new Objeto();
		for (ObtenerInventarioFiltradoOut fullObj : listObtenerObjetoFilterOut) {
			listResponseObjects= new Objeto();
			listResponseObjects.setTranIdGrupo(request.getValue().getTranTipoObjeto());
			listResponseObjects.setTranNombreGrupo("");
			listResponseObjects.setTranIdTipoObjeto(fullObj.getVal1());
			listResponseObjects.setTranObjeto(fullObj.getVal4());
			listResponseObjects.setTranDescripcion(fullObj.getVal5());
			listResponseObjects.setTranTipoOperacion(getFechaCreacionR1(propsObj.removeSpaceInString(fullObj.getVal10())));
			listResponseObjects.setTranFechaCreacion(convertStringToGregrianCalendar(fullObj.getVal8(), format));
			listResponseObjects.setTranFechaModificacion(convertStringToGregrianCalendar(propsObj.removeSpaceInString(fullObj.getVal10()), format));
			listResponseObjects.setTranIdUsuario(propsObj.removeSpaceInString(fullObj.getVal9()));
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
			relacionAttributeList.setTranFechaOperacion(convertStringToGregrianCalendar(fullObj.getVal3(), format));
			
			relacionesList = new Relaciones();
			relacionesList.setTranIdTipoObjeto(fullObj.getVal1()+":"+fullObj.getVal2());
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
			relacionAttributeList.setTranFechaOperacion(convertStringToGregrianCalendar(fullObj.getVal3(), format));
			
			relacionesList = new Relaciones();
			relacionesList.setTranIdTipoObjeto(fullObj.getVal1()+":"+fullObj.getVal2());
			relacionesList.getRelacion().add(relacionAttributeList);
			
			obtenerObjetoFilterResponseObject.getRelaciones().add(relacionesList);
		}		
	}	
	
	private XMLGregorianCalendar convertStringToGregrianCalendar(String fecha, DateFormat format) {
		Date date;
		XMLGregorianCalendar xmlGregCal = null;
		if(fecha.isEmpty()) {
			fecha = INIT_DATE;
		}
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
		if( (null == fecha) || (fecha.isEmpty()) || (fecha.equals(INIT_DATE)) ) {
			return ALTA;
		}
		return MODIFICACION;
	}
	
}