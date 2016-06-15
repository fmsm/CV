package es.albarregas.DAOImpl;

import java.util.List;

import org.hibernate.Query;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.EstudioAlumnoDAO;
import es.albarregas.modelos.EstudioAlumno;

public class EstudioAlumnoDAOImpl extends DAOGenerico<EstudioAlumno> implements EstudioAlumnoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<EstudioAlumno> getEstudiosAlumnoByIdUsuario(int id) {

		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT ea FROM EstudioAlumno ea WHERE ea.datoAlumno.idUsuario=:id");
		query.setParameter("id", id);
		List<EstudioAlumno> resultados =  query.list();
		
		super.closeSession();
		
		return resultados; 
		
	}

}//CLASS
