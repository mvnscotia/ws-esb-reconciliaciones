package com.banorte.ws.esb.reconciliaciones.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
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
	public XsdSchemaCollection requestSchemaCollection(/* XsdSchema ObtenerFiltradoFull, */ XsdSchema ObtenerObjetoFiltrado) {
	    return new XsdSchemaCollection() {

	        public XsdSchema[] getXsdSchemas() {
				return new XsdSchema[] { /* ObtenerFiltradoFull, */ ObtenerObjetoFiltrado};
	        }

	        public XmlValidator createValidator() {
	            throw new UnsupportedOperationException();
	        }
	        
	    };
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
}
