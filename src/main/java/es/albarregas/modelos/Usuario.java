package es.albarregas.modelos;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idUsuario;
	private String email;
	private byte[] password;
	private String rol;
	private String nombre;
	private String apellidos;
	private DatoAlumno datosAlumno;

	
	public Usuario() {
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)	
	@Column(name = "IdUsuario", unique = true, nullable = false)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "Email", unique = true, nullable = false, length = 60)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Password", nullable = false)
	public byte[] getPassword() {
		return this.password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	@Column(name = "Rol", nullable = false, length = 45)
	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Column(name = "Nombre", length = 30)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Apellidos", length = 60)
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public DatoAlumno getDatosAlumno() {
		return datosAlumno;
	}

	public void setDatosAlumno(DatoAlumno datosAlumno) {
		this.datosAlumno = datosAlumno;
	}

}
