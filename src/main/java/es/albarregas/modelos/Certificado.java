package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Certificado")
public class Certificado implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idCertificado;
	private DatoAlumno datoAlumno;
	private String tipoCertificado;
	private String certificado;

	
	public Certificado() {
	}


	@Id
	@Column(name = "IdCertificado", unique = true, nullable = false)
	public int getIdCertificado() {
		return this.idCertificado;
	}

	public void setIdCertificado(int idCertificado) {
		this.idCertificado = idCertificado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "TipoCertificado", nullable = false, length = 3)
	public String getTipoCertificado() {
		return this.tipoCertificado;
	}

	public void setTipoCertificado(String tipoCertificado) {
		this.tipoCertificado = tipoCertificado;
	}

	@Column(name = "Certificado", nullable = false, length = 60)
	public String getCertificado() {
		return this.certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

}
