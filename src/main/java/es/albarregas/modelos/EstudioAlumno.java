package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EstudioAlumno")
public class EstudioAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idBloquesDatosCv;
	private DatoAlumno datoAlumno;
	private String titulo;
	private String centro;

	
	public EstudioAlumno() {
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

}
