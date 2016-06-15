package es.albarregas.DAO;

import java.util.List;

import es.albarregas.modelos.EstudioAlumno;

public interface EstudioAlumnoDAO {

	public List<EstudioAlumno> getEstudiosAlumnoByIdUsuario(int idUsuario);
	
}//INTERFACE
