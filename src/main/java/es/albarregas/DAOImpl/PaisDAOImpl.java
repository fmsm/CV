package es.albarregas.DAOImpl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.PaisDAO;
import es.albarregas.modelos.Pais;

public class PaisDAOImpl extends DAOGenerico<Pais> implements PaisDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Pais> getPaisesLike(String cadena) {
		
		super.openSession();
		
		cadena = "%" + cadena + "%";
		Criteria criteria = sesion.createCriteria(Pais.class);
		criteria.add( Restrictions.like("pais", cadena ) );
		
		List<Pais> resultados = null;
		resultados = criteria.list();
		
		super.closeSession();
		
		return resultados;		
		
	}//getPaisesLike

}//CLASS
