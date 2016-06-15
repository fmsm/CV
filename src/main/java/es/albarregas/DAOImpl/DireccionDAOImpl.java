package es.albarregas.DAOImpl;

import org.hibernate.Query;
import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.DireccionDAO;
import es.albarregas.modelos.Direccion;

public class DireccionDAOImpl extends DAOGenerico<Direccion> implements DireccionDAO {

	@Override
	public Direccion getDireccionById(int id) {

		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT d FROM Direccion d WHERE idDireccion=:idDireccion");
		query.setParameter("idDireccion", id);
		Direccion resultado = (Direccion) query.uniqueResult();
		
		super.closeSession();
		
		return resultado; 

	}

}//CLASS
