package es.albarregas.modelos;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BloquesDatosCV")
public class BloqueDatosCv implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idBloquesDatosCv;
	private Direccion direccion;
	private boolean tipoBloque;
	private int idUsuario;
	private Date fechaInicio;
	private Date fechaFin;
	private boolean actualmente;
	private String infoAdicional;

	
	public BloqueDatosCv() {
	}

	
	@Id
	@Column(name = "IdBloquesDatosCV", unique = true, nullable = false)
	public int getIdBloquesDatosCv() {
		return this.idBloquesDatosCv;
	}

	public void setIdBloquesDatosCv(int idBloquesDatosCv) {
		this.idBloquesDatosCv = idBloquesDatosCv;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdDireccion")
	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Column(name = "TipoBloque", nullable = false)
	public boolean isTipoBloque() {
		return this.tipoBloque;
	}

	public void setTipoBloque(boolean tipoBloque) {
		this.tipoBloque = tipoBloque;
	}

	@Column(name = "IdUsuario", nullable = false)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaInicio", nullable = false, length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaFin", length = 10)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "Actualmente", nullable = false)
	public boolean isActualmente() {
		return this.actualmente;
	}

	public void setActualmente(boolean actualmente) {
		this.actualmente = actualmente;
	}

	@Column(name = "InfoAdicional", length = 65535)
	public String getInfoAdicional() {
		return this.infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

}
