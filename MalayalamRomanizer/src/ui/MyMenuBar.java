package ui;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    protected JMenu fileMenu, helpMenu;
    protected JMenuItem openItem, saveItem, exitItem, readMeItem;

    protected MyMenuBar() {
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save Output As Text");
        exitItem = new JMenuItem("Exit");

        readMeItem = new JMenuItem("README");

        fileMenu.setMnemonic(KeyEvent.VK_F);// shortcut alt + F for fileMenu
        helpMenu.setMnemonic(KeyEvent.VK_H);// shortcut alt + H for helpItem
        openItem.setMnemonic(KeyEvent.VK_O); // shortcut O for fileItem
        saveItem.setMnemonic(KeyEvent.VK_S); // shortcut S for saveItem
        exitItem.setMnemonic(KeyEvent.VK_E); // shortcut E for exitItem
        readMeItem.setMnemonic(KeyEvent.VK_R); // shortcut E for readMeItem

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        helpMenu.add(readMeItem);

        this.add(fileMenu);
        this.add(helpMenu);
    }
}
