package es.albarregas.DAOImpl;

import java.util.List;

import org.hibernate.Query;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.NacionAlumnoDAO;
import es.albarregas.modelos.NacionAlumno;

public class NacionAlumnoDAOImpl extends DAOGenerico<NacionAlumno> implements NacionAlumnoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<NacionAlumno> getNacionAlumnoByIdUsuario(int idUsuario) {
		
		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT n FROM NacionAlumno n WHERE n.datoAlumno.idUsuario=:idUsuario");
		query.setParameter("idUsuario", idUsuario);
		List<NacionAlumno> resultados =  query.list();
		
		super.closeSession();
		
		return resultados;
		
	}//NacionAlumnoDAOImpl
	

}//CLASS
