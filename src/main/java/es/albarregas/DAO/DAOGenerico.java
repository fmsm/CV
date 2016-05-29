package es.albarregas.DAO;

public class DAOGenerico<T> extends HibernateUtil {
	
	private String mensajeError = "";

	
	public void save(T entidad) {
        
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            super.sesion.save(entidad);
            super.sesion.flush();
            super.transaccion.commit();
                        
        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            System.err.println("[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .save()\n" + e);
            
        } finally {
            super.closeSession();
        }
        
        System.out.println("[INFO - ACCESSO BD] .save " + entidad.getClass());		
		
	}//save	
	
	
	public void update(T entidad) {
        
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            super.sesion.update(entidad);
            super.transaccion.commit();
                        
        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            System.err.println("[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .update()\n" + e);
            
        } finally {
            super.closeSession();
        }
        
        System.out.println("[INFO - ACCESSO BD] .update " + entidad.getClass());		
		
	}//update

	
	public void saveOrUpdate(T entidad) {
        
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            super.sesion.saveOrUpdate(entidad);
            super.transaccion.commit();
                        
        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            System.err.println("[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .saveOrUpdate()\n" + e);
            
        } finally {
            super.closeSession();
        }
        
        System.out.println("[INFO - ACCESSO BD] .saveOrUpdate " + entidad.getClass());		
		
	}//save		
	
	
	public void delete(T entidad) {
        
        super.openSession();
        
        try {
            super.transaccion = super.sesion.beginTransaction();
            super.sesion.delete(entidad);
            super.transaccion.commit();
                        
        } catch (Exception e) {
            super.transaccion.rollback();
            
            this.setMensajeError(e.getMessage());
            System.err.println("[ERROR - es.albarregas.dao.DAOGenerico.java] Fallo en método .delete()\n" + e);
            
        } finally {
            super.closeSession();
        }
        
        System.out.println("[INFO - ACCESSO BD] .delete " + entidad.getClass());		
		
	}//delete	
	

	
	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
}//CLASS
