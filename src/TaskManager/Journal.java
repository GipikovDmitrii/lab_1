package TaskManager;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.sql.Time;
import java.time.Instant;
import java.util.*;

/**
 *
 * @author Дмитрий
 */

@XmlRootElement(name = "journal")
public class Journal {

    @XmlElementRef
    private List<Task> tasks;

    Journal() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(Task task) {
        this.tasks.remove(task);
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void objectToXml(Journal journal, String file) {
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

    public Journal xmlToObject(String file) {
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