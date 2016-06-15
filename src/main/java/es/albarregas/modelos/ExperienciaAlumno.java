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
@Table(name = "ExperienciaAlumnos")
public class ExperienciaAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idExperienciaAlumno;
	private DatoAlumno datoAlumno;
	private String puesto;
	private String empresa;
	private String sector;
	private String nombreMunicipio;
	private Date fechaInicio;
	private Date fechaFin;
	private boolean actualmente;
	private String infoAdicional;

	
	public ExperienciaAlumno() {
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)	
	@Column(name = "IdExperienciaAlumnos", unique = true, nullable = false)
	public int getIdExperienciaAlumno() {
		return idExperienciaAlumno;
	}

	public void setIdExperienciaAlumno(int idExperienciaAlumno) {
		this.idExperienciaAlumno = idExperienciaAlumno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "Puesto", nullable = false, length = 60)
	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	@Column(name = "Empresa", nullable = false, length = 60)
	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Column(name = "Sector", length = 60)
	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
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
