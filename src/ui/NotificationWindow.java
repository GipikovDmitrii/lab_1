package ui;

import javax.swing.*;

/**
 *
 * @author Dmitrii
 */

public class NotificationWindow extends JDialog{
    private JPanel Panel = new JPanel();
    private JButton completeTask = new JButton();
    private JScrollPane contactsScrollPane = new JScrollPane();
    private JEditorPane contactsTask = new JEditorPane();
    private JScrollPane descriptionScrollPane = new JScrollPane();
    private JEditorPane descriptionTask = new JEditorPane();
    private JTextField nameTask = new JTextField();
    private GroupLayout PanelLayout = new GroupLayout(Panel);
    private GroupLayout layout = new GroupLayout(getContentPane());

    public NotificationWindow() {
        initComponents();
    }

    public void initComponents() {
        descriptionScrollPane.setViewportView(descriptionTask);
        completeTask.setText("Завершить задачу");
        contactsScrollPane.setViewportView(contactsTask);
        Panel.setLayout(PanelLayout);

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