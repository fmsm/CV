package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Conocimiento")
public class Conocimiento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idConocimientos;
	private DatoAlumno datoAlumno;
	private String nombre;
	private String nivel;

	
	public Conocimiento() {
	}


	@Id
	@Column(name = "IdConocimientos", unique = true, nullable = false)
	public int getIdConocimientos() {
		return this.idConocimientos;
	}

	public void setIdConocimientos(int idConocimientos) {
		this.idConocimientos = idConocimientos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "Nombre", nullable = false, length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Nivel", nullable = false, length = 15)
	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
