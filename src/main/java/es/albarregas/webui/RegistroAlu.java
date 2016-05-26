
/**
 * Temporal, para ir probando lo del registro de alumnos
 */

package es.albarregas.webui;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
//import es.albarregas.utilidades.EnviarCorreo;

@Named
@RequestScoped
public class RegistroAlu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private String passwordMatch;	
			
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
	public String getPasswordMatch() {
		return passwordMatch;
	}
	public void setPasswordMatch(String passwordMatch) {
		this.passwordMatch = passwordMatch;
	}
	
	public void mostrarResultados() {
		
//		EnviarCorreo ec = new EnviarCorreo();
//		ec.configurar(nombre + apellidos, "fmsm.mmo@gmail.com", "registroOK");
//		ec.enviar();
				
	}

}//CLASS
