
package es.albarregas.webui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.text.WordUtils;
import org.primefaces.context.RequestContext;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAOImpl.DatoAlumnoDAOImpl;
import es.albarregas.DAOImpl.MunicipioDAOImpl;
import es.albarregas.DAOImpl.PaisDAOImpl;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.DatoAlumno;
import es.albarregas.modelos.Direccion;
import es.albarregas.modelos.Imagen;
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
	
	//aqui se almacenara el objeto DatoAlumno que se cargará de la BD en el método .getData(); o que se grabará en la BD en el método .aceptar()
	private DatoAlumno datosPersonales;
	
	//variable que indicará si en la BD hay o no DatosPersonales para el usuario que ha iniciado sesión
	//y servirá para mostrar un contenido u otro en el panel de datos personales dentro del fichero 'cv/content.xhtml'
	private boolean tieneDatos = false;
	
	@Inject
	private ImageBean cdiImageBean;
	
	
	/**
	 * Comprueba si hay datos en la BD para el usuario que ha iniciado sesión actual y en caso positivo, los carga en los atributos de este bean
	 */
	public void getData() {
		
		System.out.println("CvDatosPer.getData()");
		
		//cargar nombre/apellidos/email de la sesion actual
		this.nombre = UtilSesion.getSessionNombre();
		this.apellidos = UtilSesion.getSessionApellidos();
		this.email = UtilSesion.getSessionLogin();
		
		//(BD) obtener objeto Usuario correspondiente a la sesion actual; usando email
		//(BD) intentar obtener objeto DatoAlumno para ese Usuario y ver si DatoAlumno es null o tiene datos
		
		UsuarioDAOImpl oDAO = new UsuarioDAOImpl();
		DatoAlumnoDAOImpl oDAODA = new DatoAlumnoDAOImpl();
		
		Usuario usuario = oDAO.getUsuarioByLogin( UtilSesion.getSessionLogin(), null);		
		//DatoAlumno datoAlumno = oDAODA.getDatoAlumnoByIdUsuario( usuario.getIdUsuario() );
		this.datosPersonales = oDAODA.getDatoAlumnoByIdUsuario( usuario.getIdUsuario() );
		
		if (this.datosPersonales != null) {
			
			//variable que indica si se han encontrado datos en la BD
			this.tieneDatos = true;
						
			//rellenar los atributos de este bean con los datos que hay en la BD, que se iran leyendo de la BD a medida que se acceda a los atributos del objeto DatoAlumno 
			//obtenido anteriormente; gracias al parámetro "hibernate.enable_lazy_load_no_trans" del fichero hibernate.cfg.xml
			this.genero = this.datosPersonales.getGenero();
			this.fechaNacimiento = this.datosPersonales.getFechaNacimiento();
			this.tipoIdentificacion = this.datosPersonales.getTipoIdentificacion().toUpperCase();
			this.identificacion = this.datosPersonales.getIdentificacion();
			this.codPostal = this.datosPersonales.getDireccion().getMunicipio().getCodPostal();
			this.provincia = this.datosPersonales.getDireccion().getMunicipio().getProvincia().getProvincia();
			
			this.municipio = this.datosPersonales.getDireccion().getMunicipio().getMunicipio();
			this.municipio = WordUtils.capitalizeFully( this.municipio, new char[] {' ','/'});
			
			this.direccion = this.datosPersonales.getDireccion().getDireccion();
			this.telefonoDomicilio = this.datosPersonales.getTelefonoDomicilio();
			this.telefonoMovil = this.datosPersonales.getTelefonoMovil();
			this.webPersonal = this.datosPersonales.getWebPersonal();
			
			if (this.nacionalidades != null && !this.nacionalidades.isEmpty()) {
				this.nacionalidades.clear();
			}
			
			if (this.nacionalidades == null) {
				this.nacionalidades = new ArrayList<Pais>();
			}			
			
			for ( NacionAlumno item : this.datosPersonales.getNacionesAlumnos() ) {
				this.nacionalidades.add( item.getPais() );
			}
			
			//cargar la imagen (si existe) en cdiImageBean.content
			if (this.datosPersonales.getImagen() != null) {
				this.cdiImageBean.setContent( this.datosPersonales.getImagen().getContent());				
			}
			
		}//if
		
	}//init
	
	
	/**
	 * Método para grabar los datos introducidos en el dialogo de datos personales en la BD
	 * Hay que tener en cuenta que por aqui va a pasar tanto cuando se añaden datos por primera vez, como cuando se pulsa sobre el boton
	 * para editar/cambiar datos ya existentes
	 */
	public void aceptar() {
		
		//diferenciar entre cuando se añaden datos o cuando se editan datos ya existentes
		
		if (this.datosPersonales != null) {
			
			aceptarEditar();
			
		} else {
									
			UsuarioDAOImpl oDAOU = new UsuarioDAOImpl();
			MunicipioDAOImpl oDAOM = new MunicipioDAOImpl();		
			DAOGenerico<DatoAlumno> oDAO = new DAOGenerico<DatoAlumno>();				

			Direccion direccion = new Direccion();
			Set<NacionAlumno> listaNacionAlumno = new HashSet<NacionAlumno>(); 
			Imagen imagen = new Imagen();
			this.datosPersonales = new DatoAlumno();
			
			
			//necesitamos el idUsuario (aunque tb se podria haber guardado en la sesion)
			int idUsuario = oDAOU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
			this.datosPersonales.setIdUsuario( idUsuario );
			
			//cargar datosPersonales con su 'Direccion' correspondiente, segun lo introducido en el formulario
			//pero para rellenar el objeto Direccion, se necesita el Municipio			
			direccion.setMunicipio( oDAOM.getMunicipioByCP( this.codPostal) );
			direccion.setDireccion( this.direccion );
			this.datosPersonales.setDireccion( direccion );
			
			//cargar datosPersonales con su Set<NacionAlumno> correspondiente, segun la/s nacionalidad/es que se han introducido en el formulario			
			for (Pais item : this.nacionalidades) {
				NacionAlumno nacionalidad = new NacionAlumno();
				nacionalidad.setDatoAlumno(this.datosPersonales);
				nacionalidad.setPais(item);
				listaNacionAlumno.add(nacionalidad);
			}//for			
			this.datosPersonales.setNacionesAlumnos( listaNacionAlumno );
			
			//cargar datosPersonales con su 'Imagen' correspondiente segun lo introducido en el formulario
			imagen.setIdUsuario( idUsuario );
			imagen.setContent( cdiImageBean.getContent() );
			this.datosPersonales.setImagen(imagen);
						
			//resto de datos de DatoAlumno							
			datosComunes();
			
			//salvar objeto DatoAlumno en BD
			oDAO.save(this.datosPersonales);
			
			System.out.println("aceptar");
			
		}
		
		//poner a true la variable que indica si hay datos personales guardados en la BD para este usuario
		this.tieneDatos = true;
		
		//cerrar dialogo dlgDatosPersonales		
		RequestContext.getCurrentInstance().execute("PF('wv_dlgDatosPersonales').hide();");		

	}//aceptar()
	
	
	
	public void aceptarEditar() {
				
		MunicipioDAOImpl oDAOM = new MunicipioDAOImpl();
		DAOGenerico<DatoAlumno> oDAO = new DAOGenerico<DatoAlumno>();				
		
		//cargar datosPersonales con el nuevo valor para 'Direccion' 		
		this.datosPersonales.getDireccion().setMunicipio( oDAOM.getMunicipioByCP( this.codPostal ) );
		this.datosPersonales.getDireccion().setDireccion( this.direccion );
		
		//cargar datosPersonales con el/los nuevos valores/s para 'NacionAlumno'
		this.datosPersonales.getNacionesAlumnos().clear();

		for (Pais item : this.nacionalidades) {		
			NacionAlumno nacionalidad = new NacionAlumno();
			nacionalidad.setDatoAlumno(this.datosPersonales);
			nacionalidad.setPais(item);			
			this.datosPersonales.getNacionesAlumnos().add(nacionalidad);		
		}//for		
		
		//cargar datosPersonales con su 'nueva' imagen, segun lo introducido en el formulario
		this.datosPersonales.getImagen().setContent( cdiImageBean.getContent());
		
		//cargar datosPersonales con los nuevos valores para el resto de campos
		datosComunes();
		
		//actualizar la BD en cascada
		oDAO.update(this.datosPersonales);
		
		System.out.println("aceptarEditar");
		
	}//aceptarEditar()
	
	
	/**
	 * Datos comunes a los metodos .aceptar y .aceptarEditar
	 */
	public void datosComunes() {
		
		this.datosPersonales.setGenero(this.genero);
		this.datosPersonales.setFechaNacimiento(this.fechaNacimiento);
		this.datosPersonales.setTipoIdentificacion(this.tipoIdentificacion);
		this.datosPersonales.setIdentificacion(this.identificacion);
		this.datosPersonales.setTelefonoDomicilio(this.telefonoDomicilio);
		this.datosPersonales.setTelefonoMovil(this.telefonoMovil);
		this.datosPersonales.setWebPersonal(this.webPersonal);
		this.datosPersonales.setInfoAdicional(null);
		this.datosPersonales.setUltimoAcceso( new Date());
		
	}//datosComunes
	
	
