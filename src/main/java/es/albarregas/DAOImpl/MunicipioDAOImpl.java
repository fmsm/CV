package es.albarregas.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.MunicipioDAO;
import es.albarregas.modelos.Municipio;
import es.albarregas.modelos.Pais;
import es.albarregas.modelos.Provincia;

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
		
		Municipio resultado = null;
		
		if (!resultados.isEmpty()) {
			
			//esto no lo puedo hacer antes directamente en en list, por que puede ser que no haya ningun resultado y resultados sea null
			resultado = resultados.get(0);
			
			//hay que obtener aqui que esta la sesion abierta (despues no sera posible debido al lazy loading)
			//y rellenar tambien el objeto Provincia correspondiente a ese objeto Municipio
			//lo que a su vez incluye obtener el objeto Pais para esa Provincia
			
			Provincia provincia = new Provincia();
			provincia.setIdProvincia( resultado.getProvincia().getIdProvincia() );
			//provincia.setMunicipios(null);						
			provincia.setProvincia( resultado.getProvincia().getProvincia() );
			
			Pais pais = new Pais();
			pais.setIdPais( resultado.getProvincia().getPais().getIdPais() );
			//pais.setNacionesAlumnos(null);
			pais.setPais( resultado.getProvincia().getPais().getPais() );
			//pais.setProvincias(null);
			
			provincia.setPais( pais );			
			resultado.setProvincia(provincia);
			
		}//if
		
		super.closeSession();
		
		return resultado;
		
	}//getMunicipioByCP

}//CLASS
