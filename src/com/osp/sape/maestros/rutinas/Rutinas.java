package com.osp.sape.maestros.rutinas;

import java.util.Date;
import java.lang.reflect.Field;
/**
 * @hibernate.class table="rutinas"
 */
public class Rutinas {

	public Integer id;
	/**
     * @hibernate.id type = "int" column = "id" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "idrutinas"
     */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String tipo;
    /**
     * @hibernate.property column = "tipo" type = "string"
     */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String valorTipo;
    /**
     * @hibernate.property column = "valortipo" type = "string"
     */ 	
	public String getValorTipo() {
		return valorTipo;
	}
	public void setValorTipo(String valorTipo) {
		this.valorTipo = valorTipo;
	}
	public String usuario;
    /**
     * @hibernate.property column = "usuario" type = "string"
     */
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String tipoDePrueba;
    /**
     * @hibernate.property column = "tipodeprueba" type = "string"
     */
	public String getTipoDePrueba() {
		return tipoDePrueba;
	}
	public void setTipoDePrueba(String tipoDePrueba) {
		this.tipoDePrueba = tipoDePrueba;
	}
	public String tipoHorario;
    /**
     * @hibernate.property column = "tipohorario" type = "string"
     */
	public String getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	public String horas;
    /**
     * @hibernate.property column = "horas" type = "string"
     */
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}

	public String dias;
    /**
     * @hibernate.property column = "dias" type = "string"
     */
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	}	
	public Date fechaInicial;
    /**
     * @hibernate.property column = "fechainicial" type = "date"
     */
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}	
	public String horaInicial;
    /**
     * @hibernate.property column = "horainicial" type = "string"
     */
	public String getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}
	
	public Date fechaFinal;
    /**
     * @hibernate.property column = "fechafinal" type = "date"
     */
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}	
	public String horaFinal;
    /**
     * @hibernate.property column = "horafinal" type = "string"
     */
	public String getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	public Boolean habilitado;
    /**
     * @hibernate.property column = "habilitado" type = "boolean"
     */

	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public java.util.Date fechaProgramada;
    /**
     * @hibernate.property column = "fechaprogramada" type = "timestamp"
     */
	public java.util.Date getFechaProgramada() {
		return fechaProgramada;
	}
	public void setFechaProgramada(java.util.Date fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}
	/**
	 * API Reflection in action!!!!!!!!!!!!!!!!!!!
	 */
	public String toString(){
		String ret = "";
		Object objetoActual = this;
		Field ff[]=getClass().getFields();
		ret =  getClass().getName()+" : [";
		
		String fieldName="";
		Object fieldValue="";
		String separator="";
		for(int i=0;i<ff.length;i++) {
			fieldName=ff[i].getName();
			try {
				fieldValue=ff[i].get(objetoActual);
			} catch (IllegalArgumentException e) {
				System.out.println("OCURRIO ERROR[1]: "+e);
			} catch (IllegalAccessException e) {
				System.out.println("OCURRIO ERROR[2]: "+e);
			}
			ret += separator+fieldName+"="+fieldValue;
			separator=",";
		}
		ret+=" ]";
		
		return ret;
	}

}
