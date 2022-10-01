package ui;

import javax.swing.*;
import java.awt.*;

public class HelpWindow extends JDialog {
    JDialog helpWindow;

    public HelpWindow() {
        helpWindow = new JDialog();
        JTextArea jTextArea = new JTextArea();
        jTextArea.setText("Confused? Refer to the below information: \n\n\n" +
                "To enter words in Malayalam, type your input in the in Input pane or go to 'File -> Open' to enter input as a text file.\n\n" +
                "To view the romanized output in the output pane, click on the 'Romanize Input' button.\n\n" +
                "To save the output as a text file using 'File -> Save as Text' in the menu bar.\n\n" +
                "To clear the input pane, click on the 'Clear' button.\n\n" +
                "To view the various keyboard shortcuts, click on the shortcuts tab.\n\n");
        jTextArea.setEditable(false);
        jTextArea.setFont(new Font("Verdana", Font.PLAIN,15));

        helpWindow.setTitle("README");
        helpWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        helpWindow.setMinimumSize(new Dimension(400, 400));
        helpWindow.add(jTextArea);
        helpWindow.pack();
        helpWindow.setMinimumSize(new Dimension(helpWindow.getWidth(), helpWindow.getHeight()));
        helpWindow.setVisible(true);
    }
}
