
package com.osp.sape.utils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;



public class CustomDataSource implements JRDataSource
{


	/**
	 *
	 */
    
    private String nameFields[];
	private Object[][] data;


	private int index = -1;
	

	/**
	 *
	 */
	public CustomDataSource(Object dat[][], String fields[])
	{
	    data=dat;
	    nameFields = fields;
	}
	
	public CustomDataSource()
	{
	}


	/**
	 *
	 */
	public boolean next() throws JRException
	{
		index++;

		return (index < data.length);
	}

	
	public Object[][] getData(){
		return data;
	}

	/**
	 *
	 */
	public Object getFieldValue(JRField field) throws JRException
	{
		Object value = null;
		
		String fieldName = field.getName();
	
		for(int i=0;i<nameFields.length;i++){
		    
		    if(nameFields[i].equals(fieldName)){
		        
		        value = data[index][i];
		        break;
		    }
		
		}
		
		
		/*
		if ("id".equals(fieldName))
		{
			value = data[index][0];
		}
		else if ("nick".equals(fieldName))
		{
			value = data[index][1];
		}
		else if ("password".equals(fieldName))
		{
			value = data[index][2];
		}
		else if ("activo".equals(fieldName))
		{
			value = data[index][3];
		}
		else if ("nivel".equals(fieldName))
		{
			value = data[index][4];
		}
		else if ("fechaAlta".equals(fieldName))
		{
			value = data[index][5];
		}
		else if ("nombre".equals(fieldName))
		{
			value = data[index][6];
		}*/
		
		return value;
	}


}
