package es.albarregas.DAO;

import java.util.List;
import es.albarregas.modelos.ExperienciaAlumno;

public interface ExperienciaAlumnoDAO {

	public List<ExperienciaAlumno> getExperienciaAlumnoByIdUsuario(int idUsuario);
	
}//INTERFACE
