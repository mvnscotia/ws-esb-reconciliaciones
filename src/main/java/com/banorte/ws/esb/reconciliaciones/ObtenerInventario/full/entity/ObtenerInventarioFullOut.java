package com.banorte.ws.esb.reconciliaciones.ObtenerInventario.full.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

@Entity
@NamedStoredProcedureQuery(name = "Get.get_extract_reconciliaciones_test",
procedureName = "get_extract_reconciliaciones_test", parameters = {
@StoredProcedureParameter(mode = ParameterMode.IN, name = "pUsuario", type = String.class),
@StoredProcedureParameter(mode = ParameterMode.IN, name = "pTerminal", type = String.class),
@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_var", type = String.class),
@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_get_info_ap", type = Object.class)
})
public class ObtenerInventarioFullOut{
	
	@Id
	private String REPOIGID;
	@Column
	private String REPOIGNIVEL;
	@Column
	private String REPOIGCADENA;
	@Column
	private String REPOIGFECHAGEN;
	
	public String getREPOIGNIVEL() {
		return REPOIGNIVEL;
	}
	public void setREPOIGNIVEL(String rEPOIGNIVEL) {
		REPOIGNIVEL = rEPOIGNIVEL;
	}
	public String getREPOIGFECHAGEN() {
		return REPOIGFECHAGEN;
	}
	public void setREPOIGFECHAGEN(String rEPOIGFECHAGEN) {
		REPOIGFECHAGEN = rEPOIGFECHAGEN;
	}
	
	public String getREPOIGCADENA() {
		return REPOIGCADENA;
	}
	public void setREPOIGCADENA(String rEPOIGCADENA) {
		REPOIGCADENA = rEPOIGCADENA;
	}
	public String getREPOIGID() {
		return REPOIGID;
	}
	public void setREPOIGID(String rEPOIGID) {
		REPOIGID = rEPOIGID;
	}
	
}
