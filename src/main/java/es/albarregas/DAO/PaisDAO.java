package es.albarregas.DAO;

import java.util.List;
import es.albarregas.modelos.Pais;

public interface PaisDAO {
	
	public List<Pais> getPaisesLike(String cadena);
	
}//INTERFACE
