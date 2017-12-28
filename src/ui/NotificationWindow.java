package ui;

import journal.Journal;
import task.Task;
import reader.Reader;
import writer.Writer;

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
    private static final String PATH_ICON = "src/image/icon.png";
    private static final String XML_FILE_NAME = "journal.xml";
    private JPanel Panel = new JPanel();
    private JButton completeTask = new JButton();
    private JScrollPane contactsScrollPane = new JScrollPane();
    private JEditorPane contactsTask = new JEditorPane();
    private JScrollPane descriptionScrollPane = new JScrollPane();
    private JEditorPane descriptionTask = new JEditorPane();
    private JTextField nameTask = new JTextField();
    private GroupLayout PanelLayout = new GroupLayout(Panel);
    private GroupLayout layout = new GroupLayout(getContentPane());
    private Timer timer = new Timer();
    private Journal journal = new Journal();
    private Reader reader = new Reader();
    private Writer writer = new Writer();

    public NotificationWindow() {
        initComponents();
    }

    public void addNotification(Task task) {
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
        completeTask.setText("Завершить задачу");
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

        completeTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                journal = reader.loadJournal(XML_FILE_NAME);

            }
        });

        PanelLayout.setHorizontalGroup(
                PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(contactsScrollPane)
                                        .addComponent(descriptionScrollPane)
                                        .addComponent(nameTask)
                                        .addComponent(completeTask, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
                                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
                PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nameTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contactsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(completeTask)
                                .addContainerGap())
        );
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}