
package es.albarregas.webui;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.albarregas.utilidades.UtilSesion;

@Named
@ViewScoped
public class CvPrincipal implements java.io.Serializable {

	private static final long serialVersionUID = 1L;	
	private String nombre;
	private String apellidos;
	private String email;
	
	//="cv" / ="expediente"
	private String mnuOpcionSeleccionada;
	
	private final String TITULOMENU = "PANEL DEL ALUMNO";  
		
	@Inject
	private CvDatosPer cdiCvDatosPer;
	@Inject
	private CvFormacion cdiCvFormacion;
	@Inject
	private CvExperiencia cdiCvExperiencia;
	@Inject
	private CvIdiomas cdiCvIdiomas;
	@Inject
	private CvOtros cdiCvOtros;
	
	@PostConstruct
	public void init() {
		
		System.out.println("CvPrincipal.init() (@PostConstruct)");
		
		this.setMnuOpcionSeleccionada("cv");
		
		//cargar nombre/apellidos/email de la sesion actual
		this.nombre = UtilSesion.getSessionNombre();
		this.apellidos = UtilSesion.getSessionApellidos();
		this.email = UtilSesion.getSessionLogin();
		
		cdiCvDatosPer.getData();
		cdiCvFormacion.getData();
		cdiCvExperiencia.getData();
		cdiCvIdiomas.getData();
		cdiCvOtros.getData();
		
		
	}//init

	
	//indicará si en la BD hay o no DatosPersonales para el usuario que ha iniciado sesión
	//y servirá para mostrar un contenido u otro en el panel de datos personales dentro del fichero 'cv/content.xhtml'	
	public boolean isExistenDatosPersonales() {
		return cdiCvDatosPer.isTieneDatos();
	}
	
	//igual que el caso anterior, pero para los datos de formacion académica
	public boolean isExistenDatosFormacion() {
		return cdiCvFormacion.isTieneDatos();
	}
	
	//igual que el caso anterior, pero para los datos de experiencia laboral
	public boolean isExistenDatosExperiencia() {
		return cdiCvExperiencia.isTieneDatos();
	}	
	
	//igual que el caso anterior, pero para los datos de idiomas
	public boolean isExistenDatosIdiomas() {
		return cdiCvIdiomas.isTieneDatos();
	}
	
	//igual que el caso anterior, pero para los datos del diálogo "Otros Datos"
	public boolean isExistenDatosOtros() {
		return cdiCvOtros.isTieneDatos();
	}		
	
	public void cv() {
		this.mnuOpcionSeleccionada = "cv";
	}
	
	public void expediente() {
		this.mnuOpcionSeleccionada = "expediente";
	}
	
	/**
	 * Cierra la sesion actual, cuando se pulsa en la opción correspondiente del menu de 'cv/content.xhtml'
	 */
	public String cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index.xhtml?faces-redirect=true";		
	}//cerrarSesion

		
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTITULOMENU() {
		return TITULOMENU;
	}
	public String getMnuOpcionSeleccionada() {
		return mnuOpcionSeleccionada;
	}
	public void setMnuOpcionSeleccionada(String mnuOpcionSeleccionada) {
		this.mnuOpcionSeleccionada = mnuOpcionSeleccionada;
	}
	
}//CLASS
