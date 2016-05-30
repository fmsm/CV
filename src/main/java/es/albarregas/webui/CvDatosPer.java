
package es.albarregas.webui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class CvDatosPer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellidos;
	private String genero;
	private Date fechaNacimiento;
	private String tipoIdentificacion;
	private String identificacion;
	private String codPostal;
	private String provincia;
	private String municipio;
	private String direccion;
	private String telefonoDomicilio;
	private String telefonoMovil;
	private String email;
	private String webPersonal;
	private String servicio;
	private String nombreUsuario;	
	
	private UploadedFile file;
	private StreamedContent image;	
	
	
	public void handleFileUpload(FileUploadEvent event) {
        
		this.file = event.getFile();
		
		InputStream stream = null;
		
		try {
			stream = this.file.getInputstream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		this.image = new DefaultStreamedContent(stream, "image/jpeg");
		
        FacesContext.getCurrentInstance().addMessage("mensajeGrowlFoto", new FacesMessage(FacesMessage.SEVERITY_INFO, "Imagen subida con éxito", "") );
        System.out.println( file.getFileName());
    }
	
	public void buscarCP() {
	}
	
	/*
	 * Getters y Setters
	 */
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefonoDomicilio() {
		return telefonoDomicilio;
	}
	public void setTelefonoDomicilio(String telefonoDomicilio) {
		this.telefonoDomicilio = telefonoDomicilio;
	}
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebPersonal() {
		return webPersonal;
	}
	public void setWebPersonal(String webPersonal) {
		this.webPersonal = webPersonal;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}
	
		
}//CLASS
