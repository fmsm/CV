package es.albarregas.modelos;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Pais")
public class Pais implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idPais;
	private String pais;
	private Set<NacionAlumno> nacionesAlumnos;
	private Set<Provincia> provincias;

	
	public Pais() {
	}

	
	@Id
	@Column(name = "IdPais", unique = true, nullable = false)
	public int getIdPais() {
		return this.idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	@Column(name = "Pais", nullable = false, length = 45)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
	public Set<NacionAlumno> getNacionesAlumnos() {
		return nacionesAlumnos;
	}

	public void setNacionesAlumnos(Set<NacionAlumno> nacionesAlumnos) {
		this.nacionesAlumnos = nacionesAlumnos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
	public Set<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}

}
