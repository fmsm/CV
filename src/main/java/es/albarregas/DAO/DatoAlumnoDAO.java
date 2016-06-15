package es.albarregas.DAO;

import es.albarregas.modelos.DatoAlumno;

public interface DatoAlumnoDAO {
	
	public DatoAlumno getDatoAlumnoByIdUsuario(int IdUsuario);
	
	public String getInfoAdicional(int idUsuario);

}//INTERFACE
