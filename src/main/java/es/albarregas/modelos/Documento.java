package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Documentos")
public class Documento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idDocumento;
	private DatoAlumno datoAlumno;
	private boolean tipoDocumento;
	private String nombreFichero;

	
	public Documento() {
	}

	
	@Id
	@Column(name = "IdDocumento", unique = true, nullable = false)
	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "TipoDocumento", nullable = false)
	public boolean isTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(boolean tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Column(name = "NombreFichero", nullable = false, length = 60)
	public String getNombreFichero() {
		return this.nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

}
