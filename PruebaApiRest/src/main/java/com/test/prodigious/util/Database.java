package com.test.prodigious.util;

import com.test.prodigious.rest.model.Festivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Class in charge of interacting with the database, everything related to 
 * the CRUD operations of the festivities as well as the consultations by any of its wildcards
 * @author Oscar Martinez
 */
public class Database {
    
    /**
     * Method for storing a festivity in the database
     * @param object Festivity object
     * @return True in case it stores successfully, false otherwise
     */
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
    
     /**
     * Method for updating an especific festivity in the database
     * @param object Festivity object
     * @return True in case it updates successfully, false otherwise
     */
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
    
    /**
     * Method Method in charge of validating that there is a festivity in the database
     * @param festivity Festivity object
     * @return true in case it exists, false otherwise
     */
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
    
    /**
     * Return all the festivities of the database
     * @return Festivity list
     */
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
    
    /**
     * metodo encargado de consultar por medio de los comodines
     * @param name name of the Festivity
     * @param start start date of the Festivity
     * @param min min date of the Festivity to ask
     * @param max max date of the Festivity to ask
     * @param place place where is going to celebrate the festivity
     * @return Festivity list
     */
    public static List<Festivity> getFestivities(String name, String start, String min, String max, String place){
        List<Festivity> festivities = new ArrayList<>();
        String queryOriginal = "from Festivity f ";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if(!name.equals("default")){
                if(!queryOriginal.contains("where")){
                    queryOriginal += " where";
                }
                
                if(!queryOriginal.endsWith("where")){
                    queryOriginal += " and";
                }
                
                queryOriginal += " name = :name";
            }
            if(!place.equals("default")){
                if(!queryOriginal.contains("where")){
                    queryOriginal += " where";
                }
                
                if(!queryOriginal.endsWith("where")){
                    queryOriginal += " and";
                }
                
                queryOriginal += " place = :place";
            }
            
            if(!start.equals("default")){
                if(!queryOriginal.contains("where")){
                    queryOriginal += " where";
                }
                
                if(!queryOriginal.endsWith("where")){
                    queryOriginal += " and";
                }
                
                queryOriginal += " start = :start";
            }
            
            if(!min.equals("default") && !max.equals("default")){
                if(!queryOriginal.contains("where")){
                    queryOriginal += " where";
                }
                
                if(!queryOriginal.endsWith("where")){
                    queryOriginal += " and";
                }
                
                queryOriginal += " start between :min and :max";
            }
            System.out.println("query final: "+queryOriginal);
            Query queryFestivities = session.createQuery(queryOriginal);
            String[] params = queryFestivities.getNamedParameters();
            for (String param : params) {
                if(param.equals("name")){
                    queryFestivities.setParameter("name", name);
                }else if(param.equals("place")){
                    queryFestivities.setParameter("place", place);
                }else if(param.equals("start")){
                    SimpleDateFormat sdf = new SimpleDateFormat(ReadResource.formatDate);
                    Date startDate = sdf.parse(start);
                    queryFestivities.setParameter("start", startDate);
                }else if(param.equals("min")){
                    SimpleDateFormat sdf = new SimpleDateFormat(ReadResource.formatDate);
                    Date minDate = sdf.parse(min);
                    queryFestivities.setParameter("min", minDate);
                }else if(param.equals("max")){
                    SimpleDateFormat sdf = new SimpleDateFormat(ReadResource.formatDate);
                    Date maxDate = sdf.parse(max);
                    queryFestivities.setParameter("max", maxDate);
                }
            }
            festivities = queryFestivities.list();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return festivities;
    }
    
    /**
     * The method responsible for eliminating all the festivities, is executed 
     * a moment before loading the file xml with all the information of the database
     * @return number of the rows deleted
     */
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
