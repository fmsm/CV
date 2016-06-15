package es.albarregas.DAO;

import java.util.List;
import es.albarregas.modelos.NacionAlumno;

public interface NacionAlumnoDAO {
	
	public List<NacionAlumno> getNacionAlumnoByIdUsuario(int idUsuario);

}//INTERFACE
