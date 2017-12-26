package ui;

import java.awt.*;

/**
 *
 * @author Dmitrii
 */

public class SystemTrayWindow {
    private static final String pathIcon = "src/image/icon.png";
    private PopupMenu trayPopupMenu = new PopupMenu();
    private MenuItem open = new MenuItem("Развернуть");
    private MenuItem close = new MenuItem("Закрыть");

    public SystemTrayWindow() {
    }

    public void hide() {
        Image image = Toolkit.getDefaultToolkit().getImage(pathIcon);
        TrayIcon trayIcon = new TrayIcon(image, "Менеджер задач", trayPopupMenu);
        SystemTray systemTray = SystemTray.getSystemTray();
        //Toolkit toolkit = Toolkit.getDefaultToolkit();
        //Dimension dimension = toolkit.getScreenSize();
        //setBounds(dimension.width / 2 - 143, dimension.height / 2 - 48, 286, 96);

        trayPopupMenu.add(open);
        trayPopupMenu.add(close);

        open.addActionListener(e -> {
            new MainWindow().setVisible(true);
            MainWindow.listUpdate();
        });

        close.addActionListener(e -> {
            System.exit(0);
        });

        trayIcon.setImageAutoSize(true);

        try {
            systemTray.add(trayIcon);
        } catch(AWTException awtException){
            awtException.printStackTrace();
        }
    }
}