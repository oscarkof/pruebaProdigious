/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.prodigious.util;

import com.test.prodigious.rest.model.Festivity;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author osmar
 */
public class Database {
    public static boolean save(Object object){
        boolean returnValue = true;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(object);
            tx.commit();
        }catch (Exception e) {
            returnValue = false;
            e.printStackTrace();
        }
        return returnValue;
    }
    
    public static boolean update(Object object){
        boolean returnValue = true;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(object);
            tx.commit();
        }catch (Exception e) {
            returnValue = false;
            e.printStackTrace();
        }
        return returnValue;
    }
    
    public static List<Festivity> getAllFestivities(){
        List<Festivity> festivities = new ArrayList<>();
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query queryFestivities = session.createQuery("from Festivity f");
            festivities = queryFestivities.list();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return festivities;
    }
}
