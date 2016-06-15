package es.albarregas.webui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAOImpl.DatoAlumnoDAOImpl;
import es.albarregas.DAOImpl.ExperienciaAlumnoDAOImpl;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.DatoAlumno;
import es.albarregas.modelos.ExperienciaAlumno;
import es.albarregas.utilidades.UtilMisc;
import es.albarregas.utilidades.UtilSesion;

@Named
@ViewScoped
public class CvExperiencia implements java.io.Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private String puesto;
	private String empresa;
	private String sector;
	private String municipio;
	private String inicioMes;
	private String inicioAnno;
	private String finMes;
	private String finAnno;
	private boolean actualmente;
	private String infoAdicional;

	private List<ExperienciaAlumno> listaExperiencia = new ArrayList<ExperienciaAlumno>();
	
	//para identificar especificamente que objeto de los contenidos en listaExperiencia, se va a editar, cuando se pulse en el botón para editar datos
	private int editarIdExperienciaAlumno = -1;
		
	//variable que indicará si en la BD hay o no datos de experiencia laboral para el usuario que ha iniciado sesión
	//y servirá para mostrar un contenido u otro en el panel correspondiente dentro del fichero 'cv/content.xhtml'
	private boolean tieneDatos = false;
	
	
	/**
	 * Comprueba si hay datos de Experiencia Laboral en la BD para el usuario que ha iniciado sesión actual y en caso positivo, los carga en este bean
	 */
	public void getData() {
		
		System.out.println("CvExperiencia.getData()");
		
		//(BD) obtener id del usuario correspondiente a la sesion actual; usando email
		UsuarioDAOImpl oU = new UsuarioDAOImpl();		
		int id = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
		
		//(BD) intentar obtener objeto/s EstudioAlumno para ese Usuario y ver si es null o tiene datos		
		ExperienciaAlumnoDAOImpl oEA = new ExperienciaAlumnoDAOImpl();		
		this.listaExperiencia = oEA.getExperienciaAlumnoByIdUsuario(id);
		
		//si la lista devuelta NO esta vacia, marcar el atributo tieneDatos como true		
		if (!listaExperiencia.isEmpty()) {
			this.tieneDatos = true;
		}

	}//getData	
	
	
	public void reset() {		
		RequestContext.getCurrentInstance().reset("dlgExperiencia");				
	}//reset		
	
	
	/**
	 * Método para grabar los datos introducidos en el diálogo de Experiencia Laboral en la BD.
	 * Hay que tener en cuenta que por aqui va a pasar tanto cuando se añaden datos, como cuando se pulsa sobre el botón para editar/cambiar datos ya existentes  
	 */		
	public void aceptar() {	

		//diferenciar guardar datos nuevos y actualización
		if (this.editarIdExperienciaAlumno != -1) {
			
			aceptarEditar();
			
		} else {
				
			//leer de BD objeto DatoAlumno correspondiente al usuario actual, para luego asignarselo al objeto ExperienciaAlumno
			UsuarioDAOImpl oU = new UsuarioDAOImpl();
			int idUsuario = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
			DatoAlumnoDAOImpl oDA = new DatoAlumnoDAOImpl();
			DatoAlumno datoAlumno = oDA.getDatoAlumnoByIdUsuario( idUsuario );
			
			//crear objeto de la clase EstudioAlumno y asignarle los valores del formulario
			//TODO: Comprobar que fecha final es mayor que fecha inicio
			ExperienciaAlumno eA = new ExperienciaAlumno();
			eA.setIdExperienciaAlumno(idUsuario);
			eA.setDatoAlumno(datoAlumno);
			eA.setPuesto(this.puesto);
			eA.setEmpresa(this.empresa);
			eA.setSector(this.sector);
			eA.setNombreMunicipio(this.municipio);
			eA.setFechaInicio( UtilMisc.strMesAnnoToDate(this.inicioMes, this.inicioAnno) );
			eA.setFechaFin( (this.actualmente) ? null : UtilMisc.strMesAnnoToDate(this.finMes, this.finAnno) );
			eA.setActualmente( this.actualmente );
			eA.setInfoAdicional( this.infoAdicional );
			
			//y guardar en la BD
			DAOGenerico<ExperienciaAlumno> oEA = new DAOGenerico<ExperienciaAlumno>();
			oEA.save(eA);
			
			//además de guardarlo en la BD también añadirlo a "listaExperiencia"
			this.listaExperiencia.add(eA);
		
		}//if..else
		
		//marcar atributo tieneDatos a true, pues ya hay datos de formacion académica en la BD para el usuario en sesion 		
		this.tieneDatos = true;
		
		//y finalmente cerrar dialogo dlgExperiencia		
		RequestContext.getCurrentInstance().execute("PF('wv_dlgExperiencia').hide();");		
		
		//¿limpiar campos del bean que reciben datos del formulario?
		limpiarCamposFormulario();		
		
	}//aceptar

	
	public void aceptarEditar() {
		
		//recorrer ArrayList listaExperiencia, buscando el objeto ExperienciaAlumno, que se corresponda con el objeto del que se van a editar/modificar datos
		ExperienciaAlumno eA = new ExperienciaAlumno();
		int indice = 0;
		do {
			eA = this.listaExperiencia.get(indice);
			indice++;
		} while (eA.getIdExperienciaAlumno() != this.editarIdExperienciaAlumno);
				
		//cargar objeto obtenido con los nuevos valores, recogidos del formulario
		eA.setPuesto( this.puesto );
		eA.setEmpresa( this.empresa );
		eA.setNombreMunicipio( this.municipio );
		eA.setFechaInicio( UtilMisc.strMesAnnoToDate(this.inicioMes, this.inicioAnno) );
		eA.setFechaFin( (this.actualmente) ? null : UtilMisc.strMesAnnoToDate(this.finMes, this.finAnno) );		
		eA.setActualmente( this.actualmente );
		eA.setInfoAdicional( this.infoAdicional );
		
		//y guardar en la BD
		DAOGenerico<ExperienciaAlumno> oEA = new DAOGenerico<ExperienciaAlumno>();
		oEA.update(eA);
		
		//una vez que se han editado los datos, reinicializar a -1 el atributo del bean que se usa para identificar el objeto de listaExperiencia, que se va a editar  
		//para que si el usuario quiere introducir nuevos datos, no nos pase siempre por este método
		this.editarIdExperienciaAlumno = -1;
		
	}//aceptarEditar
	

	public void limpiarCamposFormulario() {
		this.puesto = null;
		this.empresa = null;
		this.sector = null;		
		this.municipio = null;
		this.inicioMes = null;
		this.inicioAnno = null;
		this.finMes = null;
		this.finAnno = null;
		this.actualmente = false;
		this.infoAdicional = null;
	}//limpiarCamposFormulario
	
	
	/**
	 * Eliminar un bloque con datos de experiencia laboral de los que se muestran en "pnlExperiencia.xhtml".
	 * Que se corresponde con un elemento del ArrayList listaExperiencia.
	 */
	public void eliminarBloque(ExperienciaAlumno item) {
		
		//tendrá que recibir algo para identificar el bloque
		//borrar el bloque del ArrayList y de la BD
		
		System.out.println("CvExperiencia.eliminarBloque(" + item.getIdExperienciaAlumno() + ")");
		
		DAOGenerico<ExperienciaAlumno> oDAO = new DAOGenerico<ExperienciaAlumno>();
		oDAO.delete(item);
		this.listaExperiencia.remove(item);
		
		//¿si al eliminar el bloque el ArrayList queda vacio, habría que actualizar el atributo tieneDatos?
		if (this.listaExperiencia.isEmpty()) {
			this.tieneDatos = false;
		}
		
	}//eliminarBloque	
	
	
	/**
	 * Cargar los atributos de este bean, que se corresponden con los campos del formulario del diálogo 'dlgExperiencia',
	 * con los atributos del objeto ExperienciaAlumno que se pasa como parámetro
	 */	
	public void editarBloque(ExperienciaAlumno item) {
		
		this.editarIdExperienciaAlumno = item.getIdExperienciaAlumno();
		
		//asignarle a los atributos de este managed bean, los valores del objeto item que nos llega como parámetros
				
		this.puesto = item.getPuesto();
		this.empresa = item.getEmpresa();
		this.sector = item.getSector();
		this.municipio = item.getNombreMunicipio();
		
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime( item.getFechaInicio() );
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);		
		
		this.inicioMes = Integer.toString( month + 1);
		this.inicioAnno = Integer.toString( year );
		
	    calendar.setTime( item.getFechaFin() );
	    year = calendar.get(Calendar.YEAR);
	    month = calendar.get(Calendar.MONTH);		
	    
	    this.finMes = Integer.toString( month + 1 );
	    this.finAnno = Integer.toString( year );
	    this.actualmente = item.isActualmente();
	    this.infoAdicional = item.getInfoAdicional();
		
	}//editarBloque	
	
	
	/*
	 * Getters y Setters
	 */	
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getInicioMes() {
		return inicioMes;
	}
	public void setInicioMes(String inicioMes) {
		this.inicioMes = inicioMes;
	}
	public String getInicioAnno() {
		return inicioAnno;
	}
	public void setInicioAnno(String inicioAnno) {
		this.inicioAnno = inicioAnno;
	}
	public String getFinMes() {
		return finMes;
	}
	public void setFinMes(String finMes) {
		this.finMes = finMes;
	}
	public String getFinAnno() {
		return finAnno;
	}
	public void setFinAnno(String finAnno) {
		this.finAnno = finAnno;
	}
	public boolean isActualmente() {
		return actualmente;
	}
	public void setActualmente(boolean actualmente) {
		this.actualmente = actualmente;
	}
	public String getInfoAdicional() {
		return infoAdicional;
	}
	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	public List<ExperienciaAlumno> getListaExperiencia() {
		return listaExperiencia;
	}
	public boolean isTieneDatos() {
		return tieneDatos;
	}

}//CLASS
