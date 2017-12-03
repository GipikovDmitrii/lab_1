package TaskManager;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Дмитрий
 */

@XmlRootElement(name = "task")
@XmlType(propOrder = {"name", "description", "time", "contacts"})
public class Journal implements JournalInterface {

    private int id;
    private String name;
    private String description;
    private int time;
    private String contacts;

    Journal() {}

    Journal(int id, String name, String description, int time, String contacts) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setTime(time);
        this.setContacts(contacts);
    }

    @Override
    @XmlAttribute
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getTime() {
        return id;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String getContacts() {
        return contacts;
    }

    @Override
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "task{" +
                "id=" + id +
                ", name='" + name +'\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", contacts='" + contacts + '\'' +
                '}';
    }
}