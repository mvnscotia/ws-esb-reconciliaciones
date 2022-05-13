//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.12 at 07:33:43 p.m. CDT 
//


package com.banorte.ws.esb.reconciliaciones.ObtenerObjeto.filter.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObtenerObjetoFiltradaInType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObtenerObjetoFiltradaInType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tran_TipoObjeto" type="{http://www.banorte.com/ws/esb/Reconciliaciones}CadenaLGTreinta"/>
 *         &lt;element name="Tran_Aplicacion" type="{http://www.banorte.com/ws/esb/Reconciliaciones}CadenaLGTreintaYDos"/>
 *         &lt;element name="Objetos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Objeto" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Tran_IdTipoObjeto" type="{http://www.banorte.com/ws/esb/Reconciliaciones}CadenaLGSesentaYCuatro"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenerObjetoFiltradaInType", propOrder = {
    "tranTipoObjeto",
    "tranAplicacion",
    "objetos"
})
public class ObtenerObjetoFiltradaInType {

    @XmlElement(name = "Tran_TipoObjeto", required = true)
    protected String tranTipoObjeto;
    @XmlElement(name = "Tran_Aplicacion", required = true)
    protected String tranAplicacion;
    @XmlElement(name = "Objetos", required = true)
    protected ObtenerObjetoFiltradaInType.Objetos objetos;

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

    /**
     * Gets the value of the objetos property.
     * 
     * @return
     *     possible object is
     *     {@link ObtenerObjetoFiltradaInType.Objetos }
     *     
     */
    public ObtenerObjetoFiltradaInType.Objetos getObjetos() {
        return objetos;
    }

    /**
     * Sets the value of the objetos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObtenerObjetoFiltradaInType.Objetos }
     *     
     */
    public void setObjetos(ObtenerObjetoFiltradaInType.Objetos value) {
        this.objetos = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Objeto" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Tran_IdTipoObjeto" type="{http://www.banorte.com/ws/esb/Reconciliaciones}CadenaLGSesentaYCuatro"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "objeto"
    })
    public static class Objetos {

        @XmlElement(name = "Objeto", required = true)
        protected List<ObtenerObjetoFiltradaInType.Objetos.Objeto> objeto;

        /**
         * Gets the value of the objeto property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the objeto property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getObjeto().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ObtenerObjetoFiltradaInType.Objetos.Objeto }
         * 
         * 
         */
        public List<ObtenerObjetoFiltradaInType.Objetos.Objeto> getObjeto() {
            if (objeto == null) {
                objeto = new ArrayList<ObtenerObjetoFiltradaInType.Objetos.Objeto>();
            }
            return this.objeto;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Tran_IdTipoObjeto" type="{http://www.banorte.com/ws/esb/Reconciliaciones}CadenaLGSesentaYCuatro"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "tranIdTipoObjeto"
        })
        public static class Objeto {

            @XmlElement(name = "Tran_IdTipoObjeto", required = true)
            protected String tranIdTipoObjeto;

            /**
             * Gets the value of the tranIdTipoObjeto property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTranIdTipoObjeto() {
                return tranIdTipoObjeto;
            }

            /**
             * Sets the value of the tranIdTipoObjeto property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTranIdTipoObjeto(String value) {
                this.tranIdTipoObjeto = value;
            }

        }

    }

}
