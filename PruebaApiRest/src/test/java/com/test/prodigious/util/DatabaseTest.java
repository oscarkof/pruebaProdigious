/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.prodigious.util;

import com.test.prodigious.rest.model.Festivity;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author osmar
 */
public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class Database.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Festivity object = new Festivity();
        object.setStart(Calendar.getInstance().getTime());
        object.setEnd(Calendar.getInstance().getTime());
        object.setName("Test Fest"+System.currentTimeMillis());
        object.setPlace("Narnia");
        boolean expResult = true;
        boolean result = Database.save(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class Database.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Festivity object = new Festivity();
        object.setStart(Calendar.getInstance().getTime());
        object.setEnd(Calendar.getInstance().getTime());
        object.setName("Test Fest 2");
        object.setPlace("Howards");
        boolean expResult = true;
        boolean result = Database.update(object);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAllFestivities method, of class Database.
     */
    @Test
    public void testGetAllFestivities() {
        System.out.println("getAllFestivities");
        List<Festivity> result = Database.getAllFestivities();
        assertTrue(result.size() > 0 );
    }
    
}
