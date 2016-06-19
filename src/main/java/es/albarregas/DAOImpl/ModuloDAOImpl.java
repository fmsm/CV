package es.albarregas.DAOImpl;

import java.util.List;

import org.hibernate.Query;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.ModuloDAO;
import es.albarregas.modelos.Modulo;

public class ModuloDAOImpl extends DAOGenerico<Modulo> implements ModuloDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Modulo> getModulosByCiclo(String nombreCiclo) {

		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT m FROM Modulo m WHERE m.ciclo.nombre=:nombreCiclo");
		query.setParameter("nombreCiclo", nombreCiclo);
		List<Modulo> resultados = query.list();
		
		super.closeSession();
		
		return resultados; 

	}//getModulosCiclo
	
}//CLASS
