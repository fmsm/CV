package es.albarregas.DAO;

import java.util.List;

import es.albarregas.modelos.NotaAlumno;

public interface NotaAlumnoDAO {
	
	public List<NotaAlumno> getNotasAlumnoByIdUsuario(int idUsuario);
	
	public void borrarNotasAlumnoByIdUsuario(int idUsuario);
	
}//INTERFACE
