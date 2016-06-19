
package es.albarregas.webui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAOImpl.CicloDAOImpl;
import es.albarregas.DAOImpl.DatoAlumnoDAOImpl;
import es.albarregas.DAOImpl.ModuloDAOImpl;
import es.albarregas.DAOImpl.NotaAlumnoDAOImpl;
import es.albarregas.DAOImpl.UsuarioDAOImpl;
import es.albarregas.modelos.DatoAlumno;
import es.albarregas.modelos.Modulo;
import es.albarregas.modelos.NotaAlumno;
import es.albarregas.utilidades.UtilSesion;

@Named
@ViewScoped
public class Expediente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;	
	
	private Map<String,String> listaCiclos = new LinkedHashMap<>();
	private String cicloSeleccionado;
	
	private List<NotaAlumno> listaNotasAlumno;
	
	//variable que indicará si en la BD hay o no datos en la tabla 'NotasAlumnos' para el usuario que ha iniciado sesión
	//y servirá para mostrar un contenido u otro en el panel correspondiente
	private boolean tieneDatos = false;

	
	@PostConstruct
	public void init() {
		
		//cargar lista de ciclos de la BD en el map listaCiclos
		CicloDAOImpl oDAO = new CicloDAOImpl();
		List<String> listaTemporal = oDAO.getNombresCiclos();
		Iterator<String> it = listaTemporal.iterator();
		
		while (it.hasNext()) {
			String tmpString = it.next();
			this.listaCiclos.put(tmpString, tmpString);
		}//while
		
		getData();
		
	}//init
	
	
	/**
	 * Comprueba si hay datos en la tabla 'NotasAlumnos' de la BD para el usuario que ha iniciado sesión actual y en caso positivo, los carga en este bean
	 */
	public void getData() {
		
		System.out.println("expediente.getData()");
		
		//(BD) obtener id del usuario correspondiente a la sesion actual; usando email
		UsuarioDAOImpl oU = new UsuarioDAOImpl();		
		int id = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
		
		//(BD) intentar obtener objeto/s 'NotaAlumno' para ese Usuario y ver si es null o tiene datos		
		NotaAlumnoDAOImpl oDAO = new NotaAlumnoDAOImpl();		
		this.listaNotasAlumno = oDAO.getNotasAlumnoByIdUsuario(id);
		
		//si la lista devuelta NO esta vacia, marcar el atributo tieneDatos como true
		//y asignar el valor correspondiente, dependiendo de lo que venga en estos datos, a 'cicloSeleccionado'
		//solo se plantea el caso de que 1 alumno se matricule en 1 ciclo, por lo tanto el valor de 'NotaAlumno.modulo.ciclo.nombre' va a ser igual para todas las notas
		//disponibles para el usuario/alumno logeado
		if (!listaNotasAlumno.isEmpty()) {
			this.tieneDatos = true;
			this.cicloSeleccionado = this.listaNotasAlumno.get(0).getModulo().getCiclo().getNombre();
		}

	}//getData	
	

	/*
	 * Método para grabar las notas indicadas por el usuario para cada uno de los modulos en la BD.
	 * Hay que tener en cuenta que por aqui va a pasar tanto cuando no haya datos (en la tabla NotasAlumnos) en la BD, como cuando los haya y
	 * lo que se quiera es actualizar esos datos   
	 */		
	public void aceptar() {
		
		//leer de BD objeto DatoAlumno correspondiente al usuario actual, para luego asignarselo a los objeto NotaAlumno
		UsuarioDAOImpl oU = new UsuarioDAOImpl();
		int idUsuario = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
		DatoAlumnoDAOImpl oDA = new DatoAlumnoDAOImpl();
		DatoAlumno datoAlumno = oDA.getDatoAlumnoByIdUsuario( idUsuario );
		
		//preparar los datos a insertar
		for (NotaAlumno item: this.listaNotasAlumno) {
			item.setConvalidada(false);
			item.setDatoAlumno(datoAlumno);
		}
		
		//borrar datos antiguos que habia en la tabla 'NotasAlumnos' para este ususario (si los habia)
		if (this.tieneDatos) {			
			NotaAlumnoDAOImpl oNA = new NotaAlumnoDAOImpl();		
			oNA.borrarNotasAlumnoByIdUsuario(idUsuario);
		}
		
		//guardar en la BD
		DAOGenerico<NotaAlumno> oDAO = new DAOGenerico<>();
		oDAO.saveAll(listaNotasAlumno);
		
		this.tieneDatos = true;
		
	}//aceptar
	
		
	/*
	 * Método al que se llama cada vez que se selecciona uno de los ciclos disponibles en el select, y se encargar de leer de la BD los módulos que tiene ese ciclo,
	 * para mostrarlos por pantalla. 
	 * Asi como de preparar un ArrayList de objetos NotaAlumno, en los que se almacenaran las notas de los alumnos, si se pulsa despues sobre el boton aceptar,
	 */
	public void seleccionCiclo() {
		
		this.listaNotasAlumno.clear();
		
		//obtener objetos Modulo del ciclo seleccionado, para mostrar su nombre por pantalla y usarlos despues para otro fin
		if (!this.cicloSeleccionado.equals(" ")) {
			
			//se obtienen los objetos Modulo del ciclo seleccionado, y para cada uno, se crea un objeto NotaAlumno
			ModuloDAOImpl oDAO = new ModuloDAOImpl();			
			for (Modulo item: oDAO.getModulosByCiclo( this.cicloSeleccionado ) ) {
				this.listaNotasAlumno.add( new NotaAlumno(item) );
			}//for
			
		} else {
						
			this.cicloSeleccionado = null;
			System.out.println("Ningun ciclo seleccionado");
			
			//si equals(" "), se van a borrar todas las filas de la tabla NotasAlumnos para el usuario actual
			
			//(BD) obtener id del usuario correspondiente a la sesion actual; usando email
			UsuarioDAOImpl oU = new UsuarioDAOImpl();		
			int id = oU.getUsuarioByLogin( UtilSesion.getSessionLogin(), null).getIdUsuario();
			
			//borrar
			NotaAlumnoDAOImpl oDAO = new NotaAlumnoDAOImpl();		
			oDAO.borrarNotasAlumnoByIdUsuario(id);
			
			this.tieneDatos = false;
			
		}//if..else
		
	}//seleccionCiclo
	
	
	/*
	 * Getters y Setters
	 */
	
	public Map<String, String> getListaCiclos() {
		return listaCiclos;
	}
	public String getCicloSeleccionado() {
		return cicloSeleccionado;
	}
	public void setCicloSeleccionado(String cicloSeleccionado) {
		this.cicloSeleccionado = cicloSeleccionado;
	}
	public boolean isTieneDatos() {
		return tieneDatos;
	}
	public List<NotaAlumno> getListaNotasAlumno() {
		return listaNotasAlumno;
	}
	public void setListaNotasAlumno(List<NotaAlumno> listaNotasAlumno) {
		this.listaNotasAlumno = listaNotasAlumno;
	}
	
}//CLASS
