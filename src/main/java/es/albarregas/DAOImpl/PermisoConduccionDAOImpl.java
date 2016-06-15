package es.albarregas.DAOImpl;

import java.util.List;

import org.hibernate.Query;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.PermisoConduccionDAO;
import es.albarregas.modelos.PermisoConduccion;

public class PermisoConduccionDAOImpl extends DAOGenerico<PermisoConduccion> implements PermisoConduccionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PermisoConduccion> getPermisoConduccionByIdUsuario(int id) {

		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT p FROM PermisoConduccion p WHERE p.datoAlumno.idUsuario=:id");
		query.setParameter("id", id);
		List<PermisoConduccion> resultados =  query.list();
		
		super.closeSession();
		
		return resultados; 
				
	}//getPermisoConduccionByIdUsuario
	
}//CLASS
