package TaskManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

/**
 *
 * @author Дмитрий
 */

public class Journal {
    private List<Task> taskList;

    Journal() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void delete(Task task) {
        this.taskList.remove(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int index) {
        Task task = getTaskList().get(index);
        return task;
    }

    public int getSize() {
        int size = this.taskList.size();
        return size;
    }

    public void objectToXml(Journal journal, OutputStream os) throws IOException {
        for (int i = 0; i < journal.getSize(); i++) {
           Task task = journal.getTask(i);
            try {
                JAXBContext context = JAXBContext.newInstance(Task.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(task, os);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            os.flush();
        }
        os.close();
    }

    public void xmlToObject(InputStream is) {

        try {
            JAXBContext context = JAXBContext.newInstance(Task.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.unmarshal(is);
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream os = new FileOutputStream("journal.xml");
        FileInputStream is = new FileInputStream("journal.xml");
        String file = "journal.xml";
        File file1 = new File("journal.xml");

        Task task = new Task(1, "1", "1", 1, "1");
        Task task1 = new Task(2, "2", "2", 2, "2");
        Task task2 = new Task(3, "3", "3", 3, "3");
        Journal journal = new Journal();
        journal.add(task);
        journal.add(task1);
        journal.add(task2);
        //journal.delete(task);
        journal.objectToXml(journal, os);
        System.out.println(journal.getTask(0));
        System.out.println(journal.getTaskList());
        System.out.println(journal.getSize());
        //journal.xmlToObject(is);
    }
}