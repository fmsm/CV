package es.albarregas.modelos;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Modulo")
public class Modulo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idModulo;
	private Ciclo ciclo;
	private String nombre;
	private Set<NotaAlumno> notasAlumnos;

	
	public Modulo() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdModulo", unique = true, nullable = false)
	public Integer getIdModulo() {
		return this.idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdCiclo", nullable = false)
	public Ciclo getCiclo() {
		return this.ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	@Column(name = "Nombre", nullable = false, length = 60)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "modulo")
	public Set<NotaAlumno> getNotasAlumnos() {
		return notasAlumnos;
	}

	public void setNotasAlumnos(Set<NotaAlumno> notasAlumnos) {
		this.notasAlumnos = notasAlumnos;
	}

}
