
package es.albarregas.webui;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.text.WordUtils;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAOImpl.MunicipioDAOImpl;
import es.albarregas.DAOImpl.PaisDAOImpl;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.DatoAlumno;
import es.albarregas.modelos.Direccion;
import es.albarregas.modelos.Municipio;
import es.albarregas.modelos.NacionAlumno;
import es.albarregas.modelos.Pais;
import es.albarregas.modelos.Usuario;
import es.albarregas.utilidades.UtilSesion;

@Named
@ViewScoped
public class CvDatosPer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellidos;
	private String genero;
	private Date fechaNacimiento;
	private String tipoIdentificacion;
	private String identificacion;
	private String codPostal;
	private String provincia;
	private String municipio;
	private String direccion;
	private String telefonoDomicilio;
	private String telefonoMovil;
	private String email;
	private String webPersonal;
	private String servicio;
	private String nombreUsuario;	
	private List<Pais> nacionalidades;
	
	private String msgErrorCP = "";
	
	@Inject
	private UploadImageBean cdiImageBean;
	
	/**
	 * Carga de la sesión actual, los atributos nombre, apellidos y email
	 */
	@PostConstruct
	private void init() {
		this.nombre = UtilSesion.getSessionNombre();
		this.apellidos = UtilSesion.getSessionApellidos();
		this.email = UtilSesion.getSessionLogin();
		
//		this.genero = "m";
//		this.fechaNacimiento = new Date();
//		this.tipoIdentificacion = "dni/nif";
//		this.identificacion = "01234567A";
//		this.direccion = "Calle Uno";
//		this.telefonoDomicilio = "912345123";
		
	}//init
	
	
	/**
	 * Método para grabar los datos introducidos en el dialogo de datos personales en la BD
	 */
	public void aceptar() {
				
		//salvar objeto Direccion en BD
		//salvar objeto DatoAlumno en BD
		//salvar nacionalidades en la tabla correspondiente de la BD
		//guardar la foto que esta en memoria en el bean "UploadImageBean", en soporte físico		
						
		UsuarioDAOImpl oDAOU = new UsuarioDAOImpl();
		MunicipioDAOImpl oDAOM = new MunicipioDAOImpl();		
		DAOGenerico<Direccion> oDAOD = new DAOGenerico<Direccion>();
		DAOGenerico<DatoAlumno> oDAO = new DAOGenerico<DatoAlumno>();				
		DAOGenerico<NacionAlumno> oDAONA = new DAOGenerico<NacionAlumno>();
		
		DatoAlumno datos = new DatoAlumno();
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		Municipio municipio = new Municipio();		
		
		//necesitamos el idUsuario (aunque tb se podria haber guardado en la sesion)
		usuario = oDAOU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null);
		datos.setIdUsuario( usuario.getIdUsuario() );
		
		//para rellenar el objeto Direccion, se necesita el Municipio
		municipio = oDAOM.getMunicipioByCP( this.codPostal);		
		direccion.setMunicipio( municipio );
		direccion.setDireccion( this.direccion );
		direccion.setIdDireccion(0);
		
		//salvar objeto Direccion en BD
		oDAOD.save(direccion);
		
		//resto de datos de DatoAlumno		
		datos.setDireccion( direccion );
				
		datos.setGenero(this.genero);
		datos.setFechaNacimiento(this.fechaNacimiento);
		datos.setTipoIdentificacion(this.tipoIdentificacion);
		datos.setIdentificacion(this.identificacion);
		datos.setTelefonoDomicilio(this.telefonoDomicilio);
		datos.setTelefonoMovil(this.telefonoMovil);
		datos.setWebPersonal(this.webPersonal);
		datos.setInfoAdicional(null);
		datos.setUltimoAcceso( new Date());
		
		//salvar objeto DatoAlumno en BD
		oDAO.save(datos);
		
		//salvar nacionalidades en la tabla correspondiente de la BD
		for (Pais item : this.nacionalidades) {
			NacionAlumno nacionalidad = new NacionAlumno();
			nacionalidad.setDatoAlumno(datos);
			nacionalidad.setPais(item);
			oDAONA.save(nacionalidad);
		}
		
		//guardar la foto que esta en memoria en el bean "UploadImageBean", en soporte físico
		cdiImageBean.save( Integer.toString(usuario.getIdUsuario()) );
		
		
	}//aceptar
	
	
	/**
	 * Metodo usado por el actionListener que se dispara cuando se pulsa sobre el boton Cancelar
	 * o cuando se cierra el dialogo con la "X" de la parte izquierda.
	 * Debe restablecer el valor de todos los campos a como estaban cuando se crea la vista, todos vacios, menos los que vienen de la sesion
	 * y se inicializan en el @PostConstruct
	 */
	public void cancelar() {
		
		this.genero = null;
		this.fechaNacimiento = null;
		this.tipoIdentificacion = null;
		this.identificacion = null;
		this.codPostal = null;
		this.provincia = null;
		this.municipio = null;
		this.direccion = null;
		this.telefonoDomicilio = null;
		this.telefonoMovil = null;
		this.webPersonal = null;
		this.servicio = null;
		this.nombreUsuario = null;
		this.nacionalidades = null;
		
	}//cancelar
	
	
	/**
	 * Metodo usado por el actionListener que se dispara cada vez que cambia el valor del CP
	 * y actualiza los valores de cp, provincia y poblacion
	 */
	public void buscarCP() {
		
		//controlar que el codigo postal que viene del ajax request:
		//- tenga 5 caracteres
		//- que se corresponda a un municipio, osea que tras llamar a .getMunicipioByCp, el valor de muni no sea null
		
		if (this.codPostal.length() == 5) {
			
			MunicipioDAOImpl oDAO = new MunicipioDAOImpl();
			Municipio muni = oDAO.getMunicipioByCP( this.codPostal );
			
			if (muni != null) {
				//Mostrar el nombre del municipio capitalizado, usando un metodo de la libreria Apache Commons
				//que convierte un string todo en mayusculas de la ste forma: (MERIDA LA VIEJA) a (Merida La Vieja)
				this.municipio = WordUtils.capitalizeFully( muni.getMunicipio(), new char[] {' ','/'});
				this.provincia = muni.getProvincia().getProvincia();
				this.msgErrorCP = "";
				
			} else {
				
				this.codPostal = "";
				this.msgErrorCP = "Codigo postal inexistente";
						        				
			}//if..else
			
		} else {
			
			this.codPostal = "";
			this.msgErrorCP = "Formato incorrecto";			
			
		}//if..else		
		
	}//buscarCP
	
	
	/**
	 * Metodo usado en el input de nacionalidades, para ofrecer la funcionalidad de autocompletar
	 */
	public List<Pais> acPais(String cadena) {
		
		PaisDAOImpl oDAO = new PaisDAOImpl();		
		List<Pais> sugerencias = oDAO.getPaisesLike(cadena);		
		return sugerencias;
		
	}//acPais
	
	
	/*
	 * Getters y Setters
	 */
	
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
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefonoDomicilio() {
		return telefonoDomicilio;
	}
	public void setTelefonoDomicilio(String telefonoDomicilio) {
		this.telefonoDomicilio = telefonoDomicilio;
	}
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebPersonal() {
		return webPersonal;
	}
	public void setWebPersonal(String webPersonal) {
		this.webPersonal = webPersonal;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public List<Pais> getNacionalidades() {
		return nacionalidades;
	}
	public void setNacionalidades(List<Pais> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}
	public String getMsgErrorCP() {
		return msgErrorCP;
	}
	public void setMsgErrorCP(String msgErrorCP) {
		this.msgErrorCP = msgErrorCP;
	}
	
}//CLASS
