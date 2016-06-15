package es.albarregas.DAOImpl;

import java.util.List;
import org.hibernate.Query;
import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.ExperienciaAlumnoDAO;
import es.albarregas.modelos.ExperienciaAlumno;

public class ExperienciaAlumnoDAOImpl extends DAOGenerico<ExperienciaAlumno> implements ExperienciaAlumnoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ExperienciaAlumno> getExperienciaAlumnoByIdUsuario(int id) {

		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT ea FROM ExperienciaAlumno ea WHERE ea.datoAlumno.idUsuario=:id");
		query.setParameter("id", id);
		List<ExperienciaAlumno> resultados =  query.list();
		
		super.closeSession();
		
		return resultados; 
		
	}//getExperienciaAlumnoByIdUsuario

}//CLASS
