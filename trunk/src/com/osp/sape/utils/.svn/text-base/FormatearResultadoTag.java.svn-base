package com.osp.sape.utils;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

/**
 * Le pone un color especial a un resultado en caso que cumpla o no ciertos umbrales.
 * Esta clase retorna es un nombre de class html para formatearlo.
 * @author Andres Aristizabal
 *  TODO sacar los umbrales dinamicamente.
 */
public class FormatearResultadoTag extends SimpleTagSupport {

	private Logger logs;
	private boolean debug = false;	//este se lo aprendi a Mauro
	
	/**
	 * Es el tipo de variable a formatear.
	 */
	private String tipoResultado;
	
	private String valor;
	
	
	public FormatearResultadoTag() {
		logs = Logger.getLogger(getClass());
		debug = logs.isDebugEnabled();
	}
	
	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public void doTag() throws IOException {
		if (debug) logs.debug("doTag. tipoResultado: " + tipoResultado);
		String claseRetorno = "";
		JspWriter out = getJspContext().getOut();
		if (tipoResultado == null) out.write(claseRetorno);
		if (tipoResultado.equals("signalNoiseRatioMargin")) {
			if (debug) logs.debug("Entro por signalNoiseRatioMargin. Valor: " + valor);
			int pos = valor.indexOf(" dB"); 
			if (pos != -1) {
				try {
					String datoParse = valor.substring(0, pos);
					double valorNum = Double.parseDouble(datoParse);
					if (valorNum > 6) {
						claseRetorno = "valorUp";
					} else {
						claseRetorno = "valorDown";
					}
				} catch (NumberFormatException e) {
					logs.error(e);
				}
			}
		} else if (tipoResultado.equals("txPower")) {
			if (debug) logs.debug("Entro por txPower. Valor: " + valor);
			int pos = valor.indexOf(" dBm"); 
			if (pos != -1) {
				try {
					String datoParse = valor.substring(0, pos);
					double valorNum = Double.parseDouble(datoParse);
					if (valorNum > 8) {
						claseRetorno = "valorUp";
					} else {
						claseRetorno = "valorDown";
					}
				} catch (NumberFormatException e) {
					logs.error(e);
				}
			}
		} else if (tipoResultado.equals("txPower")) {
			if (debug) logs.debug("Entro por txPower. Valor: " + valor);
			int pos = valor.indexOf(" dBm"); 
			if (pos != -1) {
				try {
					String datoParse = valor.substring(0, pos);
					double valorNum = Double.parseDouble(datoParse);
					if (valorNum > 8) {
						claseRetorno = "valorUp";
					} else {
						claseRetorno = "valorDown";
					}
				} catch (NumberFormatException e) {
					logs.error(e);
				}
			}
		} else if (tipoResultado.equals("estadoPing")) {
			if (valor.equals("OK")) {
				claseRetorno = "estadoOk";
			} else if (valor.equals("ERROR")) {
				claseRetorno = "estadoError";
			} 
		} else if (tipoResultado.equals("atenuacionDown")) {
			if (debug) logs.debug("Entro por atenuacionDown. Valor: " + valor);
			int pos = valor.indexOf(" dB"); 
			if (pos != -1) {
				try {
					String datoParse = valor.substring(0, pos);
					double valorNum = Double.parseDouble(datoParse);
					if (valorNum < 63) {
						claseRetorno = "valorUp";
					} else {
						claseRetorno = "valorDown";
					}
				} catch (NumberFormatException e) {
					logs.error(e);
				}
			}
		} else if (tipoResultado.equals("atenuacionUp")) {
			if (debug) logs.debug("Entro por atenuacionDown. Valor: " + valor);
			int pos = valor.indexOf(" dB"); 
			if (pos != -1) {
				try {
					String datoParse = valor.substring(0, pos);
					double valorNum = Double.parseDouble(datoParse);
					if (valorNum < 45) {
						claseRetorno = "valorUp";
					} else {
						claseRetorno = "valorDown";
					}
				} catch (NumberFormatException e) {
					logs.error(e);
				}
			}
		}
		out.write(claseRetorno);
		out.flush();
	}
	
	
}
