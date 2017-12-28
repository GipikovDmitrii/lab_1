package ui;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Dmitrii
 */

public class SystemTrayWindow extends JDialog {
    private static final String PATH_ICON = "src/image/icon.png";
    private PopupMenu trayPopupMenu = new PopupMenu();
    private MenuItem open = new MenuItem("Развернуть");
    private MenuItem close = new MenuItem("Закрыть");
    private MainWindow mainWindow;

    public SystemTrayWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void hide() {
        Image image = Toolkit.getDefaultToolkit().getImage(PATH_ICON);
        TrayIcon trayIcon = new TrayIcon(image, "Менеджер задач", trayPopupMenu);
        SystemTray systemTray = SystemTray.getSystemTray();

        trayPopupMenu.add(open);
        trayPopupMenu.add(close);

        open.addActionListener(e -> {
            mainWindow.setVisible(true);
            mainWindow.listUpdate();
        });

        close.addActionListener(e -> System.exit(0));

        trayIcon.setImageAutoSize(true);

        try {
            systemTray.add(trayIcon);
        } catch(AWTException awtException){
            awtException.printStackTrace();
        }
    }
}