package TaskManager;

import javax.xml.bind.JAXBException;

/**
 *
 * @author Дмитрий
 */

public class Main {
    public static void main(String[] args) throws JAXBException {
        String file = "journal.xml";
        Journal journal = new Journal(1, "Задача", "Описание", 2, "Контакты");
        journal.setContacts("Contacts");
        TaskPlanner.objectToXml(journal, file);
        System.out.print(TaskPlanner.xmlToObject(file).toString());
    }
}