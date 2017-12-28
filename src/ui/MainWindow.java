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
    protected Journal journal = new Journal();
    protected JButton deleteAllTaskButton = new JButton();
    private JButton addTaskButton = new JButton();
    private JEditorPane contactsEditorPane = new JEditorPane();
    private JLabel contactsLabel = new JLabel();
    private JScrollPane contactsScrollPane = new JScrollPane();
    private JButton deleteTaskButton = new JButton();
    private JEditorPane descriptionEditorPane = new JEditorPane();
    private JLabel descriptionLabel = new JLabel();
    private JScrollPane descriptionScrollPane = new JScrollPane();
    private JList<Task> taskList = new JList<>();
    private JLabel taskListLabel = new JLabel();
    private JPanel taskListPanel = new JPanel();
    private JScrollPane taskListScrollPane = new JScrollPane();
    private final String titleName = "Планировщик задач";
    private final String addTask = "Добавить задачу";
    private final String deleteTask = "Удалить задачу";
    private final String deleteAllTask = "Удалить все задачи";
    public static final String PATH_ICON = "src/image/icon.png";
    public static final String XML_FILE_NAME = "journal.xml";

    private DefaultListModel model = new DefaultListModel();
    private AddTaskWindow addTaskWindow = new AddTaskWindow(this);
    private SystemTrayWindow systemTrayWindow = new SystemTrayWindow(this);
    private ConfirmWindow confirmWindow = new ConfirmWindow(this);

    private Reader reader = new Reader();
    private Writer writer = new Writer();

    public MainWindow() {
        xmlToJournal(XML_FILE_NAME);
        createAndShowGUI();
        listUpdate();
        updateNotification(journal);
    }

    public void journalToXml(Journal journal, String file) {
        writer.saveJournal(journal, file);
    }

    public Journal xmlToJournal(String file) {
        return journal = reader.loadJournal(file);
    }

    public void deleteTask(Task task) {
        journal.deleteTask(task);
        journalToXml(journal, XML_FILE_NAME);
    }

    public void addNewTask(String name, String description, Date date, String contacts) {
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
    public void listUpdate() {
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
                confirmWindow.setVisible(true);
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
                addTaskWindow.setVisible(true);
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
                    journal.deleteAllTask(journal);
                }
                deleteAllTaskButton.setEnabled(false);
                listUpdate();
            }
        });

        GroupLayout taskListPanelLayout = new GroupLayout(taskListPanel);
        taskListPanel.setLayout(taskListPanelLayout);
        taskListPanelLayout.setHorizontalGroup(
                taskListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(taskListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(taskListLabel)
                                                .addGap(212, 212, 212)
                                                .addComponent(descriptionLabel)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                                .addComponent(taskListScrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(taskListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(contactsLabel)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(contactsScrollPane)
                                                        .addComponent(descriptionScrollPane)))))
        );
        taskListPanelLayout.setVerticalGroup(
                taskListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(taskListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(taskListLabel)
                                        .addComponent(descriptionLabel))
                                .addGap(7, 7, 7)
                                .addGroup(taskListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(taskListScrollPane)
                                        .addGroup(taskListPanelLayout.createSequentialGroup()
                                                .addComponent(descriptionScrollPane, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(contactsLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(contactsScrollPane, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(addTaskButton, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(deleteTaskButton, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                                                .addComponent(deleteAllTaskButton, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(taskListPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(addTaskButton).addComponent(deleteTaskButton))
                                        .addComponent(deleteAllTaskButton, GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(taskListPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
    }
    class ConfirmWindow extends JDialog {
        private JPanel Panel = new JPanel();
        private JButton closeButton = new JButton();
        private JButton hideButton = new JButton();
        private JLabel selectLabel = new JLabel();
        private GroupLayout layout = new GroupLayout(getContentPane());
        private GroupLayout PanelLayout = new GroupLayout(Panel);
        private MainWindow mainWindow;

        public ConfirmWindow(MainWindow mainWindow) {
            initComponents();
            this.mainWindow = mainWindow;
        }

        private void initComponents() {
            selectLabel.setText("Закрыть менеджер задач?");
            hideButton.setText("Свернуть");
            closeButton.setText("Закрыть");
            setTitle("Подтверждение");
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            setBounds(dimension.width / 2 - 227, dimension.height / 2 - 165, 464, 339);

            hideButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    mainWindow.setVisible(false);
                    systemTrayWindow.hide();
                    setVisible(false);
                }
            });

            closeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    mainWindow.dispose();
                    System.exit(0);
                }
            });

            Panel.setLayout(PanelLayout);
            PanelLayout.setHorizontalGroup(
                    PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(PanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                    .addComponent(hideButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            .addGroup(GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(selectLabel, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40))
            );
            PanelLayout.setVerticalGroup(
                    PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(PanelLayout.createSequentialGroup()
                                    .addContainerGap(12, Short.MAX_VALUE)
                                    .addComponent(selectLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(hideButton)
                                            .addComponent(closeButton))
                                    .addGap(15, 15, 15))
            );

            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(Panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(Panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }
    }
}