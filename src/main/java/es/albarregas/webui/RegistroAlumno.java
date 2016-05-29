
package es.albarregas.webui;

import java.nio.charset.StandardCharsets;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.Usuario;

@Named
@ViewScoped
public class RegistroAlumno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private String passwordMatch;
	private String nombre;
	private String apellidos;
				
	/**
	 * Manejar registro de alumnos
	 */
	public void registro() {
		
		UsuarioDAOImpl oDAO = new UsuarioDAOImpl();
		
		//comprobar que no haya otro usuario con ese email ya registrado
		if (oDAO.existeUsuario(email)) {
			
	        FacesContext.getCurrentInstance().addMessage("mensajeGrowl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un usuario registrado con ese email", "") );
	        
	        this.email = "";
	        this.password = "";
	        this.passwordMatch = "";
	        this.nombre = "";
	        this.apellidos = "";
	        	        
		} else {
			
			//guardar datos en BD
			Usuario usuario = new Usuario();
			usuario.setEmail(this.email);
			usuario.setPassword(this.password.getBytes(StandardCharsets.UTF_8));
			usuario.setRol("Alumno");
			usuario.setNombre(this.nombre);
			usuario.setApellidos(this.apellidos);
			oDAO.save(usuario);
			
			//iniciar nueva sesion (boolean a true)
			//meter en sesion datos relevantes del usuario (nombre, apellidos, ¿email? )			
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);            
            session.setAttribute("nombre", this.nombre);
            session.setAttribute("apellidos", this.apellidos);
			session.setAttribute("login", this.email);
			
			//mostrar un dialogo, informando al usuario de que se ha registrado correctamente
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("loginCorrecto", true);
			
			//TODO: quedaria actualizar tabla DatosAlumnos.ultimoAcceso
			
		}//if..else
										
	}//registro

	
	/**
	 * getters y setters
	 */
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
	
}//CLASS
