package notification;

import ui.NotificationWindow;
import task.Task;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Notification extends TimerTask {
    private Timer timer = new Timer();
    private Task task;
    NotificationWindow window = new NotificationWindow();

    public Notification(Task task) {
        this.task = task;
    }



    public void addNotification() {
        long delay = (new Date().getTime() - task.getTime().getTime()) / 1000;
       // timer.schedule(, delay);
    }

    public void updateNotification() {

    }

    @Override
    public void run() {
        addNotification();
    }



    /*public void createNotification(int index) {
        long delay = ((new Date().getTime() - journal.getTask(index).getTime().getTime())) / 1000;
        pool.schedule(super.setVisible(true), delay, TimeUnit.SECONDS);
    }

    public void updateNotification() {
        for (int i = 0; i < journal.getSize(); i++) {
            long delay = ((new Date().getTime() - journal.getTask(i).getTime().getTime())) / 1000;
            pool.schedule(SwingUtilities.invokeLater(() -> createAndShowGUI()), delay, TimeUnit.SECONDS);
        }
    }*/
}
