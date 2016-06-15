package es.albarregas.DAOImpl;

import java.util.List;
import org.hibernate.Query;
import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.IdiomaAlumnoDAO;
import es.albarregas.modelos.IdiomaAlumno;

public class IdiomaAlumnoDAOImpl extends DAOGenerico<IdiomaAlumno> implements IdiomaAlumnoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<IdiomaAlumno> getIdiomaAlumnoByIdUsuario(int id) {

		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT i FROM IdiomaAlumno i WHERE i.datoAlumno.idUsuario=:id");
		query.setParameter("id", id);
		List<IdiomaAlumno> resultados =  query.list();
		
		super.closeSession();
		
		return resultados; 
		
	}//getIdiomaAlumnoByIdUsuario

}//CLASS
