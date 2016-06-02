package es.albarregas.modelos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Direcciones")
public class Direccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idDireccion;
	private Municipio municipio;
	private String direccion;
	//private DatoAlumno datosAlumno;
	private Set<BloqueDatosCv> bloquesDatosCvs;

	
	public Direccion() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)	
	@Column(name = "IdDireccion", unique = true, nullable = false)
	public int getIdDireccion() {
		return this.idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdMunicipio", nullable = false)
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Column(name = "Direccion", nullable = false, length = 60)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

//	@OneToOne(fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn	
//	public DatoAlumno getDatosAlumno() {
//		return datosAlumno;
//	}
//
//	public void setDatosAlumno(DatoAlumno datosAlumno) {
//		this.datosAlumno = datosAlumno;
//	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "direccion")
	public Set<BloqueDatosCv> getBloquesDatosCvs() {
		return this.bloquesDatosCvs;
	}

	public void setBloquesDatosCvs(Set<BloqueDatosCv> bloquesDatosCvs) {
		this.bloquesDatosCvs = bloquesDatosCvs;
	}

}
