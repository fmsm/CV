package es.albarregas.webui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import es.albarregas.DAOImpl.DatoAlumnoDAOImpl;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.DatoAlumno;
import es.albarregas.modelos.Usuario;

@Named
@ViewScoped
public class Inicio implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
//	private static final LatLng COORDALBARREGAS = new LatLng(38.9233251, -6.3368287);
//	private MapModel mapModel = new DefaultMapModel();
	
	private String username;
	private String password;

	
	/**
	 * Si se encuentra sesión ya existente, pasa de la pagina principal, directamente al perfil del alumno
	 */
	@PostConstruct
	public void init() {
		
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        if (!session.isNew() && session.getAttribute("login") != null ) {
        	try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("panelAlumno.xhtml");
			} catch (IOException e) {				
				e.printStackTrace();
			}
        }
		
	}//init
	
	
//	/**
//	 * Necesario para mostrar el mapa en el footer de la vista
//	 * @return MapModel
//	 */
//	public MapModel getMapModel() {
//		
////		StringBuilder ruta = new StringBuilder();
////		ruta.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()).append("/resources/imagenes/marker.png");
////				
////		mapModel.addOverlay( new Marker(COORDALBARREGAS,"IES Albarregas", null, ruta.toString()) );
//		
//		mapModel.addOverlay( new Marker(COORDALBARREGAS,"IES Albarregas") );
//		return mapModel;
//	}
	
	
	/**
	 * Manejar login de alumnos
	 */
	public void login() {
		
		UsuarioDAOImpl oDAO = new UsuarioDAOImpl();
		
		//comprobar credenciales validas
		Usuario usuario = oDAO.getUsuarioByLogin(this.username, this.password.getBytes(StandardCharsets.UTF_8));
		
		if ( usuario != null ) {

			//iniciar nueva sesion (boolean a true)
			//meter en sesion datos relevantes del usuario (nombre, apellidos, ¿email? )
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);            
            session.setAttribute("nombre", usuario.getNombre());
            session.setAttribute("apellidos", usuario.getApellidos());
			session.setAttribute("login", usuario.getEmail());
			
			//mostrar un dialogo, informando al usuario de que se ha registrado correctamente
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("loginCorrecto", true);
			
			//actualizar la ultima fecha de acceso: se podria hacer de otra forma, pero lo voy a hacer de tal forma que cuando un usuario haga login
			//en la aplicacion se actualize dicha fecha de ultimo acceso, pero solo si el usuario en cuestión ha introducido datos personales y por lo
			//tanto tiene datos en la tabla DatosAlumnos
			
			//se obtiene el objeto DatoAlumno correspondiente a este usuario y se actualiza el timestamp con la fecha actual
			DatoAlumnoDAOImpl datoAlumnoDAO = new DatoAlumnoDAOImpl();
			DatoAlumno datoAlumno = datoAlumnoDAO.getDatoAlumnoByIdUsuario( usuario.getIdUsuario() );
			
			if (datoAlumno != null) {
				datoAlumno.setUltimoAcceso( new Date() );
				datoAlumnoDAO.update(datoAlumno);				
			}//if
			
						
		} else {
	        FacesContext.getCurrentInstance().addMessage("mensajeGrowl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email y/o contraseña incorrectos", "") );
	        this.username = "";
	        this.password = "";
		}
				
	}//login
		
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}//CLASS
