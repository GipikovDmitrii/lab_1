/*package TaskManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Интерфейс пользователя
 *
 * @author Дмитрий
 *

public class UserInterface extends JFrame {
    private Task task;

    public UserInterface() throws IOException {
        initComponents();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        Task oldTask = this.task;
        this.task = task;
        firePropertyChange("task", oldTask, task);
    }






    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *

    private void initComponents() throws IOException {
        Image image = Toolkit.getDefaultToolkit().getImage("src/images/label.png");
        setIconImage(image);
        setTitle("Планировщик задач");
        viewTaskPanel = new javax.swing.JPanel();
        contactsScrollPane = new javax.swing.JScrollPane();
        contactsEditorPane = new javax.swing.JEditorPane();
        contactsLabel = new javax.swing.JLabel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionEditorPane = new javax.swing.JEditorPane();
        descriptionLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        dateTextField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        taskListPanel = new javax.swing.JPanel();
        taskListScrollPane = new javax.swing.JScrollPane();
        taskList = new javax.swing.JList<>();
        addTaskButton = new javax.swing.JButton();
        deleteAllTaskButton = new javax.swing.JButton();
        taskListLabel = new javax.swing.JLabel();
        deleteTaskButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contactsScrollPane.setViewportView(contactsEditorPane);

        contactsLabel.setText("Контакты:");

        descriptionScrollPane.setViewportView(descriptionEditorPane);

        descriptionLabel.setText("Описание:");

        nameLabel.setText("Название:");

        dateLabel.setText("Дата:");

        javax.swing.GroupLayout viewTaskPanelLayout = new javax.swing.GroupLayout(viewTaskPanel);
        viewTaskPanel.setLayout(viewTaskPanelLayout);
        viewTaskPanelLayout.setHorizontalGroup(
                viewTaskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(contactsScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(descriptionScrollPane)
                        .addComponent(nameTextField)
                        .addComponent(dateTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(viewTaskPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(viewTaskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(contactsLabel)
                                        .addComponent(descriptionLabel)
                                        .addComponent(nameLabel)
                                        .addComponent(dateLabel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewTaskPanelLayout.setVerticalGroup(
                viewTaskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewTaskPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(dateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descriptionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(contactsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contactsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        taskList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Задача" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        taskListScrollPane.setViewportView(taskList);

        javax.swing.GroupLayout taskListPanelLayout = new javax.swing.GroupLayout(taskListPanel);
        taskListPanel.setLayout(taskListPanelLayout);
        taskListPanelLayout.setHorizontalGroup(
                taskListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(taskListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        );
        taskListPanelLayout.setVerticalGroup(
                taskListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskListPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(taskListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        addTaskButton.setText("Добавить задачу");

        deleteAllTaskButton.setText("Удалить все задачи");

        taskListLabel.setText("Список задач:");

        deleteTaskButton.setText("Удалить задачу");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(addTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(deleteTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                                                .addComponent(deleteAllTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(taskListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(taskListLabel)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(viewTaskPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(16, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(addTaskButton)
                                                .addComponent(deleteTaskButton))
                                        .addComponent(deleteAllTaskButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(taskListLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(taskListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(viewTaskPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     *
     *
     *
    public static void main(String args[]) {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        /* Create and display the form *
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new UserInterface().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private javax.swing.JButton addTaskButton;
    private javax.swing.JEditorPane contactsEditorPane;
    private javax.swing.JLabel contactsLabel;
    private javax.swing.JScrollPane contactsScrollPane;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton deleteAllTaskButton;
    private javax.swing.JButton deleteTaskButton;
    private javax.swing.JEditorPane descriptionEditorPane;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JList<String> taskList;
    private javax.swing.JLabel taskListLabel;
    private javax.swing.JPanel taskListPanel;
    private javax.swing.JScrollPane taskListScrollPane;
    private javax.swing.JPanel viewTaskPanel;
}
*/