//	/** 
//	 * Restablecer el valor de todos los campos a como estaban cuando se crea la vista, todos vacios, menos los que vienen de la sesión
//	 * y se inicializan en el @PostConstruct
//	 */
//	public void limpiar() {
//		
//		this.genero = null;
//		this.fechaNacimiento = null;
//		this.tipoIdentificacion = null;
//		this.identificacion = null;
//		this.codPostal = null;
//		this.provincia = null;
//		this.municipio = null;
//		this.direccion = null;
//		this.telefonoDomicilio = null;
//		this.telefonoMovil = null;
//		this.webPersonal = null;
//		this.servicio = null;
//		this.nombreUsuario = null;
//		this.nacionalidades = null;
//		this.cdiImageBean.setContent(null);
//		
//	}//cancelar
	
	
	public void reset() {
		
		this.msgErrorCP = "";
		
		//solo hacer el reset si no han sido cargados datos de la BD o no se han guardado en la BD desde este diálogo
		if (!this.tieneDatos) {
			this.codPostal = null;
			this.provincia = null;
			this.municipio = null;
			this.cdiImageBean.setContent(null);
			
			RequestContext.getCurrentInstance().reset("dlgDatosPersonales");
		}
	}
	
	/**
	 * Método usado por el actionListener que se dispara cada vez que cambia el valor del CP
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
				this.municipio = WordUtils.capitalizeFully( muni.getMunicipio(), new char[] {' ','/','-'});
				this.provincia = muni.getProvincia().getProvincia();
				this.msgErrorCP = "";
				
			} else {
				
//				this.codPostal = "";
//				this.municipio = "";
//				this.provincia = "";
				this.msgErrorCP = "Codigo postal inexistente";
						        				
			}//if..else
			
		} else {
			
//			this.codPostal = "";
//			this.municipio = "";
//			this.provincia = "";
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
	
	
	/**
	 * Para calcular la edad a partir de la fecha de nacimiento y mostrarlo en la vista 'pnlDatosPersonales.xhtnl'
	 * @return
	 */
	public int getEdad() {
		long diferencia = new Date().getTime() - this.fechaNacimiento.getTime();
		diferencia = TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS);
		return (int)diferencia / 365;
	}
	
	
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
	public boolean isTieneDatos() {
		return tieneDatos;
	}
	public void setTieneDatos(boolean tieneDatos) {
		this.tieneDatos = tieneDatos;
	}
	public DatoAlumno getDatosPersonales() {
		return datosPersonales;
	}
	
}//CLASS
