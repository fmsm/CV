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
@Table(name = "Provincia")
public class Provincia implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private byte idProvincia;
	private Pais pais;
	private String provincia;
	private Set<Municipio> municipios;

	
	public Provincia() {
	}

	
	@Id
	@Column(name = "IdProvincia", unique = true, nullable = false)
	public byte getIdProvincia() {
		return this.idProvincia;
	}

	public void setIdProvincia(byte idProvincia) {
		this.idProvincia = idProvincia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdPais", nullable = false)
	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Column(name = "Provincia", nullable = false, length = 25)
	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provincias")
	public Set<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(Set<Municipio> municipios) {
		this.municipios = municipios;
	}

}
