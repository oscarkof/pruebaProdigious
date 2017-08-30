/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.prodigious.util;

import com.test.prodigious.rest.model.Festivities;
import com.test.prodigious.rest.model.Festivity;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author osmar
 */
public class ReadResource {
    public static void loadExternalFestivities(){
        try {
            System.out.println("Begin load XML");
            Properties prop = new Properties();
            InputStream input = null;
            
            input = ReadResource.class.getClassLoader().getResourceAsStream("festivities.properties");

            // load a properties file
            prop.load(input);
            
            File file = new File(prop.getProperty("file.xml.location"));
            JAXBContext jaxbContext = JAXBContext.newInstance(Festivities.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Festivities festivities = (Festivities) jaxbUnmarshaller.unmarshal(file);
            
            int removed = Database.removeAllFestivities();
            
            System.out.println("Registros Eliminados: "+removed);
            
            for (Festivity festivity : festivities.getFestivities()) {
                Database.save(festivity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
