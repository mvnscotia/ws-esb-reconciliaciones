package com.banorte.ws.esb.reconciliaciones.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_RECORD_REP_OIG")
public class T_RECORD_REP_OIG{
	@Id
	Long REPOIGID;
	@Column
	String REPOIGNIVEL;
	@Column
	String VAL1;
	@Column
	String VAL2; 
	@Column
	String VAL3;
	@Column
	String VAL4;
	@Column
	String VAL5;
	@Column
	String VAL6;
	@Column
	String VAL7;
	@Column
	String VAL8;
	@Column
	String VAL9;
	@Column
	String VAL10;
	@Column
	String REPOIGFECHAGEN;
	
	public Long getREPOIGID() {
		return REPOIGID;
	}
	public void setREPOIGID(Long rEPOIGID) {
		REPOIGID = rEPOIGID;
	}
	public String getREPOIGNIVEL() {
		return REPOIGNIVEL;
	}
	public void setREPOIGNIVEL(String rEPOIGNIVEL) {
		REPOIGNIVEL = rEPOIGNIVEL;
	}
	public String getVAL1() {
		return VAL1;
	}
	public void setVAL1(String VAL1) {
		this.VAL1 = VAL1;
	}
	public String getVAL2() {
		return VAL2;
	}
	public void setVAL2(String VAL2) {
		this.VAL2 = VAL2;
	}
	public String getVAL3() {
		return VAL3;
	}
	public void setVAL3(String VAL3) {
		this.VAL3 = VAL3;
	}
	public String getVAL4() {
		return VAL4;
	}
	public void setVAL4(String VAL4) {
		this.VAL4 = VAL4;
	}
	public String getVAL5() {
		return VAL5;
	}
	public void setVAL5(String VAL5) {
		this.VAL5 = VAL5;
	}
	public String getVAL6() {
		return VAL6;
	}
	public void setVAL6(String VAL6) {
		this.VAL6 = VAL6;
	}
	public String getVAL7() {
		return VAL7;
	}
	public void setVAL7(String VAL7) {
		this.VAL7 = VAL7;
	}
	public String getVAL8() {
		return VAL8;
	}
	public void setVAL8(String VAL8) {
		this.VAL8 = VAL8;
	}
	public String getVAL9() {
		return VAL9;
	}
	public void setVAL9(String VAL9) {
		this.VAL9 = VAL9;
	}
	public String getVAL10() {
		return VAL10;
	}
	public void setVAL10(String VAL10) {
		this.VAL10 = VAL10;
	}
	public String getREPOIGFECHAGEN() {
		return REPOIGFECHAGEN;
	}
	public void setREPOIGFECHAGEN(String string) {
		REPOIGFECHAGEN = string;
	}	
}