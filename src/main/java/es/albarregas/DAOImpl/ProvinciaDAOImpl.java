package es.albarregas.DAOImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.ProvinciaDAO;
import es.albarregas.modelos.Provincia;

public class ProvinciaDAOImpl extends DAOGenerico<Provincia> implements ProvinciaDAO {

	@Override
	public Provincia getProvinciaByNombre(String provincia) {

		Criteria criteria = sesion.createCriteria(Provincia.class);
		criteria.add( Restrictions.eq("provincia", provincia));
		
		Provincia resultado = null;
		resultado = (Provincia) criteria.uniqueResult();
				
		super.closeSession();
		
		return resultado;

	}//getProvinciaByNombre

}//CLASS
