package com.osp.sape.maestros.auditoria;

import java.util.Date;

/**
 * @hibernate.class table="rastrossape"
 * @author Andres Aristizabal
 *
 */
public class RastroSape {

	private int id;
	private Date fecha;
	private String usuario;
	private int tipo;
	private String descripcion;
	
	public RastroSape() { }
	
	public RastroSape(Date fecha, String usuario, int tipo, String descripcion) {
		this.fecha = fecha;
		this.usuario = usuario;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	/**
	 * @hibernate.property type="string"
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String description) {
		this.descripcion = description;
	}
	
	/**
	 * @hibernate.property type="timestamp"
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @hibernate.id type="int" generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="idrastrossape"
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @hibernate.property tipe="integer"
	 * @return
	 */
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @hibernate.property type="string"
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String toString() {
		return getClass().getName() + "[id=" + id + "; fecha=" + fecha + "; tipo=" + tipo + 
				"; usuario=" + usuario + "; descripcion=" + descripcion + "]";
	}
	
}
