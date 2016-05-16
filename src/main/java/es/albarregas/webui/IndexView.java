package es.albarregas.webui;

import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named
@ViewScoped
public class IndexView implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final LatLng COORDALBARREGAS = new LatLng(38.9233251, -6.3368287);
	
	private String username;
	private String password;
	
	private MapModel mapModel = new DefaultMapModel();

	public MapModel getMapModel() {
		
//		StringBuilder ruta = new StringBuilder();
//		ruta.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()).append("/resources/imagenes/marker.png");
//				
//		mapModel.addOverlay( new Marker(COORDALBARREGAS,"IES Albarregas", null, ruta.toString()) );
		
		mapModel.addOverlay( new Marker(COORDALBARREGAS,"IES Albarregas") );
		return mapModel;
	}

	public Date getFecha() {
		return new Date();
	}	
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}//CLASS
