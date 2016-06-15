package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Imagenes")
public class Imagen implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idUsuario;
	private byte[] content;

	
	public Imagen() {
	}

	
	@Id	
	@Column(name = "IdUsuario", unique = true, nullable = false)	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "Content", nullable = false)
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
