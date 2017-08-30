
import com.test.prodigious.rest.model.Festivities;
import com.test.prodigious.rest.model.Festivity;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author osmar
 */
public class TestXML {

    public static void main(String[] args) {

        try {

            File file = new File("D:\\data.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Festivities.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Festivities festivities = (Festivities) jaxbUnmarshaller.unmarshal(file);
            
            for (Festivity festivity : festivities.getFestivities()) {
                System.out.println(festivity);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
