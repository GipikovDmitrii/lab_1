package TaskManager;

/**
 *
 * @author Дмитрий
 */

public interface JournalInterface {

    /**
     * Изменение номера задачи
     *
     */
    void setId(int id);

    /**
     * Получение номера задачи
     */
    int getId();

    /**
     * Изменение названия задачи
     */
    void setName(String name);

    /**
     * Получение названия задачи
     * @return
     */
    String getName();

    /**
     * Изменение описания задачи
     */
    void setDescription(String description);

    /**
     * Получение описания задачи
     * @return
     */
    String getDescription();

    /**
     * Изменение времени задачи
     */
    void setTime(int time);

    /**
     * Получение времени задачи
     * @return
     */
    int getTime();

    /**
     * Изменение контактов
     */
    void setContacts(String contacts);

    /**
     * Получение контактов
     * @return
     */
    String getContacts();
}