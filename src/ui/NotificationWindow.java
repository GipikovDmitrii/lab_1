package ui;

import journal.Journal;
import task.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Dmitrii
 */

public class NotificationWindow extends JDialog {
    private static final String PATH_ICON = "src/images/icon.png";
    private static final String XML_FILE_NAME = "journal.xml";
    private JEditorPane contactsTask = new JEditorPane();
    private JEditorPane descriptionTask = new JEditorPane();
    private JTextField nameTask = new JTextField();
    private JPanel Panel = new JPanel();
    private JButton completeTaskButton = new JButton();
    private JScrollPane contactsScrollPane = new JScrollPane();
    private JScrollPane descriptionScrollPane = new JScrollPane();
    private GroupLayout PanelLayout = new GroupLayout(Panel);
    private GroupLayout layout = new GroupLayout(getContentPane());
    private MainWindow mainWindow;
    private Timer timer = new Timer();
    private Task task;

    public NotificationWindow(MainWindow mainWindow, Task task) {
        initComponents();
        this.task = task;
        this.mainWindow = mainWindow;
    }

    public void addNotification() {
        long delay = (task.getTime().getTime() - new Date().getTime());
        if (delay > 0) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    nameTask.setText(task.getName());
                    descriptionTask.setText(task.getDescription());
                    contactsTask.setText(task.getContacts());
                    initComponents();
                    setVisible(true);
                }
            }, delay);
        }
    }

    public void initComponents() {
        descriptionScrollPane.setViewportView(descriptionTask);
        completeTaskButton.setText("Завершить задачу");
        contactsScrollPane.setViewportView(contactsTask);
        Panel.setLayout(PanelLayout);
        descriptionTask.setEditable(false);
        nameTask.setEditable(false);
        contactsTask.setEditable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Image image = Toolkit.getDefaultToolkit().getImage(PATH_ICON);
        setIconImage(image);
        setTitle("Напоминание");
        setBounds(dimension.width / 2 - 227, dimension.height / 2 - 165, 464, 339);
        setResizable(false);

        completeTaskButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Journal oldJournal = mainWindow.journal;
                oldJournal.deleteTask(task);
                Journal newJournal = mainWindow.journal;
                mainWindow.journalToXml(newJournal, XML_FILE_NAME);
                mainWindow.listUpdate();
                setVisible(false);
                if (mainWindow.journal.getSize() == 0) {
                    mainWindow.deleteAllTaskButton.setEnabled(false);
                }
            }
        });

        PanelLayout.setHorizontalGroup(
                PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(PanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(contactsScrollPane)
                                        .addComponent(descriptionScrollPane)
                                        .addComponent(nameTask)
                                        .addComponent(completeTaskButton, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
                                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
                PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nameTask, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionScrollPane, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contactsScrollPane, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(completeTaskButton)
                                .addContainerGap())
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