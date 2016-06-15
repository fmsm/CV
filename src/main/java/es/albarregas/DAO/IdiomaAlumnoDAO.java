package es.albarregas.DAO;

import java.util.List;
import es.albarregas.modelos.IdiomaAlumno;

public interface IdiomaAlumnoDAO {

	public List<IdiomaAlumno> getIdiomaAlumnoByIdUsuario(int idUsuario);
	
}//INTERFACE
