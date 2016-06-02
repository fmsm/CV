package es.albarregas.modelos;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "DatosAlumnos")
public class DatoAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idUsuario;
	private Direccion direccion;
	private String genero;
	private Date fechaNacimiento;
	private String tipoIdentificacion;
	private String identificacion;
	private String telefonoDomicilio;
	private String telefonoMovil;
	private String webPersonal;
	private String infoAdicional;
	private Date ultimoAcceso;
	private Set<Conocimiento> conocimientos;
	private Set<NotaAlumno> notasAlumnos;
	private Set<IdiomaAlumno> idiomasAlumnos;
	private Set<EstudioAlumno> estudiosAlumnos;
	private Set<Documento> documentos;
	private Set<Certificado> certificados;
	private Set<RedSocialAlumno> redesSocialesAlumnos;
	private Set<NacionAlumno> nacionesAlumnos;
	private Set<PermisoConduccion> permisosConduccion;
	private Set<ExperienciaAlumno> experienciaAlumnos;

	
	public DatoAlumno() {
	}

	
	@Id	
	@Column(name = "IdUsuario", unique = true, nullable = false)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="IdDireccion")
	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}


	@Column(name = "Genero", nullable = false, length = 3)
	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaNacimiento", nullable = false, length = 10)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Column(name = "TipoIdentificacion", length = 21)
	public String getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	@Column(name = "Identificacion", length = 45)
	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	@Column(name = "TelefonoDomicilio", length = 9)
	public String getTelefonoDomicilio() {
		return this.telefonoDomicilio;
	}

	public void setTelefonoDomicilio(String telefonoDomicilio) {
		this.telefonoDomicilio = telefonoDomicilio;
	}

	@Column(name = "TelefonoMovil", length = 9)
	public String getTelefonoMovil() {
		return this.telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	@Column(name = "WebPersonal", length = 60)
	public String getWebPersonal() {
		return this.webPersonal;
	}

	public void setWebPersonal(String webPersonal) {
		this.webPersonal = webPersonal;
	}

	@Column(name = "InfoAdicional", length = 65535)
	public String getInfoAdicional() {
		return this.infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UltimoAcceso", nullable = false, length = 19)
	public Date getUltimoAcceso() {
		return this.ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<Conocimiento> getConocimientos() {
		return conocimientos;
	}


	public void setConocimientos(Set<Conocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<NotaAlumno> getNotasAlumnos() {
		return notasAlumnos;
	}


	public void setNotasAlumnos(Set<NotaAlumno> notasAlumnos) {
		this.notasAlumnos = notasAlumnos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<IdiomaAlumno> getIdiomasAlumnos() {
		return idiomasAlumnos;
	}


	public void setIdiomasAlumnos(Set<IdiomaAlumno> idiomasAlumnos) {
		this.idiomasAlumnos = idiomasAlumnos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<EstudioAlumno> getEstudiosAlumnos() {
		return estudiosAlumnos;
	}


	public void setEstudiosAlumnos(Set<EstudioAlumno> estudiosAlumnos) {
		this.estudiosAlumnos = estudiosAlumnos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<Documento> getDocumentos() {
		return documentos;
	}


	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<Certificado> getCertificados() {
		return certificados;
	}


	public void setCertificados(Set<Certificado> certificados) {
		this.certificados = certificados;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<RedSocialAlumno> getRedesSocialesAlumnos() {
		return redesSocialesAlumnos;
	}


	public void setRedesSocialesAlumnos(Set<RedSocialAlumno> redesSocialesAlumnos) {
		this.redesSocialesAlumnos = redesSocialesAlumnos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<NacionAlumno> getNacionesAlumnos() {
		return nacionesAlumnos;
	}


	public void setNacionesAlumnos(Set<NacionAlumno> nacionesAlumnos) {
		this.nacionesAlumnos = nacionesAlumnos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<PermisoConduccion> getPermisosConduccion() {
		return permisosConduccion;
	}


	public void setPermisosConduccion(Set<PermisoConduccion> permisosConduccion) {
		this.permisosConduccion = permisosConduccion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoAlumno")
	public Set<ExperienciaAlumno> getExperienciaAlumnos() {
		return experienciaAlumnos;
	}


	public void setExperienciaAlumnos(Set<ExperienciaAlumno> experienciaAlumnos) {
		this.experienciaAlumnos = experienciaAlumnos;
	}

	
}
