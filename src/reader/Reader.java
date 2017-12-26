package reader;

import journal.Journal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 *
 * @author Dmitrii
 */

public class Reader {
    public static Journal loadJournal(String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Journal.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Journal) unmarshaller.unmarshal(new File(file));
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return new Journal();
    }
}
