
package es.albarregas.utilidades;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Francisco
 */
public class UtilSesion {
     
//    public static String getSessionID(){
//        return FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
//    }
    
    private static HttpSession getSession() {
      return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
//    private static HttpSession getNewSession() {
//      return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//    }    

    
    public static String getSessionNombreApellidos() {
      HttpSession session = getSession();
      if ( session != null && session.getAttribute("nombre") != null && session.getAttribute("apellidos") != null)
          return String.format("%s %s" , (String) session.getAttribute("nombre"), (String) session.getAttribute("apellidos")) ;
      else
          return null;
    }//getSessionNombreApellidos
    

    public static String getSessionNombre() {
        HttpSession session = getSession();
        if ( session != null && session.getAttribute("nombre") != null )
            return (String) session.getAttribute("nombre") ;
        else
            return null;
      }//getSessionNombre
    
    
    public static String getSessionApellidos() {
        HttpSession session = getSession();
        if ( session != null && session.getAttribute("apellidos") != null )
            return (String) session.getAttribute("apellidos") ;
        else
            return null;
      }//getSessionApellidos
    
    
    public static String getSessionLogin() {
        HttpSession session = getSession();
        if ( session != null && session.getAttribute("login") != null)
            return (String) session.getAttribute("login");
        else
            return null;
    }//getSessionLogin
           
}//CLASS
