package com.test.prodigious.listener;

import com.test.prodigious.util.HibernateUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {  
  
    @Override
    public void contextInitialized(ServletContextEvent event) {  
        HibernateUtil.getSessionFactory(); // Just call the static initializer of that class      
    }  
  
    @Override
    public void contextDestroyed(ServletContextEvent event) {  
        HibernateUtil.getSessionFactory().close(); // Free all resources   
    }  
}  