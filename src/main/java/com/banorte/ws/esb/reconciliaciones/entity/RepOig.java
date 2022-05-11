package com.banorte.ws.esb.reconciliaciones.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "REP_OIG")
public class RepOig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPOIGID")	
	private Long repoigid;
	
	@Column(name = "REPOIGNIVEL")
	private String repoignivel;
	
	@Column(name = "REPOIGCADENA")
	private String repoigcadena;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "REPOIGFECHAGEN")
	private Date repoigfechagen;
	
	

	public Long getRepoigid() {
		return repoigid;
	}

	public void setRepoigid(Long repoigid) {
		this.repoigid = repoigid;
	}

	public String getRepoignivel() {
		return repoignivel;
	}

	public void setRepoignivel(String repoignivel) {
		this.repoignivel = repoignivel;
	}

	public String getRepoigcadena() {
		return repoigcadena;
	}

	public void setRepoigcadena(String repoigcadena) {
		this.repoigcadena = repoigcadena;
	}

	public Date getRepoigfechagen() {
		return repoigfechagen;
	}

	public void setRepoigfechagen(Date repoigfechagen) {
		this.repoigfechagen = repoigfechagen;
	}
	
	

	public RepOig() {
		
	}

	public RepOig(Long repoigid) {
		this.repoigid = repoigid;
	}

	public RepOig(Long repoigid, String repoignivel, String repoigcadena, Date repoigfechagen) {
		this.repoigid = repoigid;
		this.repoignivel = repoignivel;
		this.repoigcadena = repoigcadena;
		this.repoigfechagen = repoigfechagen;
	}

	@Override
	public String toString() {
		return "RepOig [repoigid=" + repoigid + ", repoignivel=" + repoignivel + ", repoigcadena=" + repoigcadena
				+ ", repoigfechagen=" + repoigfechagen + "]";
	}
	
	
	

	
}