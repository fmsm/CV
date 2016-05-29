package es.albarregas.webui;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.Usuario;


@Named
@ViewScoped
public class Inicio implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final LatLng COORDALBARREGAS = new LatLng(38.9233251, -6.3368287);
	private MapModel mapModel = new DefaultMapModel();
	
	private String username;
	private String password;

	
	/**
	 * Necesario para mostrar el mapa en el footer de la vista
	 * @return MapModel
	 */
	public MapModel getMapModel() {
		
//		StringBuilder ruta = new StringBuilder();
//		ruta.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()).append("/resources/imagenes/marker.png");
//				
//		mapModel.addOverlay( new Marker(COORDALBARREGAS,"IES Albarregas", null, ruta.toString()) );
		
		mapModel.addOverlay( new Marker(COORDALBARREGAS,"IES Albarregas") );
		return mapModel;
	}
	
	
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
			
			//TODO: quedaria actualizar tabla DatosAlumnos.ultimoAcceso
						
		} else {
	        FacesContext.getCurrentInstance().addMessage("mensajeGrowl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email y/o contraseña incorrectos", "") );
	        this.username = "";
	        this.password = "";
		}
				
	}//login
	
	
	
	public Date getFecha() {
		return new Date();
	}	
	
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
