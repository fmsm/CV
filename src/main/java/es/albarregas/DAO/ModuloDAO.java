package es.albarregas.DAO;

import java.util.List;

import es.albarregas.modelos.Modulo;

public interface ModuloDAO {
	
	public List<Modulo> getModulosByCiclo(String nombreCiclo);
	
}//INTERFACE
