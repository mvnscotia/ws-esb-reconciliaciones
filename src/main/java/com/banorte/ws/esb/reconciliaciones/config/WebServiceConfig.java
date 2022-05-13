package com.banorte.ws.esb.reconciliaciones.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;


@EnableWs
@Configuration
@ComponentScan(basePackages = {"com.banorte.ws.esb.reconciliaciones.config.WebServiceConfig"})
public class WebServiceConfig extends WsConfigurerAdapter{

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
	
//	@Bean(name="reconciliaciones")
//	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchemaCollection  requestSchemaCollection) {
//		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
//		defaultWsdl11Definition.setPortTypeName("Reconciliaciones");
//		defaultWsdl11Definition.setLocationUri("/sifews");
//		defaultWsdl11Definition.setTargetNamespace("http://www.banorte.com/ws/esb/Reconciliaciones");
//		//defaultWsdl11Definition.setSchema(userSchema);
//		defaultWsdl11Definition.setSchemaCollection(requestSchemaCollection);
//		return defaultWsdl11Definition;
//	} 
	
	@Bean
	public XsdSchemaCollection requestSchemaCollection(/* XsdSchema ObtenerFiltradoFull, */  XsdSchema ObtenerObjetoFiltrado) {
	    return new XsdSchemaCollection() {

	        public XsdSchema[] getXsdSchemas() {
				return new XsdSchema[] { /* ObtenerFiltradoFull, */  ObtenerObjetoFiltrado};
	        }

	        public XmlValidator createValidator() {
	            throw new UnsupportedOperationException();
	        }
	        
	    };
	}
	
	
	
	@Bean(name = "requestHeadersV1xsd3") 
	public XsdSchema requestHeadersV1xsd3() 
	{ 
		return new SimpleXsdSchema(new ClassPathResource("xsd/requestHeadersV1xsd3.xsd")); 
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
		payloadValidatingInterceptor.setSchema(new ClassPathResource("xsd/ObtenerObjetoFiltrado.xsd"));
		//payloadValidatingInterceptor.setXsdSchemaCollection(requestSchemaCollection);
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
		interceptors.add(payloadValidatingInterceptor());
		interceptors.add(securityInterceptor());
	}	
	
	
}
