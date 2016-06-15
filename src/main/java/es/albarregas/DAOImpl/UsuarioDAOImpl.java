package es.albarregas.DAOImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import es.albarregas.DAO.DAOGenerico;
import es.albarregas.DAO.UsuarioDAO;
import es.albarregas.modelos.Usuario;

public class UsuarioDAOImpl extends DAOGenerico<Usuario> implements UsuarioDAO {
	
	@Override
	public boolean existeUsuario(String email) {
		
		super.openSession();
		
		Criteria criteria = sesion.createCriteria(Usuario.class);
		criteria.add( Restrictions.eq("email", email));
		
		Usuario resultado = null;
		resultado = (Usuario) criteria.uniqueResult();
		
		super.closeSession();
		
		return (resultado == null) ? false : true;
				
	}//existeUsuario

	
	@Override
	public Usuario getUsuarioByLogin(String email, byte[] password) {
		
		super.openSession();
		
		Criteria criteria = sesion.createCriteria(Usuario.class);
		criteria.add( Restrictions.eq("email", email));
		
		if (password != null) {
			criteria.add( Restrictions.eq("password", password) );	
		}
		
		Usuario resultado = null;
		resultado = (Usuario) criteria.uniqueResult();
		
		super.closeSession();
		
		return resultado;
		
	}//datosAccesoValidos

}//CLASS
