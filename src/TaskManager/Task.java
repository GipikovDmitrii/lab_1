package TaskManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private Date time;
    private String contacts;

    Task() {}

    Task(String name, String description, Date time, String contacts) {

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
    public Date getTime() {
        return time;
    }

    /**
     * Изменение времени задачи
     */
    public void setTime(Date time) {
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
        StringBuilder sb = new StringBuilder();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String date = df.format(getTime());
        sb.append(date);
        sb.append(" | ");
        sb.append(getName());
        return sb.toString();
    }
}