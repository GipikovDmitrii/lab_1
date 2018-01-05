package journal;

import task.Task;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dmitrii
 */

@XmlRootElement(name = "journal")
public class Journal {
    @XmlElementRef
    private List<Task> tasks;

    public Journal() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void deleteAllTask() {
        tasks.removeAll(tasks);
    }
}