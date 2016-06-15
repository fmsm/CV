package es.albarregas.modelos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EstudiosAlumnos")
public class EstudioAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idEstudioAlumno;
	private DatoAlumno datoAlumno;
	private String titulo;
	private String centro;
	private String nombreMunicipio;
	private Date fechaInicio;
	private Date fechaFin;
	private boolean actualmente;
	private String infoAdicional;	

	
	public EstudioAlumno() {
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)	
	@Column(name = "IdEstudiosAlumnos", unique = true, nullable = false)
	public int getIdEstudioAlumno() {
		return idEstudioAlumno;
	}

	public void setIdEstudioAlumno(int idEstudioAlumno) {
		this.idEstudioAlumno = idEstudioAlumno;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "Titulo", nullable = false, length = 60)
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(name = "Centro", nullable = false, length = 60)
	public String getCentro() {
		return this.centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	@Column(name = "NombreMunicipio", length = 101)
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaInicio", nullable = false, length = 10)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaFin", length = 10)
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "Actualmente", nullable = false)
	public boolean isActualmente() {
		return actualmente;
	}

	public void setActualmente(boolean actualmente) {
		this.actualmente = actualmente;
	}

	@Column(name = "InfoAdicional", length = 65535)
	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	
}//CLASS
