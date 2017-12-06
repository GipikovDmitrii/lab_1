package TaskManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.*;

/**
 *
 * @author Дмитрий
 */

@XmlRootElement(name = "journal")
public class Journal {
    @XmlElement
    private List<Task> task;

    Journal() {
        this.task = new ArrayList<>();
    }

    public void add(Task task) {
        this.task.add(task);
    }

    public void delete(Task task) {
        this.task.remove(task);
    }

    public List<Task> getTaskList() {
        return task;
    }

    public Task getTask(int index) {
        Task task = getTaskList().get(index);
        return task;
    }

    public int getSize() {
        int size = this.task.size();
        return size;
    }

    public void objectToXml(Journal journal, OutputStream os) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(Journal.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(journal, os);
            os.flush();
            os.close();
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
        return null;
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream os = new FileOutputStream("journal.xml");
        String file = "journal.xml";

        Task task = new Task(1, "1", "1", 1, "1");
        Task task1 = new Task(2, "2", "2", 2, "2");
        Task task2 = new Task(3, "3", "3", 3, "3");
        Journal journal = new Journal();
        Journal journal1 = new Journal();
        Journal journal2 = new Journal();
        journal.add(task);
        journal.add(task1);
        journal.add(task2);
        //journal.delete(task);
        journal.objectToXml(journal, os);
        System.out.println(journal.getTask(0));
        System.out.println(journal.getTaskList());
        System.out.println(journal.getSize());
        journal2.xmlToObject(file);
        journal2.getSize();
        //journal2.objectToXml(journal2, os);
        System.out.println();journal.getTaskList();
    }
}