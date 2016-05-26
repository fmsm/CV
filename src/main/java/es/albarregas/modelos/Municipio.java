package es.albarregas.modelos;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Municipio")
public class Municipio implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idMunicipio;
	private Provincia provincia;
	private String municipio;
	private String codPostal;
	private Set<Direccion> direcciones;

	
	public Municipio() {
	}

	
	@Id
	@Column(name = "IdMunicipio", unique = true, nullable = false)
	public int getIdMunicipio() {
		return this.idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdProvincia", nullable = false)
	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Column(name = "Municipio", nullable = false, length = 101)
	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "CodPostal", nullable = false, length = 5)
	public String getCodPostal() {
		return this.codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "municipio")
	public Set<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(Set<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

}
