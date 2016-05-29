package es.albarregas.DAO;

import es.albarregas.modelos.Usuario;

public interface UsuarioDAO {
	
	public boolean existeUsuario(String email);
	
	public Usuario getUsuarioByLogin(String email, byte[] password);
	
	public void save(Usuario usuario);
	
	public void update(Usuario usuario);
	
	public void saveOrUpdate(Usuario usuario);
	
	public void delete(Usuario usuario);
	
}//INTERFACE
