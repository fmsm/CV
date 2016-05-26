package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RedSocialAlumno")
public class RedSocialAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idRedesSocialesAlumnos;
	private DatoAlumno datoAlumno;
	private String servicio;
	private String nombreUsuario;

	
	public RedSocialAlumno() {
	}

	
	@Id
	@Column(name = "IdRedesSocialesAlumnos", unique = true, nullable = false)
	public int getIdRedesSocialesAlumnos() {
		return this.idRedesSocialesAlumnos;
	}

	public void setIdRedesSocialesAlumnos(int idRedesSocialesAlumnos) {
		this.idRedesSocialesAlumnos = idRedesSocialesAlumnos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "Servicio", nullable = false, length = 20)
	public String getServicio() {
		return this.servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	@Column(name = "NombreUsuario", nullable = false, length = 60)
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
