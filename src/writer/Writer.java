package writer;

import journal.Journal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 *
 * @author Dmitrii
 */

public class Writer {
    public void saveJournal(Journal journal, String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Journal.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(journal, new File(file));
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
