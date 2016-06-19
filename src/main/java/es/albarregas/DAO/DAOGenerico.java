package es.albarregas.DAO;

import java.util.List;

public class DAOGenerico<T> extends HibernateUtil {
	
	private String mensajeError = "";

	
	public void save(T entidad) {
        
		String mensaje = "[INFO - ACCESSO BD] .save " + entidad.getClass();
		
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            super.sesion.save(entidad);
            //super.sesion.flush();
            super.transaccion.commit();
                        
        } catch (org.hibernate.exception.GenericJDBCException sqlE) {
            super.transaccion.rollback();
            
            this.setMensajeError(sqlE.getMessage());
            mensaje = "[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .save()\n" + sqlE;

        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            mensaje = "[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .save()\n" + e;
            
        } finally {
            super.closeSession();
        }
		
        System.out.println(mensaje);
        
	}//save	
	
	
	public void saveAll(List<T> entidades) {
        
		String mensaje = "[INFO - ACCESSO BD] .saveAll " + entidades.getClass();
		
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            
            for (T entidad: entidades) {
            	super.sesion.save(entidad);
            }//for
            
            //super.sesion.flush();
            super.transaccion.commit();
                        
        } catch (org.hibernate.exception.GenericJDBCException sqlE) {
            super.transaccion.rollback();
            
            this.setMensajeError(sqlE.getMessage());
            mensaje = "[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .saveAll()\n" + sqlE;

        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            mensaje = "[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .saveAll()\n" + e;
            
        } finally {
            super.closeSession();
        }
		
        System.out.println(mensaje);
        
	}//save	
	
	
	
	public void update(T entidad) {
		
		String mensaje = "[INFO - ACCESSO BD] .update " + entidad.getClass();
		
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            super.sesion.update(entidad);
            super.transaccion.commit();
            
        } catch (org.hibernate.exception.GenericJDBCException sqlE) {
            super.transaccion.rollback();
            
            this.setMensajeError(sqlE.getMessage());
            mensaje = "[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .save()\n" + sqlE;
                        
        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            mensaje = "[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .update()\n" + e;
            
        } finally {
            super.closeSession();
        }
        
        System.out.println(mensaje);		
		
	}//update

	
	public void saveOrUpdate(T entidad) {
        
		String mensaje = "[INFO - ACCESSO BD] .saveOrUpdate " + entidad.getClass();
		
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            super.sesion.saveOrUpdate(entidad);
            super.transaccion.commit();
                        
        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            mensaje = "[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .saveOrUpdate()\n" + e;
            
        } finally {
            super.closeSession();
        }
        
        System.out.println(mensaje);		
		
	}//save		
	
	
	public void delete(T entidad) {
        
		String mensaje = "[INFO - ACCESSO BD] .delete " + entidad.getClass();
		
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            super.sesion.delete(entidad);
            super.transaccion.commit();
                        
        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            mensaje = "[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .delete()\n" + e;
            
        } finally {
            super.closeSession();
        }
        
        System.out.println(mensaje);		
		
	}//delete	
	

	
	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
}//CLASS
