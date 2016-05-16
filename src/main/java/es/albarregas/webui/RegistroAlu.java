
/**
 * Temporal, para ir probando lo del registro de alumnos
 */

package es.albarregas.webui;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import es.albarregas.utilidades.EnviarCorreo;

@Named
@RequestScoped
public class RegistroAlu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
//	private String passwordMatch;
	private String nombre;
	private String apellidos;
	private String tipoIdentificacion;
	private String identificacion;
	
			
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getPasswordMatch() {
//		return passwordMatch;
//	}
//	public void setPasswordMatch(String passwordMatch) {
//		this.passwordMatch = passwordMatch;
//	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	public String mostrarResultados() {
		
		EnviarCorreo ec = new EnviarCorreo();
		ec.configurar(nombre + apellidos, "fmsm.mmo@gmail.com", "registroOK");
		ec.enviar();
		
		return "resultados.xhtml";
	}

}//CLASS
