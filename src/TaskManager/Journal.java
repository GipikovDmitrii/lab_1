package TaskManager;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Дмитрий
 */

@XmlRootElement(name = "journal")
public class Journal {
    private static final String xmlFile = "journal.xml";

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

    public static void objectToXml(Journal journal, String file) {
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

    public static Journal xmlToObject(String file) {
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

    public static void main(String[] args) throws IOException {

        Task task = new Task();
        Task task1 = new Task();
        Task task2 = new Task();
        Journal journal = new Journal();
        Journal journal2 = new Journal();
        journal.add(task);
        journal.add(task1);
        journal.add(task2);
        journal.delete(task2);
        objectToXml(journal, xmlFile);
        journal2.getSize();
        System.out.println(journal.getTaskList());
        journal2 = xmlToObject(xmlFile);
        System.out.println(journal2.getTaskList());
        System.out.println(journal.getTask(0).toString());
    }
}