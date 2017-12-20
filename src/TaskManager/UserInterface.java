package TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface extends JFrame {
    Journal journal = new Journal();

    private JButton addTaskButton;
    private JEditorPane contactsEditorPane;
    private JLabel contactsLabel;
    private JScrollPane contactsScrollPane;
    private JButton deleteAllTaskButton;
    private JButton deleteTaskButton;
    private JEditorPane descriptionEditorPane;
    private JLabel descriptionLabel;
    private JScrollPane descriptionScrollPane;
    private JList<Task> taskList;
    private JLabel taskListLabel;
    private JPanel taskListPanel;
    private JScrollPane taskListScrollPane;
    private DefaultListModel model = new DefaultListModel();

    private static final String titleName = "Планировщик задач";
    private static final String addTask = "Добавить задачу";
    private static final String deleteTask = "Удалить задачу";
    private static final String deleteAllTask = "Удалить все задачи";
    private static final String pathIcon = "src/images/icon.png";
    private static final String xmlFileName = "journal.xml";

    public UserInterface() {
        createAndShowGUI();
    }

    public Journal xmlToJournal(Journal journal) {
        return this.journal = journal.xmlToObject(xmlFileName);
    }

    public void journalToXml(Journal journal) {
        this.journal.objectToXml(journal, xmlFileName);
    }

    //Добавление новой задачи
    public void addNewTask(String name, String description, Date date, String contacts) {
        Task task = new Task(name, description, date, contacts);
        journal.xmlToObject(xmlFileName);
        journal.addTask(task);
        journalToXml(journal);
    }

    //Обновление списка задач
    public void listUpdate() {
        model.clear();
        for (int i = 0; i < journal.getSize(); i++) {
            Task task = journal.getTask(i);
            model.addElement(task);
        }
        taskList.setModel(model);
    }

    private void createAndShowGUI() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Image image = Toolkit.getDefaultToolkit().getImage(pathIcon);
        setBounds(dimension.width/2 - 450, dimension.height/2 - 300, 900, 600);
        setTitle(titleName);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(image);
        taskListPanel = new JPanel();
        taskListScrollPane = new JScrollPane();
        taskList = new JList<>();
        taskListLabel = new JLabel();
        contactsScrollPane = new JScrollPane();
        contactsEditorPane = new JEditorPane();
        contactsLabel = new JLabel();
        descriptionScrollPane = new JScrollPane();
        descriptionEditorPane = new JEditorPane();
        descriptionLabel = new JLabel();
        addTaskButton = new JButton();
        deleteAllTaskButton = new JButton();
        deleteTaskButton = new JButton();

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

        taskListScrollPane.setViewportView(taskList);

        taskListLabel.setText("Список задач:");

        contactsScrollPane.setViewportView(contactsEditorPane);

        contactsLabel.setText("Контакты:");

        descriptionScrollPane.setViewportView(descriptionEditorPane);

        descriptionLabel.setText("Описание:");

        javax.swing.GroupLayout taskListPanelLayout = new javax.swing.GroupLayout(taskListPanel);
        taskListPanel.setLayout(taskListPanelLayout);
        taskListPanelLayout.setHorizontalGroup(
                taskListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                .addComponent(contactsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        addTaskButton.setText(addTask);
        addTaskButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                new AddTaskWindow().setVisible(true);
            }
        });

        deleteTaskButton.setText(deleteTask);
        deleteTaskButton.setEnabled(false);
        deleteTaskButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            Task task = journal.getTask(index);
            journal.deleteTask(task);
            model.removeElementAt(index);
            if (journal.getSize() == 0) {
                deleteAllTaskButton.setEnabled(false);
            }
        });

        deleteAllTaskButton.setText(deleteAllTask);
        deleteAllTaskButton.setEnabled(false);
        deleteAllTaskButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (journal.getSize() != 0) {
                    journal = new Journal();
                    model.clear();
                    taskList.setModel(model);
                    listUpdate();
                    deleteAllTaskButton.setEnabled(false);
                }
            }
        });

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

    class AddTaskWindow extends JDialog {
        private JScrollPane contactsScrollPane;
        private JScrollPane descriptionScrollPane;
        private JEditorPane enterContactsField;
        private JLabel enterContactsLabel;
        private JTextField enterDateField;
        private JLabel enterDateLabel;
        private JEditorPane enterDescriptionField;
        private JLabel enterDescriptionLabel;
        private JTextField enterNameField;
        private JLabel enterNameLabel;
        private JPanel jPanel;
        private JButton saveButton;
        private JLabel validationLabel;
        private JLabel dateLabel;
        private JLabel timeLabel;
        private JTextField enterTimeField;
        private final String titleName = "Создание задачи";
        private final String saveButtonText = "Сохранить задачу";
        private final Pattern DATE = Pattern.compile("((0|1)?[0-9]|2?[0-3]:[0-5][0-9])");

        AddTaskWindow() {
            initComponents();
        }

        private void initComponents() {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            setBounds(dimension.width / 2 - 300, dimension.height / 2 - 280, 600, 560);
            Image image = Toolkit.getDefaultToolkit().getImage(pathIcon);
            setIconImage(image);
            setTitle(titleName);
            jPanel = new JPanel();
            enterDateField = new JTextField();
            enterDateLabel = new JLabel();
            enterNameLabel = new JLabel();
            enterNameField = new JTextField();
            descriptionScrollPane = new JScrollPane();
            enterDescriptionField = new JEditorPane();
            enterDescriptionLabel = new JLabel();
            enterContactsLabel = new JLabel();
            contactsScrollPane = new JScrollPane();
            enterContactsField = new JEditorPane();
            saveButton = new JButton();
            validationLabel = new JLabel();
            dateLabel = new JLabel();
            timeLabel = new JLabel();
            enterTimeField = new JTextField();

            enterDateLabel.setText("Введите дату и время в указанном формате:");

            enterNameLabel.setText("Введите название задачи:");

            descriptionScrollPane.setViewportView(enterDescriptionField);

            enterDescriptionLabel.setText("Введите описание задачи:");

            enterContactsLabel.setText("Введите контакты:");

            contactsScrollPane.setViewportView(enterContactsField);

            dateLabel.setText("Дата: dd.mm.yyyy");
            timeLabel.setText("Время: hh:mm");

            validationLabel.setText(" ");
            validationLabel.setFont(new Font("Dialog", 1, 11));
            validationLabel.setForeground(Color.RED);
            saveButton.setText(saveButtonText);
            saveButton.addActionListener(evt -> {
                String textInDateField = enterDateField.getText();
                String textInNameField = enterNameField.getText();
                String textInTimeField = enterTimeField.getText();
                Matcher matcher = DATE.matcher(textInTimeField);
                DateValidator dv = new DateValidator();

                if (Objects.equals(textInDateField, "") && Objects.equals(textInTimeField, "")) {
                    validationLabel.setText("Введите дату и время задачи!");
                } else if (Objects.equals(textInDateField, "")) {
                    validationLabel.setText("Введите дату!");
                } else if (!(dv.validate(textInDateField))) {
                    validationLabel.setText("Введите корректную дату в указанном формате! Например: 18.03.2018");
                } else if (Objects.equals(textInTimeField, "")) {
                    validationLabel.setText("Введите время!");
                } else if (!(matcher.matches())) {
                    validationLabel.setText("Введите корректное время в указанном формате! Например: 20:18");
                } else if (Objects.equals(textInNameField, "")) {
                    validationLabel.setText("Введите название задачи!");
                } else {
                    String name = enterNameField.getText();
                    String description = enterDescriptionField.getText();
                    String dateString = enterDateField.getText().concat(" ").concat(enterTimeField.getText());
                    String contacts = enterContactsField.getText();
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    Date date = null;
                    try {
                        date = format.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    addNewTask(name, description, date, contacts);
                    deleteAllTaskButton.setEnabled(true);
                    listUpdate();
                    dispose();
                }
            });

            javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
            jPanel.setLayout(jPanelLayout);
            jPanelLayout.setHorizontalGroup(
                    jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelLayout.createSequentialGroup()
                                                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(enterDateLabel)
                                                            .addGroup(jPanelLayout.createSequentialGroup()
                                                                    .addComponent(dateLabel)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(enterDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(16, 16, 16)
                                                                    .addComponent(timeLabel)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(enterTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGap(0, 171, Short.MAX_VALUE))
                                            .addGroup(jPanelLayout.createSequentialGroup()
                                                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(enterDescriptionLabel)
                                                            .addComponent(enterContactsLabel)
                                                            .addGroup(jPanelLayout.createSequentialGroup()
                                                                    .addComponent(enterNameLabel)
                                                                    .addGap(59, 59, 59)))
                                                    .addGap(144, 144, 144))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(enterNameField)
                                                            .addComponent(descriptionScrollPane)
                                                            .addComponent(contactsScrollPane)
                                                            .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(validationLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addContainerGap())))
            );
            jPanelLayout.setVerticalGroup(
                    jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(enterDateLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(enterDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dateLabel)
                                            .addComponent(timeLabel)
                                            .addComponent(enterTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(enterNameLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(enterNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(enterDescriptionLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(enterContactsLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(contactsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(validationLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(saveButton)
                                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }
    }

    public class DateValidator {
        private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((20)\\d\\d)";
        private final Pattern pattern;
        private Matcher matcher;

        public DateValidator() {
            pattern = Pattern.compile(DATE_PATTERN);
        }

        public boolean validate(String date) {
            matcher = pattern.matcher(date);
            if (matcher.matches()) {
                matcher.reset();
                if (matcher.find()) {
                    String day = matcher.group(1);
                    String month = matcher.group(2);
                    int year = Integer.parseInt(matcher.group(3));
                    if ("31".equals(day)) {
                        return Arrays.asList(new String[] {"1", "01", "3", "03", "5", "05", "7", "07", "8", "08", "10", "12"}).contains(month);
                    } else if ("2".equals(month) || "02".equals(month)) {
                        if (year % 4 == 0) {
                            if (year % 100 == 0) {
                                if (year % 400 == 0) {
                                    return Integer.parseInt(day) <= 29;
                                }
                                return Integer.parseInt(day) <= 28;
                            }
                            return Integer.parseInt(day) <= 29;
                        } else {
                            return Integer.parseInt(day) <= 28;
                        }
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(() -> new UserInterface().setVisible(true));
    }
}