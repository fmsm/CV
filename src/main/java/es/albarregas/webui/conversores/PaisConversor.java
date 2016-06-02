package es.albarregas.webui.conversores;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import es.albarregas.DAOImpl.PaisDAOImpl;
import es.albarregas.modelos.Pais;

@FacesConverter("paisConversor")
public class PaisConversor implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String cadena) {

		PaisDAOImpl oDAO = new PaisDAOImpl();		
		List<Pais> sugerencias = oDAO.getPaisesLike(cadena);
		return sugerencias.get(0);
	}//getAsObject

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		

		String devolver = (objeto != null) ? ((Pais) objeto).getPais() : null;
		return devolver;

	}//getAsString

}
