package com.osp.sape.reportes;

/**
 * @hibernate.class table="reporteadorplantilla"
 * @author Andres Aristizabal
 *
 */
public class ReporteadorPlantilla {

	private int id;
	private String usuario;
	private String fechaCambio;
	private String titulo;
	private String campos;
	private String etiquetas;
	private String orderBy;
	private String colCondicion;
	private String tipoCondicion;
	private String valorCondicion;
	private String desdeFecha;
	private String hastaFecha;
	private String formato;
	private String valorMedio;
	private String fechaEnvio;
		

	/**
	 * @hibernate.property type="string" column="campos" 
	 * @return
	 */
	public String getCampos() {
		return campos;
	}
	
	public void setCampos(String campos) {
		this.campos = campos;
	}
	
	/**
	 * @hibernate.property type="string"  column="colcondicion"
	 * @return
	 */
	public String getColCondicion() {
		return colCondicion;
	}
	
	public void setColCondicion(String colCondicion) {
		this.colCondicion = colCondicion;
	}
	
	/**
	 * @hibernate.property type="string"  column="desdefecha"
	 * @return
	 */
	public String getDesdeFecha() {
		return desdeFecha;
	}
	
	public void setDesdeFecha(String desdeFecha) {
		this.desdeFecha = desdeFecha;
	}
	
	/**
	 * @hibernate.property type="string"  column="etiquetas"
	 * @return
	 */
	public String getEtiquetas() {
		return etiquetas;
	}
	
	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}
	
	/**
	 * @hibernate.property type="string"  column="fechacambio"
	 * @return
	 */
	public String getFechaCambio() {
		return fechaCambio;
	}
	
	public void setFechaCambio(String fechaCambio) {
		this.fechaCambio = fechaCambio;
	}
	
	/**
	 * @hibernate.property type="string"  column="fechaenvio"
	 * @return
	 */
	public String getFechaEnvio() {
		return fechaEnvio;
	}
	
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	
	/**
	 * @hibernate.property type="string"  column="hastafecha"
	 * @return
	 */
	public String getHastaFecha() {
		return hastaFecha;
	}
	
	public void setHastaFecha(String hastaFecha) {
		this.hastaFecha = hastaFecha;
	}
	/**
	 * @hibernate.id type="int"  column="id" unsaved-value = "-1" generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="idreporteador"
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @hibernate.property type="string"  column="formato"
	 * @return
	 */
	public String getFormato() {
		return formato;
	}
	
	public void setFormato(String medioEnvio) {
		this.formato = medioEnvio;
	}
	
	/**
	 * @hibernate.property type="string"  column="orderby"
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}
	
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	/**
	 * @hibernate.property type="string"  column="tipocondicion"
	 * @return
	 */
	public String getTipoCondicion() {
		return tipoCondicion;
	}
	
	public void setTipoCondicion(String tipoCondicion) {
		this.tipoCondicion = tipoCondicion;
	}
	
	/**
	 * @hibernate.property type="string"  column="titulo"
	 * @return
	 */
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @hibernate.property type="string"  column="usuario"
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * @hibernate.property type="string"  column="valorcondicion"
	 * @return
	 */
	public String getValorCondicion() {
		return valorCondicion;
	}
	
	public void setValorCondicion(String valorCondicion) {
		this.valorCondicion = valorCondicion;
	}
	
	/**
	 * @hibernate.property type="string"  column="valormedio"
	 * @return
	 */
	public String getValorMedio() {
		return valorMedio;
	}
	
	public void setValorMedio(String valorMedio) {
		this.valorMedio = valorMedio;
	}
	

	private String paramString() {
		return "id=" + id + ";usuario=" + usuario + ";fechaCambio=" + fechaCambio + ";titulo=" + titulo + ";campos=" +
		campos + ";etiquetas=" + etiquetas + ";orderBy=" + orderBy + ";colCondicion=" + colCondicion + ";tipoCondicion=" + 
		tipoCondicion + ";valorCondicion=" + valorCondicion + ";desdeFecha=" + desdeFecha + ";hastaFecha=" + hastaFecha +
		";formato=" + formato + ";valorMedio=" + valorMedio + ";fechaEnvio=" + fechaEnvio;
	}
	
	public String toString() {
		return getClass().getName() + "[" + paramString() + "]";
	}
}