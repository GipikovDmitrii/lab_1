package TaskManager;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 *
 * @author Дмитрий
 */

@XmlRootElement(name = "task")
@XmlType(propOrder = {"name", "description", "time", "contacts"})
public class Task {

    private String name;
    private String description;
    private String time;
    private String contacts;

    Task() {}

    Task(String name, String description, String time, String contacts) {

        this.setName(name);
        this.setDescription(description);
        this.setTime(time);
        this.setContacts(contacts);
    }

    /**
     * Получение названия задачи
     */
    @XmlElement
    public String getName() {
        return name;
    }

    /**
     * Изменение названия задачи
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Получение описания задачи
     * @return
     */
    @XmlElement
    public String getDescription() {
        return description;
    }

    /**
     * Изменение описания задачи
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Получение времени задачи
     * @return
     */
    @XmlElement
    public String getTime() {
        return time;
    }

    /**
     * Изменение времени задачи
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Получение контактов
     * @return
     */

    @XmlElement
    public String getContacts() {
        return contacts;
    }

    /**
     * Изменение контактов
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return time + " | " + name;
    }
}