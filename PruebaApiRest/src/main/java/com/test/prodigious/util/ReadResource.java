package com.test.prodigious.util;

import com.test.prodigious.rest.model.Festivities;
import com.test.prodigious.rest.model.Festivity;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Class responsible for reading the XML file and loading it into the database
 * @author Oscar Martinez
 */
public class ReadResource {
    
    public static String formatDate;
    
    /**
     * Method in charge of persisting all the XML information in the database   
     */
    public static void loadExternalFestivities(){
        try {
            System.out.println("Begin load XML");
            Properties prop = new Properties();
            InputStream input = null;
            
            input = ReadResource.class.getClassLoader().getResourceAsStream("festivities.properties");

            // load a properties file
            prop.load(input);
            
            File file = new File(prop.getProperty("file.xml.location"));
            formatDate = prop.getProperty("dateformat");
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
