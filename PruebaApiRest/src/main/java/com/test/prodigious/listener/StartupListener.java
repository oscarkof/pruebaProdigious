package com.test.prodigious.listener;

import com.test.prodigious.util.HibernateUtil;
import com.test.prodigious.util.ReadResource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class responsible for initializing the Hibernate context and creating the SessionFactory
 * on the other hand loads the data from the XML file to the start of the server
 * @author Oscar Martinez
 */
public class StartupListener implements ServletContextListener {  
  
    /**
     * Method that executes loading the context of the war, is programmed logic
     * to load the session with the database and create the SessionFactory
     * @param event Servlet event context
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {  
        HibernateUtil.getSessionFactory(); // Just call the static initializer of that class   
        ReadResource.loadExternalFestivities();
    }  
  
    /**
     * Method that executes on destroying servlet context, in un-deploy time
     * @param event Servlet event context
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {  
        HibernateUtil.getSessionFactory().close(); // Free all resources   
    }  
}  