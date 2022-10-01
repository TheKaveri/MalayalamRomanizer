package ui;

import model.MalayalamRomanizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MalayalamRomanizerUI extends JFrame implements ActionListener {
    private MyMenuBar myMenuBar;
    private JTabbedPane tabMenu;
    private JTextArea inputTextArea, outputTextArea;
    private JButton submitButton;


    private MalayalamRomanizerUI() {
        this.setTitle("Welcome To The Malayalam Romanizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 600);

        myMenuBar = new MyMenuBar();
        myMenuBar.openItem.addActionListener(this);
        myMenuBar.saveItem.addActionListener(this);
        myMenuBar.exitItem.addActionListener(e -> System.exit(0));
        myMenuBar.readMeItem.addActionListener(this);


        tabMenu = new JTabbedPane();
        tabMenu.setTabPlacement(JTabbedPane.BOTTOM);

        this.addTabs();

        this.setJMenuBar(myMenuBar);
        this.add(tabMenu);
        this.setVisible(true);

    }

    private void addTabs() {
        JPanel textTab = new JPanel();
        textTab.setSize(new Dimension(600, 600));

        {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(600, 10));
            textTab.add(label);
        }

        inputTextArea = new JTextArea("Input Pane...\nഇവിടെ മലയാളത്തിൽ ചില വാക്കുകൾ ഇടുക...", 13, 50);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);

        submitButton = new JButton();
        submitButton.setText("Romanize Input");
        submitButton.addActionListener(this);

        outputTextArea = new JTextArea("Output Pane...\nഇവിടെ ലിപിമാറ്റം ചെയ്തിരിക്കും...", 13, 50);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputTextArea.setEditable(false);

        textTab.add(inputScrollPane);
        textTab.add(submitButton);
        textTab.add(outputScrollPane);
        tabMenu.add(textTab, 0);
        tabMenu.setTitleAt(0, "Text");


        JPanel shortcutTab = new JPanel();
        shortcutTab.setPreferredSize(new Dimension(600, 600));
        JTextArea shortcutTextArea = new JTextArea();
        shortcutTextArea.setMaximumSize(new Dimension(600, 600));
        shortcutTextArea.setFont(new Font("Verdana", Font.PLAIN, 20));
        shortcutTextArea.setText(
                "FileMenu: ⌃ + ⌥ + F or Alt + H\n\n" +
                        "HelpItem: ⌃ + ⌥ + H or Alt + H\n\n" +
                        "OpenItem: O (Works Only After Opening FileMenu)\n\n" +
                        "SaveItem: S (Works Only After Opening FileMenu)\n\n" +
                        "ExitItem: E (Works Only After Opening FileMenu)\n\n" +
                        "ReadMeItem: R (Works Only After Opening HelpMenu)"
        );
        shortcutTextArea.setFocusable(false);
        shortcutTab.add(shortcutTextArea);
        tabMenu.add(shortcutTab, 1);
        tabMenu.setTitleAt(1, "Shortcuts");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == myMenuBar.openItem) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("MalayalamRomanizer/src/model"));

            int response = fileChooser.showOpenDialog(null); // select file to open

            if (response == JFileChooser.APPROVE_OPTION) {
                inputTextArea.setText("");
                outputTextArea.setText("");
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    Scanner input = new Scanner(file);
                    StringBuilder fileText = new StringBuilder();
                    while (input.hasNextLine()) {
                        fileText.append(input.nextLine()).append('\n');
                    }
                    input.close();
                    inputTextArea.setText(fileText.toString());
                    submitButton.setText("Romanize Input");

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        if (e.getSource() == myMenuBar.saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Create a new file or use an existing txt file");

            fileChooser.setCurrentDirectory(new File("MalayalamRomanizer/src/usrfiles"));


            int response = fileChooser.showSaveDialog(null); // select file to open

            if (response == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    PrintWriter out = new PrintWriter(file);
                    out.println(outputTextArea.getText());
                    out.close();

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        if (e.getSource() == submitButton) {
            if (submitButton.getText().equals("Romanize Input")) {
                try {
                    outputTextArea.setText(MalayalamRomanizer.romanizer(inputTextArea.getText()));
                    submitButton.setText("Clear");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (submitButton.getText().equals("Clear")) {
                inputTextArea.setText("");
                outputTextArea.setText("");
            }
        }

        if (e.getSource() == myMenuBar.readMeItem) {
            new HelpWindow();
        }
    }

    public static void main(String[] args) {
        new MalayalamRomanizerUI();
    }
}
