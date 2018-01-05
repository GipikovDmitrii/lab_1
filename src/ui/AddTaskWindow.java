package ui;

import util.DateValidator;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dmitrii
 */

public class AddTaskWindow extends JDialog {
    private JScrollPane contactsScrollPane = new JScrollPane();
    private JScrollPane descriptionScrollPane = new JScrollPane();
    private JEditorPane enterContactsField = new JEditorPane();
    private JLabel enterContactsLabel = new JLabel();
    private JTextField enterDateField = new JTextField();
    private JLabel enterDateLabel = new JLabel();
    private JEditorPane enterDescriptionField = new JEditorPane();
    private JLabel enterDescriptionLabel = new JLabel();
    private JTextField enterNameField = new JTextField();
    private JLabel enterNameLabel = new JLabel();
    private JPanel jPanel = new JPanel();
    private JButton saveButton = new JButton();
    private JLabel validationLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    private JLabel timeLabel = new JLabel();
    private JTextField enterTimeField = new JTextField();
    private final String titleName = "Создание задачи";
    private final String saveButtonText = "Сохранить задачу";
    private static final Pattern VALIDATION_TIME = Pattern.compile("^([0-1]\\d|2[0-3])(:[0-5]\\d)");
    public static final String PATH_ICON = "src/images/icon.png";
    private MainWindow mainWindow;

    public AddTaskWindow(MainWindow mainWindow) {
        initComponents();
        this.mainWindow = mainWindow;
    }

    private void initComponents() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - 300, dimension.height / 2 - 280, 600, 560);
        Image image = Toolkit.getDefaultToolkit().getImage(PATH_ICON);
        setIconImage(image);
        setTitle(titleName);
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
        setResizable(false);

        saveButton.addActionListener(evt -> {
            String textInDateField = enterDateField.getText();
            String textInNameField = enterNameField.getText();
            String textInTimeField = enterTimeField.getText();
            Matcher matcher = VALIDATION_TIME.matcher(textInTimeField);
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
                mainWindow.addNewTask(name, description, date, contacts);
                mainWindow.deleteAllTaskButton.setEnabled(true);
                mainWindow.listUpdate();
                dispose();
            }
        });
        GroupLayout jPanelLayout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(enterDateLabel)
                                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                                .addComponent(dateLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(enterDateField, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(16, 16, 16)
                                                                .addComponent(timeLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(enterTimeField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 171, Short.MAX_VALUE))
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(enterDescriptionLabel)
                                                        .addComponent(enterContactsLabel)
                                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                                .addComponent(enterNameLabel)
                                                                .addGap(59, 59, 59)))
                                                .addGap(144, 144, 144))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(enterNameField)
                                                        .addComponent(descriptionScrollPane)
                                                        .addComponent(contactsScrollPane)
                                                        .addComponent(saveButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(validationLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())))
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(enterDateLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(enterDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateLabel)
                                        .addComponent(timeLabel)
                                        .addComponent(enterTimeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enterNameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enterNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(enterDescriptionLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionScrollPane, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(enterContactsLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contactsScrollPane, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(validationLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveButton)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}