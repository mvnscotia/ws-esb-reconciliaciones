//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.15 at 11:04:16 a. m. CDT 
//


package com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObtenerInventarioFullTypeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObtenerInventarioFullTypeRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tran_TipoObjeto" type="{http://www.banorte.com/ws/esb/Reconciliaciones}CadenaLGTreinta"/>
 *         &lt;element name="Tran_Aplicacion" type="{http://www.banorte.com/ws/esb/Reconciliaciones}CadenaLGTreintaYDos"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenerInventarioFullTypeRequest", propOrder = {
    "tranTipoObjeto",
    "tranAplicacion"
})
public class ObtenerInventarioFullTypeRequest {

    @XmlElement(name = "Tran_TipoObjeto", required = true)
    protected String tranTipoObjeto;
    @XmlElement(name = "Tran_Aplicacion", required = true)
    protected String tranAplicacion;

    /**
     * Gets the value of the tranTipoObjeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranTipoObjeto() {
        return tranTipoObjeto;
    }

    /**
     * Sets the value of the tranTipoObjeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranTipoObjeto(String value) {
        this.tranTipoObjeto = value;
    }

    /**
     * Gets the value of the tranAplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranAplicacion() {
        return tranAplicacion;
    }

    /**
     * Sets the value of the tranAplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranAplicacion(String value) {
        this.tranAplicacion = value;
    }

}
