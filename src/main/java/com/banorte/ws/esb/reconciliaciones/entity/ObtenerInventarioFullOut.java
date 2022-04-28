package com.banorte.ws.esb.reconciliaciones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ObtenerInventarioFullOut{
	@Id
	String REPOIGID;
	@Column
	String REPOIGNIVEL;
	@Column
	String val1;
	@Column
	String val2; 
	@Column
	String val3;
	@Column
	String val4;
	@Column
	String val5;
	@Column
	String val6;
	@Column
	String val7;
	@Column
	String val8;
	@Column
	String val9;
	@Column
	String val10;
	@Column
	String REPOIGFECHAGEN;
	
	public String getREPOIGID() {
		return REPOIGID;
	}
	public void setREPOIGID(String rEPOIGID) {
		REPOIGID = rEPOIGID;
	}
	public String getREPOIGNIVEL() {
		return REPOIGNIVEL;
	}
	public void setREPOIGNIVEL(String rEPOIGNIVEL) {
		REPOIGNIVEL = rEPOIGNIVEL;
	}
	public String getVal1() {
		return val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	public String getVal2() {
		return val2;
	}
	public void setVal2(String val2) {
		this.val2 = val2;
	}
	public String getVal3() {
		return val3;
	}
	public void setVal3(String val3) {
		this.val3 = val3;
	}
	public String getVal4() {
		return val4;
	}
	public void setVal4(String val4) {
		this.val4 = val4;
	}
	public String getVal5() {
		return val5;
	}
	public void setVal5(String val5) {
		this.val5 = val5;
	}
	public String getVal6() {
		return val6;
	}
	public void setVal6(String val6) {
		this.val6 = val6;
	}
	public String getVal7() {
		return val7;
	}
	public void setVal7(String val7) {
		this.val7 = val7;
	}
	public String getVal8() {
		return val8;
	}
	public void setVal8(String val8) {
		this.val8 = val8;
	}
	public String getVal9() {
		return val9;
	}
	public void setVal9(String val9) {
		this.val9 = val9;
	}
	public String getVal10() {
		return val10;
	}
	public void setVal10(String val10) {
		this.val10 = val10;
	}
	public String getREPOIGFECHAGEN() {
		return REPOIGFECHAGEN;
	}
	public void setREPOIGFECHAGEN(String rEPOIGFECHAGEN) {
		REPOIGFECHAGEN = rEPOIGFECHAGEN;
	}	
}