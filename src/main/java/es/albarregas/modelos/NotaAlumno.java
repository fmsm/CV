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
@Table(name = "NotasAlumnos")
public class NotaAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idNotaAlumnos;
	private DatoAlumno datoAlumno;
	private Modulo modulo;
	private byte notaModulo;
	private boolean convalidada;

	
	public NotaAlumno() {
	}
	
	public NotaAlumno(Modulo modulo) {
		this.modulo = modulo;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)	
	@Column(name = "IdNotasAlumnos", unique = true, nullable = false)
	public int getIdNotaAlumnos() {
		return this.idNotaAlumnos;
	}


	public void setIdNotaAlumnos(int idNotaAlumnos) {
		this.idNotaAlumnos = idNotaAlumnos;
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
	@JoinColumn(name = "IdModulo", nullable = false)
	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	@Column(name = "NotaModulo", nullable = false)
	public byte getNotaModulo() {
		return this.notaModulo;
	}

	public void setNotaModulo(byte notaModulo) {
		this.notaModulo = notaModulo;
	}

	@Column(name = "Convalidada", nullable = false)
	public boolean isConvalidada() {
		return this.convalidada;
	}

	public void setConvalidada(boolean convalidada) {
		this.convalidada = convalidada;
	}

}
