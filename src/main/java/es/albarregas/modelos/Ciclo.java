package es.albarregas.modelos;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Ciclos")
public class Ciclo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idCiclo;
	private String tipoCiclo;
	private String familiaProfesional;
	private String nombre;
	private String abreviatura;
	private String modalidad;
	private String ley;
	private Set<Modulo> modulos;

	
	public Ciclo() {
	}


	@Id
	@Column(name = "IdCiclo", unique = true, nullable = false, length = 8)
	public String getIdCiclo() {
		return this.idCiclo;
	}

	public void setIdCiclo(String idCiclo) {
		this.idCiclo = idCiclo;
	}

	@Column(name = "TipoCiclo", nullable = false, length = 15)
	public String getTipoCiclo() {
		return this.tipoCiclo;
	}

	public void setTipoCiclo(String tipoCiclo) {
		this.tipoCiclo = tipoCiclo;
	}

	@Column(name = "FamiliaProfesional", nullable = false, length = 60)
	public String getFamiliaProfesional() {
		return this.familiaProfesional;
	}

	public void setFamiliaProfesional(String familiaProfesional) {
		this.familiaProfesional = familiaProfesional;
	}

	@Column(name = "Nombre", nullable = false, length = 60)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Abreviatura", nullable = false, length = 5)
	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Column(name = "Modalidad", length = 15)
	public String getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	@Column(name = "Ley", nullable = false, length = 8)
	public String getLey() {
		return this.ley;
	}

	public void setLey(String ley) {
		this.ley = ley;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ciclo")
	public Set<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(Set<Modulo> modulos) {
		this.modulos = modulos;
	}

}
