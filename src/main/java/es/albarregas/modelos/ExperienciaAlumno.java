package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ExperienciaAlumno")
public class ExperienciaAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idBloqueDatosCv;
	private DatoAlumno datoAlumno;
	private String puesto;
	private String empresa;
	private String sector;

	
	public ExperienciaAlumno() {
	}

	
	@Id
	@Column(name = "IdBloqueDatosCV", unique = true, nullable = false)
	public int getIdBloqueDatosCv() {
		return this.idBloqueDatosCv;
	}

	public void setIdBloqueDatosCv(int idBloqueDatosCv) {
		this.idBloqueDatosCv = idBloqueDatosCv;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "Puesto", nullable = false, length = 60)
	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	@Column(name = "Empresa", nullable = false, length = 60)
	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Column(name = "Sector", length = 60)
	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

}
