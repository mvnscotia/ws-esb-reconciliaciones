package com.banorte.ws.esb.reconciliaciones.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.validation.XmlValidatorFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;


@EnableWs
@Configuration
@ComponentScan(basePackages = {"com.banorte.ws.esb.reconciliaciones.config.WebServiceConfig"})
public class WebServiceConfig extends WsConfigurerAdapter /* implements WebServiceMessageCallback */ {

	@Bean 
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/sifews/*");
	}
	
	@Bean(name="reconciliaciones")
	public SimpleWsdl11Definition deSimpleWsdl11Definition() {
		return new SimpleWsdl11Definition(new ClassPathResource("reconciliaciones.wsdl"));
	}	
	
	/*
	@Bean(name="reconciliaciones")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchemaCollection  requestSchemaCollection) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("Reconciliaciones");
		defaultWsdl11Definition.setLocationUri("/sifews");
		defaultWsdl11Definition.setTargetNamespace("http://www.banorte.com/ws/esb/Reconciliaciones");
		//defaultWsdl11Definition.setSchema(userSchema);
		defaultWsdl11Definition.setSchemaCollection(requestSchemaCollection);
		return defaultWsdl11Definition;
	} 
	*/
	
	/*
	@Bean
	public XsdSchemaCollection requestSchemaCollection( XsdSchema ObtenerFiltradoFull,   XsdSchema ObtenerObjetoFiltrado, XsdSchema requestHeadersV1xsd3) {
	    return new XsdSchemaCollection() {

	        public XsdSchema[] getXsdSchemas() {
				return new XsdSchema[] { ObtenerFiltradoFull, ObtenerObjetoFiltrado, requestHeadersV1xsd3};
	        }

	        public XmlValidator createValidator() {
	            throw new UnsupportedOperationException();
	        }
	        
	    };
	}
	*/
	
	@Bean(name = "wsSecurityHeaders") 
	public XsdSchema wsSecurityHeaders() 
	{ 
		return new SimpleXsdSchema(new ClassPathResource("xsd/wsSecurityHeaders.xsd")); 
	}	
	
	/**
	 * @return requestHeadersV1xsd3
	 * This are the general request headers for WSDL operations
	 */
	@Bean(name = "requestHeadersV1xsd3") 
	public XsdSchema requestHeadersV1xsd3() 
	{ 
		return new SimpleXsdSchema(new ClassPathResource("xsd/requestHeadersV1xsd3.xsd")); 
	}
	
	/**
	 * @return responseHeadersV1xsd4
	 * This are the general response headers for WSDL operations
	 */
	@Bean(name = "responseHeadersV1xsd4") 
	public XsdSchema responseHeadersV1xsd4() 
	{ 
		return new SimpleXsdSchema(new ClassPathResource("xsd/responseHeadersV1xsd4.xsd")); 
	}		
	
	@Bean(name = "ObtenerFiltradoFull") 
	public XsdSchema ObtenerFiltradoFullSchema() 
	{ 
		return new SimpleXsdSchema(new ClassPathResource("xsd/ObtenerFiltradoFull.xsd")); 
	}
	 
	
	@Bean(name = "ObtenerObjetoFiltrado")
	public XsdSchema ObtenerObjetoFiltradoSchema()
	{
		return new SimpleXsdSchema(new ClassPathResource("xsd/ObtenerObjetoFiltrado.xsd"));
	}
	
	XsdSchemaCollection schemaCollection = new XsdSchemaCollection() {
        @Override
        public XsdSchema[] getXsdSchemas() {
            return null;
        }

        @Override
        public XmlValidator createValidator() {
            try {
                XmlValidator xmlValidator = XmlValidatorFactory.createValidator(getSchemas(), "http://www.w3.org/2001/XMLSchema");

                return xmlValidator;
            } catch (IOException e) {
                //logger.error(e.getLocalizedMessage());
            }
            return null;
        }
    };	
	
	public Resource[] getSchemas() {
	    List<Resource> schemaResources = new ArrayList<>();
	    //schemaResources.add(new ClassPathResource("xsd/requestHeadersV1xsd3.xsd"));
	    schemaResources.add(new ClassPathResource("xsd/ObtenerObjetoFiltrado.xsd"));
	    schemaResources.add(new ClassPathResource("xsd/ObtenerFiltradoFull.xsd"));
	    
	    //schemaResources.add(new ClassPathResource("thirdService.xsd"));
	    return schemaResources.toArray(new Resource[schemaResources.size()]);
	}	
	
	/**
	 * @return payloadLoggingInterceptor
	 * Configure Logging Interceptors
	 * To log the payload of our SOAP messages we’ll add the below beans in the WebServiceConfig class.
	 */
	@Bean
	PayloadLoggingInterceptor payloadLoggingInterceptor() {
		return new PayloadLoggingInterceptor();
	}

	/**
	 * @return payloadValidatingInterceptor
	 * Configure Logging Interceptors
	 * To log the payload of our SOAP messages we’ll add the below beans in the WebServiceConfig class.	 * 
	 */
	@Bean
	PayloadValidatingInterceptor payloadValidatingInterceptor() {
		final PayloadValidatingInterceptor payloadValidatingInterceptor = new PayloadValidatingInterceptor();
		//payloadValidatingInterceptor.setSchema(new ClassPathResource("xsd/ObtenerObjetoFiltrado.xsd"));
		payloadValidatingInterceptor.setXsdSchemaCollection(schemaCollection);
		return payloadValidatingInterceptor;
	}	
	
	/**
	 * @return securityInterceptor
	 * Configure Security Interceptors
	 * XwsSecurityInterceptor will intercept the request and validate the username & password by the help of SimplePasswordValidationCallbackHandler.
	 */
	@Bean
	XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		securityInterceptor.setCallbackHandler(callbackHandler());
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return securityInterceptor;
	}

	/**
	 * @return callbackHandler
	 * Configure Security Interceptors
	 * XwsSecurityInterceptor will intercept the request and validate the username & password by the help of SimplePasswordValidationCallbackHandler.	 * 
	 */
	@Bean
	SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
		callbackHandler.setUsersMap(Collections.singletonMap("VUdfQ09OQ0VOVFJBX0xHQ1k=", "QU1EZ3IwRDBucw=="));
		return callbackHandler;
	}	
	
	/**
	 *Add interceptor to the chain
	 */
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(payloadLoggingInterceptor());
		//interceptors.add(payloadValidatingInterceptor());
		//interceptors.add(securityInterceptor());
	}

	/*
	 * Do not delete i am still doing testing with this method
	@Override
	public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
		// TODO Auto-generated method stub
		try {
			SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();
			ObjectFactory factory = new ObjectFactory();
			HeaderRequestType authSoapHeaders = factory.createHeaderRequestType();
			authSoapHeaders.getAcceso().setIdUsuario("admin");
			authSoapHeaders.getAcceso().setClaveAcceso("pwd123");
			JAXBElement<HeaderRequestType> headers = factory.createHeaderReq(authSoapHeaders);
			JAXBContext context = JAXBContext.newInstance(HeaderRequestType.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(marshaller, soapHeader.getResult());
			
			//JAXBElement headers = factory.createAuthSoapHeaders(AuthSoapHeaders);
			//JAXBContext context = JAXBContext.newInstance(AuthSoapHeaders.class);
			//Marshaller marshaller = context.createMarshaller();
			//marshaller.marshal(authSoapHeaders, soapHeader.getResult());
		} catch (Exception e) {
			System.out.println("error during marshalling of the SOAP headers: "+ e.getMessage());
		}		
	}
	*/	
	
	
}
