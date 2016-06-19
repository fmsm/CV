package es.albarregas.DAOImpl;

import java.util.List;

import org.hibernate.Query;

import es.albarregas.DAO.CicloDAO;
import es.albarregas.DAO.DAOGenerico;
import es.albarregas.modelos.Ciclo;

public class CicloDAOImpl extends DAOGenerico<Ciclo> implements CicloDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNombresCiclos() {
		
		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT c.nombre FROM Ciclo c");
		List<String> resultados = query.list();
		
		super.closeSession();
		
		return resultados; 

	}//getNombreCiclos()

}//CLASS
