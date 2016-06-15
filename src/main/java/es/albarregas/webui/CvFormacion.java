package es.albarregas.webui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAOImpl.DatoAlumnoDAOImpl;
import es.albarregas.DAOImpl.EstudioAlumnoDAOImpl;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.DatoAlumno;
import es.albarregas.modelos.EstudioAlumno;
import es.albarregas.utilidades.UtilMisc;
import es.albarregas.utilidades.UtilSesion;

@Named
@ViewScoped
public class CvFormacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private String centro;
	private String municipio;
	private String inicioMes;
	private String inicioAnno;
	private String finMes;
	private String finAnno;
	private boolean actualmente;
	private String infoAdicional;
	
	private List<EstudioAlumno> listaEstudios = new ArrayList<EstudioAlumno>();
	
	//para identificar especificamente que objeto de los contenidos en listaEstudios, se va a editar, cuando se pulse en el botón para editar datos
	private int editarIdEstudioAlumno = -1;
	
	//variable que indicará si en la BD hay o no datos de formacion académica para el usuario que ha iniciado sesión
	//y servirá para mostrar un contenido u otro en el panel correspondiente dentro del fichero 'cv/content.xhtml'
	private boolean tieneDatos = false;
	

	/**
	 * Comprueba si hay datos de Formación Académica en la BD para el usuario que ha iniciado sesión actual y en caso positivo, los carga en este bean
	 */
	public void getData() {
		
		System.out.println("CvFormacion.getData()");
		
		//(BD) obtener id del usuario correspondiente a la sesion actual; usando email
		UsuarioDAOImpl oU = new UsuarioDAOImpl();		
		int id = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
		
		//(BD) intentar obtener objeto/s EstudioAlumno para ese Usuario y ver si es null o tiene datos		
		EstudioAlumnoDAOImpl oEA = new EstudioAlumnoDAOImpl();		
		this.listaEstudios = oEA.getEstudiosAlumnoByIdUsuario(id);
		
		//si la lista devuelta NO esta vacia, marcar el atributo tieneDatos como true		
		if (!listaEstudios.isEmpty()) {
			this.tieneDatos = true;
		}

	}//getData
	
	
	/**
	 * Método para grabar los datos introducidos en el diálogo de Formación Académica en la BD.
	 * Hay que tener en cuenta que por aqui va a pasar tanto cuando se añaden datos, como cuando se pulsa sobre el boton para editar/cambiar datos ya existentes  
	 */	
	public void aceptar() {		
		
		//diferenciar guardar datos nuevos y actualización
		if (this.editarIdEstudioAlumno != -1) {
			
			aceptarEditar();
			
		} else {
		
			//leer de BD objeto DatoAlumno correspondiente al usuario actual, para luego asignarselo al objeto EstudioAlumno
			UsuarioDAOImpl oU = new UsuarioDAOImpl();
			int idUsuario = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
			DatoAlumnoDAOImpl oDA = new DatoAlumnoDAOImpl();
			DatoAlumno datoAlumno = oDA.getDatoAlumnoByIdUsuario( idUsuario );
			
			//crear objeto de la clase EstudioAlumno y asignarle los valores del formulario
			//TODO: Comprobar que fecha final es mayor que fecha inicio
			EstudioAlumno eA = new EstudioAlumno();
			eA.setIdEstudioAlumno(0);
			eA.setDatoAlumno( datoAlumno );
			eA.setTitulo( this.titulo );
			eA.setCentro( this.centro );
			eA.setNombreMunicipio( this.municipio );
			eA.setFechaInicio( UtilMisc.strMesAnnoToDate(this.inicioMes, this.inicioAnno) );
			eA.setFechaFin( (this.actualmente) ? null : UtilMisc.strMesAnnoToDate(this.finMes, this.finAnno) );		
			eA.setActualmente( this.actualmente );
			eA.setInfoAdicional( this.infoAdicional );
			
			//y guardar en la BD
			DAOGenerico<EstudioAlumno> oEA = new DAOGenerico<EstudioAlumno>();
			oEA.save(eA);
			
			//además de guardarlo en la BD también añadirlo a "listaEstudios"
			this.listaEstudios.add(eA);

		}//if..else
		
		//marcar atributo tieneDatos a true, pues ya hay datos de formacion académica en la BD para el usuario en sesion 		
		this.tieneDatos = true;

		//y finalmente cerrar dialogo dlgFormacion		
		RequestContext.getCurrentInstance().execute("PF('wv_dlgFormacion').hide();");
		
		//¿limpiar campos del bean que reciben datos del formulario?
		limpiarCamposFormulario();
		
	}//aceptar

	
	public void aceptarEditar() {
		
		//recorrer ArrayList listaEstudios, buscando el objeto EstudioAlumno, que se corresponda con el objeto del que se van a editar/modificar datos
		EstudioAlumno eA = new EstudioAlumno();
		int indice = 0;
		do {
			eA = this.listaEstudios.get(indice);
			indice++;
		} while (eA.getIdEstudioAlumno() != this.editarIdEstudioAlumno);
				
		//cargar objeto obtenido con los nuevos valores, recogidos del formulario
		eA.setTitulo( this.titulo );
		eA.setCentro( this.centro );
		eA.setNombreMunicipio( this.municipio );
		eA.setFechaInicio( UtilMisc.strMesAnnoToDate(this.inicioMes, this.inicioAnno) );
		eA.setFechaFin( (this.actualmente) ? null : UtilMisc.strMesAnnoToDate(this.finMes, this.finAnno) );		
		eA.setActualmente( this.actualmente );
		eA.setInfoAdicional( this.infoAdicional );
		
		//y guardar en la BD
		DAOGenerico<EstudioAlumno> oEA = new DAOGenerico<EstudioAlumno>();
		oEA.update(eA);
		
		//una vez que se han editado los datos, reinicializar a -1 el atributo del bean que se usa para identificar el objeto de listaEstudios, que se va a editar  
		//para que si el usuario quiere introducir nuevos datos, no nos pase siempre por este método
		this.editarIdEstudioAlumno = -1;
		
	}//aceptarEditar
	
	
	public void limpiarCamposFormulario() {
		this.titulo = null;
		this.centro = null;
		this.municipio = null;
		this.inicioMes = null;
		this.inicioAnno = null;
		this.finMes = null;
		this.finAnno = null;
		this.actualmente = false;
		this.infoAdicional = null;
	}//limpiarCamposFormulario

	
	public void reset() {
		RequestContext.getCurrentInstance().reset("dlgFormacion");				
	}//reset	
	
	
	/**
	 * Eliminar un bloque con datos de formación académica de los que se muestran en "pnlFormacion.xhtml".
	 * Que se corresponde con un elemento del ArrayList listaEstudios.
	 */
	public void eliminarBloque(EstudioAlumno item) {
		
		//tendrá que recibir algo para identificar el bloque
		//borrar el bloque del ArrayList y de la BD
		
		System.out.println("CvFormacion.eliminarBloque(" + item.getIdEstudioAlumno() + ")");
		
		DAOGenerico<EstudioAlumno> oDAO = new DAOGenerico<EstudioAlumno>();
		oDAO.delete(item);
		this.listaEstudios.remove(item);
		
		//¿si al eliminar el bloque el ArrayList queda vacio, habría que actualizar el atributo tieneDatos?
		if (this.listaEstudios.isEmpty()) {
			this.tieneDatos = false;
		}
		
	}//eliminarBloque
	
	
	/**
	 * Cargar los atributos de este bean, que se corresponden con los campos del formulario del diálogo 'dlgFormacion',
	 * con los atributos del objeto EstudioAlumno que se pasa como parámetro
	 */	
	public void editarBloque(EstudioAlumno item) { 
		
		this.editarIdEstudioAlumno = item.getIdEstudioAlumno();
		
		//asignarle a los atributos de este managed bean, los valores del objeto item que nos llega como parámetros
		
		this.titulo = item.getTitulo();
		this.centro = item.getCentro();
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
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
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

	public List<EstudioAlumno> getListaEstudios() {
		return listaEstudios;
	}

//	public void setListaEstudios(List<EstudioAlumno> listaEstudios) {
//		this.listaEstudios = listaEstudios;
//	}

	public boolean isTieneDatos() {
		return tieneDatos;
	}

//	public void setTieneDatos(boolean tieneDatos) {
//		this.tieneDatos = tieneDatos;
//	}	
	
}//CLASS
