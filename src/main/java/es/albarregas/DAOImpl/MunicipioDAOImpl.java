package es.albarregas.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.MunicipioDAO;
import es.albarregas.modelos.Municipio;

public class MunicipioDAOImpl extends DAOGenerico<Municipio> implements MunicipioDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Municipio getMunicipioByCP(String cp) {

		super.openSession();
		
		Criteria criteria = sesion.createCriteria(Municipio.class);
		criteria.add( Restrictions.eq("codPostal", cp));

		//lo del .setMaxResults(1) es un ñampa para no tener que ocuparme por falta de tiempo de los casos en los que hay varios municipios para un mismo codigo postal
		//por ejemplo para el cp=01007
		criteria.setMaxResults(1);
		
		List<Municipio> resultados = null;		
		resultados = criteria.list();

		Municipio resultado = (!resultados.isEmpty()) ? resultados.get(0) : null;		
				
		super.closeSession();
		
		return resultado;
		
	}//getMunicipioByCP

}//CLASS
