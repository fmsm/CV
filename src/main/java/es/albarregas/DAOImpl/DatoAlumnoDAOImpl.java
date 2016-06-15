package es.albarregas.DAOImpl;

import org.hibernate.Query;
import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.DatoAlumnoDAO;
import es.albarregas.modelos.DatoAlumno;

public class DatoAlumnoDAOImpl extends DAOGenerico<DatoAlumno> implements DatoAlumnoDAO {

	
	@Override
	public DatoAlumno getDatoAlumnoByIdUsuario(int idUsuario) {

		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT d FROM DatoAlumno d WHERE d.idUsuario=:idUsuario");
		query.setParameter("idUsuario", idUsuario);
		DatoAlumno resultado = (DatoAlumno) query.uniqueResult();
		
		super.closeSession();
		
		return resultado; 
				
	}//getDatoAlumnoByIdUsuario

	
	@Override
	public String getInfoAdicional(int idUsuario) {

		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT d.infoAdicional FROM DatoAlumno d WHERE d.idUsuario=:idUsuario");
		query.setParameter("idUsuario", idUsuario);
		String resultado = (String) query.uniqueResult();
		
		super.closeSession();
		
		return resultado;
		
	}//getInfoAdicional

	
}//CLASS
