package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IdiomaAlumno")
public class IdiomaAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idIdiomaAlumnos;
	private DatoAlumno datoAlumno;
	private String idioma;
	private String writing;
	private String listening;
	private String speaking;

	
	public IdiomaAlumno() {
	}

	

	@Id
	@Column(name = "IdIdiomasAlumnos", unique = true, nullable = false)
	public int getIdIdiomaAlumnos() {
		return this.idIdiomaAlumnos;
	}

	public void setIdIdiomaAlumnos(int idIdiomaAlumnos) {
		this.idIdiomaAlumnos = idIdiomaAlumnos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "Idioma", nullable = false, length = 30)
	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	@Column(name = "Writing", nullable = false, length = 15)
	public String getWriting() {
		return this.writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	@Column(name = "Listening", nullable = false, length = 15)
	public String getListening() {
		return this.listening;
	}

	public void setListening(String listening) {
		this.listening = listening;
	}

	@Column(name = "Speaking", nullable = false, length = 15)
	public String getSpeaking() {
		return this.speaking;
	}

	public void setSpeaking(String speaking) {
		this.speaking = speaking;
	}

}
