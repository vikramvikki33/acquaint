package com.sounds.bvs.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		JPAUtil.closeEntityManagerFactory();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		JPAUtil.buildEntityManagerFactory();
	}

}
