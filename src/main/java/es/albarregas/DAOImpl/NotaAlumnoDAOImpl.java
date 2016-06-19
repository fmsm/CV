package es.albarregas.DAOImpl;

import java.util.List;

import org.hibernate.Query;

import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.NotaAlumnoDAO;
import es.albarregas.modelos.NotaAlumno;

public class NotaAlumnoDAOImpl extends DAOGenerico<NotaAlumno> implements NotaAlumnoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<NotaAlumno> getNotasAlumnoByIdUsuario(int idUsuario) {
		
		super.openSession();
		
		super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("SELECT n FROM NotaAlumno n WHERE n.datoAlumno.idUsuario=:idUsuario");
		query.setParameter("idUsuario", idUsuario);
		List<NotaAlumno> resultados =  query.list();
		
		super.closeSession();
		
		return resultados;
		
	}//getNotasAlumnobyIdUsuario

	
	@Override
	public void borrarNotasAlumnoByIdUsuario(int idUsuario) {
		
		super.openSession();
		
		//super.transaccion = super.sesion.beginTransaction();
		Query query = super.sesion.createQuery("DELETE FROM NotaAlumno n WHERE n.datoAlumno.idUsuario=:idUsuario");
		query.setParameter("idUsuario", idUsuario);
		int filas = query.executeUpdate();
		
		System.out.println("Filas borradas: " + filas);
		
		super.closeSession();

		
	}//borrarNotasAlumnoByIdUsuario

}//CLASS
