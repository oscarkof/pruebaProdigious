package com.test.prodigious.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Oscar Martinez
 */
public class HibernateUtil { 

    private static SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static synchronized SessionFactory getSessionFactory() {
     if (sessionFactory == null) {
          sessionFactory = new Configuration().configure().buildSessionFactory();
     }
     return sessionFactory;
}
}
