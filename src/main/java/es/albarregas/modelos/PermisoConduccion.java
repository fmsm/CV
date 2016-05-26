package es.albarregas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PermisoConduccion")
public class PermisoConduccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idPermisosConduccion;
	private DatoAlumno datoAlumno;
	private String tipo;

	
	public PermisoConduccion() {
	}

	
	@Id
	@Column(name = "IdPermisosConduccion", unique = true, nullable = false)
	public int getIdPermisosConduccion() {
		return this.idPermisosConduccion;
	}

	public void setIdPermisosConduccion(int idPermisosConduccion) {
		this.idPermisosConduccion = idPermisosConduccion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public DatoAlumno getDatoAlumno() {
		return this.datoAlumno;
	}

	public void setDatoAlumno(DatoAlumno datoAlumno) {
		this.datoAlumno = datoAlumno;
	}

	@Column(name = "Tipo", nullable = false, length = 3)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
