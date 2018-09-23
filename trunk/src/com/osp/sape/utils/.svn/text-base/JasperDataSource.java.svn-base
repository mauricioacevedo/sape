/*
 * Created on Apr 28, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JasperDataSource implements JRDataSource {

	private ResultSet query;
    private String nameFields[];
    private Connection connection;
    private org.apache.log4j.Logger logs;
    
	public JasperDataSource(ResultSet rs, String names[], Connection c) {
		
		query = rs;
		nameFields = names;
		connection = c; 
		logs = org.apache.log4j.Logger.getLogger(getClass());
	}

	public boolean next() throws JRException {
		

		
		try {
			return query.next();
		} catch (SQLException e) {
			e.printStackTrace();
			logs.error(e);
			return false;
		}
		

	}

	public Object getFieldValue(JRField field) throws JRException {
		
		Object value = null;
		
		String fieldName = field.getName();
		
		try {
			value=query.getString(fieldName);
		} catch (SQLException e) {
			e.printStackTrace();
			logs.error(e);
			value = null;
		}
		
		return value;
	
	}

}
