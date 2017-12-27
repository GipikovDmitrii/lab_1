package ui;

import journal.Journal;
import task.Task;
import writer.Writer;
import reader.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 *
 * @author Dmitrii
 */

public class MainWindow extends JFrame {
    private static final String PATH_ICON = "src/image/icon.png";
    private static final String XML_FILE_NAME = "journal.xml";
    private final String titleName = "Планировщик задач";
    private final String addTask = "Добавить задачу";
    private final String deleteTask = "Удалить задачу";
    private final String deleteAllTask = "Удалить все задачи";
    private static Journal journal = new Journal();
    private JButton addTaskButton = new JButton();
    private JEditorPane contactsEditorPane = new JEditorPane();
    private JLabel contactsLabel = new JLabel();
    private JScrollPane contactsScrollPane = new JScrollPane();
    protected static JButton deleteAllTaskButton = new JButton();
    private JButton deleteTaskButton = new JButton();
    private JEditorPane descriptionEditorPane = new JEditorPane();
    private JLabel descriptionLabel = new JLabel();
    private JScrollPane descriptionScrollPane = new JScrollPane();
    private static JList<Task> taskList = new JList<>();
    private JLabel taskListLabel = new JLabel();
    private JPanel taskListPanel = new JPanel();
    private JScrollPane taskListScrollPane = new JScrollPane();
    private static DefaultListModel model = new DefaultListModel();
    private static Reader reader = new Reader();
    private static Writer writer = new Writer();

    public MainWindow() {
        xmlToJournal(XML_FILE_NAME);
        createAndShowGUI();
        listUpdate();
        updateNotification(journal);
    }

    public static void journalToXml(Journal journal, String file) {
        writer.saveJournal(journal, file);
    }

    public static Journal xmlToJournal(String file) {
        return journal = reader.loadJournal(file);
    }

    public static void deleteTask(Task task) {
        journal.deleteTask(task);
        journalToXml(journal, XML_FILE_NAME);
    }

    public static void addNewTask(String name, String description, Date date, String contacts) {
        Task task = new Task(name, description, date, contacts);
        xmlToJournal(XML_FILE_NAME);
        journal.addTask(task);
        journalToXml(journal, XML_FILE_NAME);
        new NotificationWindow().addNotification(task);
    }

    public void updateNotification(Journal journal) {
        for (int i = 0; i < journal.getSize(); i++) {
            Task task = journal.getTask(i);
            new NotificationWindow().addNotification(task);
        }
    }

    //Update task list
    public static void listUpdate() {
        model.clear();
        for (int i = 0; i < journal.getSize(); i++) {
            Task task = journal.getTask(i);
            model.addElement(task);
        }
        taskList.setModel(model);
        if (journal.getSize() != 0) {
            deleteAllTaskButton.setEnabled(true);
        }
    }

    private void createAndShowGUI() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Image image = Toolkit.getDefaultToolkit().getImage(PATH_ICON);
        setBounds(dimension.width / 2 - 450, dimension.height / 2 - 300, 900, 600);
        setTitle(titleName);
        setIconImage(image);
        taskListScrollPane.setViewportView(taskList);
        taskListLabel.setText("Список задач:");
        contactsScrollPane.setViewportView(contactsEditorPane);
        contactsLabel.setText("Контакты:");
        descriptionScrollPane.setViewportView(descriptionEditorPane);
        descriptionLabel.setText("Описание:");
        addTaskButton.setText(addTask);
        deleteTaskButton.setText(deleteTask);
        deleteAllTaskButton.setText(deleteAllTask);
        contactsEditorPane.setEditable(false);
        descriptionEditorPane.setEditable(false);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ConfirmWindow().setVisible(true);
            }
        });

        taskList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (taskList.getSelectedValue() != null) {
                    Task task = taskList.getSelectedValue();
                    descriptionEditorPane.setText(task.getDescription());
                    contactsEditorPane.setText(task.getContacts());
                    deleteTaskButton.setEnabled(true);
                } else {
                    deleteTaskButton.setEnabled(false);
                    descriptionEditorPane.setText("");
                    contactsEditorPane.setText("");
                }
            }
        });

        addTaskButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                new AddTaskWindow().setVisible(true);
            }
        });

        deleteTaskButton.setEnabled(false);
        deleteTaskButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            Task task = journal.getTask(index);
            deleteTask(task);
            model.removeElementAt(index);
            if (journal.getSize() == 0) {
                deleteAllTaskButton.setEnabled(false);
            }
        });

        deleteAllTaskButton.setEnabled(false);
        deleteAllTaskButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (journal.getSize() != 0) {
                    for (int i = 0; i < journal.getSize(); i++) {
                        Task task = journal.getTask(i);
                        deleteTask(task);
                    }
                    deleteAllTaskButton.setEnabled(false);
                    listUpdate();
                }
            }
        });

        GroupLayout taskListPanelLayout = new GroupLayout(taskListPanel);
        taskListPanel.setLayout(taskListPanelLayout);
        taskListPanelLayout.setHorizontalGroup(
                taskListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(taskListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(taskListLabel)
                                                .addGap(212, 212, 212)
                                                .addComponent(descriptionLabel)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                                .addComponent(taskListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(taskListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(contactsLabel)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(contactsScrollPane)
                                                        .addComponent(descriptionScrollPane)))))
        );
        taskListPanelLayout.setVerticalGroup(
                taskListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(taskListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(taskListLabel)
                                        .addComponent(descriptionLabel))
                                .addGap(7, 7, 7)
                                .addGroup(taskListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(taskListScrollPane)
                                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(contactsLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(contactsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(addTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(deleteTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                                                .addComponent(deleteAllTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(taskListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(addTaskButton)
                                                .addComponent(deleteTaskButton))
                                        .addComponent(deleteAllTaskButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(taskListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
    }
}