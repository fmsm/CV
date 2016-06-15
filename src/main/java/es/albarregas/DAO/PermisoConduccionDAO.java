package es.albarregas.DAO;

import java.util.List;

import es.albarregas.modelos.PermisoConduccion;

public interface PermisoConduccionDAO {
	
	public List<PermisoConduccion> getPermisoConduccionByIdUsuario(int idUsuario);

}//INTERFACE
