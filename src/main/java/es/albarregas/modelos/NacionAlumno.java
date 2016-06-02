package es.albarregas.modelos;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NacionAlumnos")
public class NacionAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int idNacionAlumnos;
	private DatoAlumno datoAlumno;
	private Pais pais;

	
	public NacionAlumno() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdNacionAlumnos", unique = true, nullable = false)
	public int getIdNacionAlumnos() {
		return this.idNacionAlumnos;
	}

	public void setIdNacionAlumnos(int idNacionAlumnos) {
		this.idNacionAlumnos = idNacionAlumnos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdPais", nullable = false)
	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
