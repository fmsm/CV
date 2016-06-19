
package es.albarregas.webui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAOImpl.DatoAlumnoDAOImpl;
import es.albarregas.DAOImpl.PermisoConduccionDAOImpl;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.DatoAlumno;
import es.albarregas.modelos.PermisoConduccion;
import es.albarregas.utilidades.UtilSesion;

@Named
@ViewScoped
public class CvOtros implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
		
	private List<String> tiposPermisos;
	private List<String> selectedTiposPermisos;	
	
	private String infoAdicional;
	private List<PermisoConduccion> listaPermisosConduccion = new ArrayList<PermisoConduccion>();
	
	//variable que indicará si en la BD hay o no "Otros Datos" para el usuario que ha iniciado sesión
	//y servirá para mostrar un contenido u otro en el panel de datos personales dentro del fichero 'cv/content.xhtml'
	private boolean tieneDatos = false;
	
	@Inject
	private CvDatosPer cdiCvDatosPer;
	
	
	/**
	 * Constructor
	 */
	public CvOtros() {
		
		tiposPermisos = new ArrayList<String>();
		tiposPermisos.add("AM");
		tiposPermisos.add("A1");
		tiposPermisos.add("A2");
		tiposPermisos.add("A");
		tiposPermisos.add("B1");
		tiposPermisos.add("B");
		tiposPermisos.add("BE");
		tiposPermisos.add("C1");
		tiposPermisos.add("C1E");
		tiposPermisos.add("C");
		tiposPermisos.add("CE");
		tiposPermisos.add("D1");
		tiposPermisos.add("D1E");
		tiposPermisos.add("D");
		tiposPermisos.add("DE");
		
	}//constructor
	
	
	/**
	 * Comprueba si hay datos en la BD para el usuario que ha iniciado sesión actual y en caso positivo, los carga en los atributos de este bean
	 */
	public void getData() {
		
		System.out.println("CvOtros.getData()");
		
		//hay que obtener de la BD los objetos PermisoConduccion pertenecientes al usuario que ha iniciado sesión
		//asi como el campo DatoAlumno.infoAdicional del mismo
		
		//(BD) obtener id del usuario correspondiente a la sesion actual; usando email
		UsuarioDAOImpl oU = new UsuarioDAOImpl();		
		int id = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
		
		//(BD) intentar obtener objeto/s PermisoConduccion para ese Usuario
		PermisoConduccionDAOImpl oDAO = new PermisoConduccionDAOImpl();		
		this.listaPermisosConduccion = oDAO.getPermisoConduccionByIdUsuario(id);
		
		//(BD) intentar obtener el valor para el atributo "infoAdicional" de esta clase
		DatoAlumnoDAOImpl oDA = new DatoAlumnoDAOImpl();
		this.infoAdicional = oDA.getInfoAdicional(id);
		
		//si la lista obtenido NO esta vacia O el infoAdicional obtenida no esta vacio/null, marcar el atributo tieneDatos como true		
		if ( !this.listaPermisosConduccion.isEmpty() || this.infoAdicional != null ) {
			this.tieneDatos = true;
		}
		
		// ¿¿ también habría que inicializar el valor de selectedTiposPermisos, para que al pulsar en el botón editar correspondiente, 
		//salgan en el diálogo, como seleccionados los checkboxs correspondientes a los objetos PermisoConduccion que se han obtenido de la BD ??		
		//recorrer this.listaPermisosConduccion, por cada elemento, añadir un String con el nombre del permiso a this.selectedTiposPermisos
		this.selectedTiposPermisos = new ArrayList<String>();
		for (PermisoConduccion item : this.listaPermisosConduccion) {
			this.selectedTiposPermisos.add( item.getTipo() );
		}//for
		
		
	}//init
	
	
	/*
	 * Método para grabar los datos introducidos en el dialogo en la BD.
	 * Hay que tener en cuenta que por aqui va a pasar tanto cuando se añaden datos por primera vez, como cuando se pulsa sobre el botón
	 * para editar/cambiar datos ya existentes.
	 */
	public void aceptar() {
		
		//diferenciar entre cuando se añaden datos o cuando se editan datos ya existentes
		if ( !this.listaPermisosConduccion.isEmpty() || this.infoAdicional != null ) {
			
			aceptarEditar();
			
		} else {
		
			//leer de BD objeto DatoAlumno correspondiente al usuario actual, para luego asignarselo al/los objetos PermisoConduccion que se creen
			UsuarioDAOImpl oU = new UsuarioDAOImpl();
			int idUsuario = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
			DatoAlumnoDAOImpl oDA = new DatoAlumnoDAOImpl();
			DatoAlumno datoAlumno = oDA.getDatoAlumnoByIdUsuario( idUsuario );
			
			//crear tantos objetos PermisoConduccion como elementos tenga el ArrayList "selectedTipoPermisos" y guardarlos en la BD		
			//pero comprobar primero que no este vacio/null, no se haya seleccionado nada
			
			DAOGenerico<PermisoConduccion> oDAO = new DAOGenerico<PermisoConduccion>();
			
			if ( this.selectedTiposPermisos != null && !this.selectedTiposPermisos.isEmpty() ) {
				
				Iterator<String> it = this.selectedTiposPermisos.iterator();
				while (it.hasNext()) {
					PermisoConduccion per = new PermisoConduccion(datoAlumno, it.next());
					oDAO.save(per);
				}
			}
			
			//el campo del dialogo "dlgOtros", que permite introducir "Informacion Adicional" y que se almacena en el atributo de esta clase llamado "infoAdicional"
			//debe ir en la Tabla "DatosAlumnos" de la BD, en la fila "InfoAdicional" (que se corresponden en clases Jave con: DatoAlumno.infoAdicional
			//por lo tanto hay que hacer un update del objeto DatoAlumno que obtuvimos anteriormente
			
			if (this.infoAdicional != null && !this.infoAdicional.isEmpty()) {
				datoAlumno.setInfoAdicional(this.infoAdicional);
				oDA.update(datoAlumno);
			}//if

		}//if (diferenciar entre cuando se añaden datos o cuando se editan datos ya existentes)	
		
		//marcar atributo tieneDatos a true, pues ya hay datos de formacion académica en la BD para el usuario en sesion 		
		this.tieneDatos = true;		
		
		//y finalmente cerrar dialogo	
		RequestContext.getCurrentInstance().execute("PF('wv_dlgOtros').hide();");						
		
	}//aceptar
	

	
	public void aceptarEditar() {
		
		//modificar la informacion adicional
		cdiCvDatosPer.getDatosPersonales().setInfoAdicional( this.infoAdicional );
		
		//modificar los permisos de conduccion si procede
		if ( this.selectedTiposPermisos != null && !this.selectedTiposPermisos.isEmpty() ) {
			
			cdiCvDatosPer.getDatosPersonales().getPermisosConduccion().clear();
			this.listaPermisosConduccion.clear();
			
			Iterator<String> it = this.selectedTiposPermisos.iterator();
			while (it.hasNext()) {
				PermisoConduccion per = new PermisoConduccion( cdiCvDatosPer.getDatosPersonales(), it.next());
				this.listaPermisosConduccion.add(per);
				cdiCvDatosPer.getDatosPersonales().getPermisosConduccion().add( per );
			}//while
			
		}//if

		DAOGenerico<DatoAlumno> oDAO = new DAOGenerico<DatoAlumno>();
		oDAO.update( cdiCvDatosPer.getDatosPersonales() );
		
	}//aceptarEditar
	

	
	public void reset() {
		RequestContext.getCurrentInstance().reset("dlgOtros");
	}


	/*
	 * Getters y Setters
	 */
	
	public boolean isTieneDatos() {
		return tieneDatos;
	}
	public List<String> getTiposPermisos() {
		return tiposPermisos;
	}
	public List<String> getSelectedTiposPermisos() {
		return selectedTiposPermisos;
	}
	public void setSelectedTiposPermisos(List<String> selectedTiposPermisos) {
		this.selectedTiposPermisos = selectedTiposPermisos;
	}
	public String getInfoAdicional() {
		return infoAdicional;
	}
	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	public List<PermisoConduccion> getListaPermisosConduccion() {
		return listaPermisosConduccion;
	}
	
}//CLASS
