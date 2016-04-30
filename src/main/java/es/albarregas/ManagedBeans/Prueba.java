
package es.albarregas.ManagedBeans;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class Prueba implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String texto = "Hola Mundo!!! (desde un managedbean con anotacion @Named) ";
	
	private boolean btnDisabled = false;
	
	
	public void textoANull() {
		this.texto = null;
	}
	
	public void booleanATrue() {
		this.btnDisabled = true;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public boolean isBtnDisabled() {
		return btnDisabled;
	}
	public void setBtnDisabled(boolean btnDisabled) {
		this.btnDisabled = btnDisabled;
	}
	

}//CLASS
