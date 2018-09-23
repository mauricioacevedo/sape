package com.osp.sape.data;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

public interface HibernateConfiguration {

	public Configuration getConfiguration() throws HibernateException;
	
	public SessionFactory getSessionFactory() throws HibernateException;
}
