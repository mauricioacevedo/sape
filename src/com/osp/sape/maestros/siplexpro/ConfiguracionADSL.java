/*
 * Created on Apr 6, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.maestros.siplexpro;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Esta clase generaliza algunos metodos y variables utilizados en las distintas
 * clases de configuracion ADSL.
 * A traves de sus metodos getValues y setValues es capas de llevar la informacion desde y hacia
 * los beans que heredan de esta clase.
 * 
 * @author root
 *
 */
public class ConfiguracionADSL {
	
	private String nombreConfiguracion = this.getClass().getSimpleName();
	
	public String getNombreConfiguracion() {
		return nombreConfiguracion; 
	}
	public void setNombreConfiguracion(String nombreConfiguracion) {
		this.nombreConfiguracion = nombreConfiguracion;
	}
	private List values;
	
	/**
	 * Metodo para setear los valores del Bean. Es una lista que debe tener objetos
	 * de tipo String[] de 2 campos: 1. nombre del campo 2. valor del campo.
	 * 
	 * @param vals
	 */
	public void setValues(List vals) {
		//this.values = values;
		
		Object objetoActual = this;
		Field ff[]=getClass().getFields();
		
		if(ff.length != vals.size()){
			System.out.println("No coinciden los campos");
			return;
		}
		
		for(int i=0;i<ff.length;i++){
			
			String[] v = (String[])vals.get(i);
			try {
				Field f=objetoActual.getClass().getField(v[0]);
				if(v[1] == null) // PARA KE NO DANE EL VALOR QUE YA TIENE
					continue;
				f.set(objetoActual,v[1]);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Metodo utilizado para obtener tanto el valor como el nombre de las variables
	 * de cada configuracion y otros valores!!!!!
	 * 
	 * @return
	 */
	public List getValues(){
		
		List lista = new ArrayList();
		Object objetoActual = this;
		Field ff[]=getClass().getFields();

		condiciones = getCondiciones();
		nombres = getNombres();
		for(int i=0;i<ff.length;i++) {
			String[] v = new String[4];
			
			// Guarda el nombre de la variable actual
			v[0]= ff[i].getName();

			try {
				// Guarda el valor de esa variable en el momento
				v[1]= (String)ff[i].get(objetoActual);
				
				// Para almacenar las condiciones que debe cumplir la variable
				if(condiciones != null && condiciones.get(v[0]) != null){
					v[2]=(String)condiciones.get(v[0]);
				}else {
					v[2]=null;
				}
				// Almacena los label de cada variable
				if(nombres != null && nombres.get(v[0]) != null){
					v[3]=(String)nombres.get(v[0]);
				}else {
					v[3]="";
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				v[1] = null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				v[1] = null;
			}
			lista.add(i,v);
		}
		
		values = lista;
		return values;
	}
	
	
	private String idcabeza;
		
	public String getIdcabeza() {
		return idcabeza;
	}
	//public void setIdcabeza(String idcabeza) {
		//this.idcabeza = idcabeza;
	//}
	/**
	 * HashMap que contiene las condiciones a validar para cada campo en el jsp.
	 * Estas condiciones se definen en cada bean que hereda de esta clase.
	 */
	private HashMap condiciones;

	public HashMap getCondiciones() {
		
		return condiciones;
	}

	public void setCondiciones(HashMap condiciones) {
		this.condiciones = condiciones;
	}
	
	/**
	 * HashMap que contiene los labels (o nombres para el usuario final) para cada uno de los campos en el jsp.
	 * Estos nombres se definen en cada bean que hereda de esta clase.
	 */
	private HashMap nombres;

	public HashMap getNombres() {
		return nombres;
	}
	public void setNombres(HashMap nombres) {
		this.nombres = nombres;
	}
	/**
	 * en el hashmap se encuentra una relacion de variables contra los nombres que estas toman en las cabezas
	 * 
	 */
	private HashMap relacionNombresVariables;

	public HashMap getRelacionNombresVariables() {
		return relacionNombresVariables;
	}
	public void setRelacionNombresVariables(HashMap relacionNombresVariables) {
		this.relacionNombresVariables = relacionNombresVariables;
	}
	
	/**
	 * Metodo para retornar el verdadero nombre de la variable pasada por parametro.
	 * Retorna el nombre de la variable como la interpreta la cabeza de prueba.
	 * @param key
	 * @return
	 */
	public String getVar(String key){
		if(relacionNombresVariables != null)
			return (String) relacionNombresVariables.get(key);
		return null;
	}
	
}
