package TaskManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 *
 * @author Дмитрий
 */

public class TaskPlanner {

    public static void objectToXml(Journal journal, String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Journal.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(journal, new File(file));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Journal xmlToObject(String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Journal.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Journal)unmarshaller.unmarshal(new File(file));
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}