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
        Transaction tx = null;
        if (existFestivity((Festivity) object)) {
            returnValue = false;
        }else{
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                session.persist(object);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                returnValue = false;
                e.printStackTrace();
            }
        }
        return returnValue;
    }
    
    public static boolean update(Object object){
        boolean returnValue = true;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(object);
            tx.commit();
        }catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            returnValue = false;
            e.printStackTrace();
        }
        return returnValue;
    }
    
    public static boolean existFestivity(Festivity festivity){
        List<Festivity> festivities = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query queryFestivities = session.createQuery("from Festivity f where f.name = :name ");
            queryFestivities.setParameter("name", festivity.getName());
            festivities = queryFestivities.list();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return !festivities.isEmpty();
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
    
    public static int removeAllFestivities(){
        int returnValue = 0;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query queryRemoteAll = session.createQuery("delete from Festivity f");
            returnValue = queryRemoteAll.executeUpdate();
            tx.commit();
        }catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            returnValue = -1;
            e.printStackTrace();
        }
        return returnValue;
    }
    
}
