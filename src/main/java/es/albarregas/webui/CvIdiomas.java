package es.albarregas.webui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAOImpl.DatoAlumnoDAOImpl;
import es.albarregas.DAOImpl.IdiomaAlumnoDAOImpl;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.DatoAlumno;
import es.albarregas.modelos.IdiomaAlumno;
import es.albarregas.utilidades.UtilSesion;

@Named
@ViewScoped
public class CvIdiomas implements java.io.Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private String idioma;
	private String writing;
	private String reading;
	private String listening;
	private String speaking;
	
	private Map<String,String> idiomasSelect;
	private Map<String,String> nivelIdiomaSelect;
	
	private List<IdiomaAlumno> listaIdiomas = new ArrayList<IdiomaAlumno>();
	
	//para identificar especificamente que objeto de los contenidos en listaIdiomas, se va a editar, cuando se pulse en el botón para editar datos
	private int editarIdIdiomaAlumno = -1;
		
	//variable que indicará si en la BD hay o no datos de idiomas para el usuario que ha iniciado sesión
	//y servirá para mostrar un contenido u otro en el panel correspondiente dentro del fichero 'cv/content.xhtml'
	private boolean tieneDatos = false;
	
	
	/**
	 * Constructor
	 */
	public CvIdiomas() {
		
		//se usa LinkedHashMap en vez de HashMap, para mantener el orden de los elementos insertados
		idiomasSelect = new LinkedHashMap<String, String>();			
		idiomasSelect.put("Alemán", "Alemán");
		idiomasSelect.put("Francés", "Francés");
		idiomasSelect.put("Inglés", "Inglés");
		idiomasSelect.put("Italiano", "Italiano");
		idiomasSelect.put("Portugués", "Portugués");
		
		//se usa LinkedHashMap en vez de HashMap, para mantener el orden de los elementos insertados
		nivelIdiomaSelect = new LinkedHashMap<String, String>();	//
		nivelIdiomaSelect.put("A1", "A1");
		nivelIdiomaSelect.put("A2", "A2");
		nivelIdiomaSelect.put("B1", "B1");
		nivelIdiomaSelect.put("B2", "B2");
		nivelIdiomaSelect.put("C1", "C1");
		nivelIdiomaSelect.put("C2", "C2");
		nivelIdiomaSelect.put("Nativo/a", "Nativo/a");		
		nivelIdiomaSelect.put("Nulo", "Nulo");
		nivelIdiomaSelect.put("Básico", "Básico");
		nivelIdiomaSelect.put("Medio", "Medio");
		nivelIdiomaSelect.put("Alto", "Alto");
		
	}//constructor
	
	
	/**
	 * Comprueba si hay datos de Idiomas en la BD para el usuario que ha iniciado sesión actual y en caso positivo, los carga en este bean
	 */
	public void getData() {
		
		System.out.println("CvIdiomas.getData()");
		
		//(BD) obtener id del usuario correspondiente a la sesion actual; usando email
		UsuarioDAOImpl oU = new UsuarioDAOImpl();		
		int id = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
		
		//(BD) intentar obtener objeto/s IdiomaAlumno para ese Usuario y ver si es null o tiene datos		
		IdiomaAlumnoDAOImpl oDAO = new IdiomaAlumnoDAOImpl();		
		this.listaIdiomas = oDAO.getIdiomaAlumnoByIdUsuario(id);
						
		//si la lista devuelta NO esta vacia, marcar el atributo tieneDatos como true		
		if (!listaIdiomas.isEmpty()) {
			this.tieneDatos = true;
		}

	}//getData	

	
	/**
	 * Método para grabar los datos introducidos en el diálogo de Idiomas en la BD.
	 * Hay que tener en cuenta que por aqui va a pasar tanto cuando se añaden datos, como cuando se pulsa sobre el botón para editar/cambiar datos ya existentes  
	 */			
	public void aceptar() {	
		
		//diferenciar guardar datos nuevos y actualización
		if (this.editarIdIdiomaAlumno != -1) {
			
			aceptarEditar();
			
		} else {
		
			//TODO: Mostrar algun mensaje de error/warning cuando se intente introducir información para un mismo idioma más de 1 vez

			//para controlar que el usuario no pueda introducir datos repetidos para un mismo idioma, se realiza una comprobación antes de guardar en la BD
			//idioma que se quiere introducir --> this.idioma
			//idiomas que hay hasta ahora en la BD --> this.listaIdiomas
			
			boolean repetido = false;
			
			for (IdiomaAlumno item: this.listaIdiomas) {				
				if ( item.getIdioma().equals( this.idioma ) ) {
					repetido = true;
				}//if
			}//for
			
			
			if (!repetido) {
			
				//leer de BD objeto DatoAlumno correspondiente al usuario actual, para luego asignarselo al objeto IdiomaAlumno
				UsuarioDAOImpl oU = new UsuarioDAOImpl();
				int idUsuario = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
				DatoAlumnoDAOImpl oDA = new DatoAlumnoDAOImpl();
				DatoAlumno datoAlumno = oDA.getDatoAlumnoByIdUsuario( idUsuario );
				
				//crear objeto de la clase IdiomaAlumno y asignarle los valores del formulario
				IdiomaAlumno idioma = new IdiomaAlumno();
				idioma.setDatoAlumno(datoAlumno);
				idioma.setIdioma( this.idioma );
				idioma.setListening( this.listening );
				idioma.setSpeaking( this.speaking );
				idioma.setWriting( this.writing );
				idioma.setReading( this.reading );			
				
				//y guardar en la BD
				DAOGenerico<IdiomaAlumno> oEA = new DAOGenerico<IdiomaAlumno>();
				oEA.save(idioma);
				
				//además de guardarlo en la BD también añadirlo a "listaIdiomas"
				this.listaIdiomas.add(idioma);
				
			}//if (repetido)
			
		}//if..else
		
		//marcar atributo tieneDatos a true, pues ya hay datos de idiomas en la BD para el usuario en sesión 		
		this.tieneDatos = true;
		
		//y finalmente cerrar dialogo	
		RequestContext.getCurrentInstance().execute("PF('wv_dlgIdiomas').hide();");				
		
		//¿limpiar campos del bean que reciben datos del formulario?
		limpiarCamposFormulario();		
		
	}//aceptar

	
	public void aceptarEditar() {
	
		//recorrer ArrayList listaIdiomas, buscando el objeto IdiomaAlumno, que se corresponda con el objeto del que se van a editar/modificar datos
		IdiomaAlumno idioma = new IdiomaAlumno();
		int indice = 0;
		do {
			idioma = this.listaIdiomas.get(indice);
			indice++;
		} while (idioma.getIdIdiomaAlumnos() != this.editarIdIdiomaAlumno);
				
		//cargar objeto obtenido con los nuevos valores, recogidos del formulario
		idioma.setIdioma( this.idioma);
		idioma.setListening( this.listening );
		idioma.setReading( this.reading );
		idioma.setSpeaking( this.speaking );
		idioma.setWriting( this.writing);
		
		//y guardar en la BD
		DAOGenerico<IdiomaAlumno> oEA = new DAOGenerico<IdiomaAlumno>();
		oEA.update(idioma);
		
		//una vez que se han editado los datos, reinicializar a -1 el atributo del bean que se usa para identificar el objeto de listaIdioma, que se va a editar  
		//para que si el usuario quiere introducir nuevos datos, no nos pase siempre por este método
		this.editarIdIdiomaAlumno = -1;
		
	}//aceptarEditar

	
	public void limpiarCamposFormulario() {
		this.idioma = null;
		this.writing = null;
		this.reading = null;
		this.listening = null;
		this.speaking = null;
	}//limpiarCamposFormulario
	
	
	public void reset() {		
		RequestContext.getCurrentInstance().reset("dlgIdiomas");				
	}//reset		
	

	/**
	 * Eliminar un bloque con datos de idiomas de los que se muestran en "pnlIdiomas.xhtml".
	 * Que se corresponde con un elemento del ArrayList listaIdiomas.
	 */
	public void eliminarBloque(IdiomaAlumno item) {
		
		//tendrá que recibir algo para identificar el bloque
		//borrar el bloque del ArrayList y de la BD
		
		System.out.println("CvIdiomas.eliminarBloque(" + item.getIdIdiomaAlumnos() + ")");
		
		DAOGenerico<IdiomaAlumno> oDAO = new DAOGenerico<IdiomaAlumno>();
		oDAO.delete(item);
		this.listaIdiomas.remove(item);
		
		//¿si al eliminar el bloque el ArrayList queda vacio, habría que actualizar el atributo tieneDatos?
		if (this.listaIdiomas.isEmpty()) {
			this.tieneDatos = false;
		}
		
	}//eliminarBloque	
	
	
	/**
	 * Cargar los atributos de este bean, que se corresponden con los campos del formulario del diálogo 'dlgIdiomas',
	 * con los atributos del objeto IdiomaAlumno que se pasa como parámetro
	 */	
	public void editarBloque(IdiomaAlumno item) {
		
		this.editarIdIdiomaAlumno = item.getIdIdiomaAlumnos();
		
		//asignarle a los atributos de este managed bean, los valores del objeto item que nos llega como parámetros		
		this.idioma = item.getIdioma();
		this.writing = item.getWriting();
		this.reading = item.getReading();
		this.listening = item.getListening();
		this.speaking = item.getSpeaking();
		
	}//editarBloque	
	
	
	/*
	 * Getters y Setters
	 */	
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public List<IdiomaAlumno> getListaIdiomas() {
		return listaIdiomas;
	}
	public boolean isTieneDatos() {
		return tieneDatos;
	}
	public Map<String, String> getIdiomasSelect() {
		return idiomasSelect;
	}
	public String getWriting() {
		return writing;
	}
	public void setWriting(String writing) {
		this.writing = writing;
	}
	public String getReading() {
		return reading;
	}
	public void setReading(String reading) {
		this.reading = reading;
	}
	public String getListening() {
		return listening;
	}
	public void setListening(String listening) {
		this.listening = listening;
	}
	public String getSpeaking() {
		return speaking;
	}
	public void setSpeaking(String speaking) {
		this.speaking = speaking;
	}
	public Map<String, String> getNivelIdiomaSelect() {
		return nivelIdiomaSelect;
	}

}//CLASS
