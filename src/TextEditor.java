import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor extends JFrame {

    private String filename = "";

    private JTextArea textArea;

    public TextEditor() {
        this.setTitle("Text Editor by PrIdYrKi");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem btnNew = new JMenuItem("New");
        btnNew.addActionListener(new ButtonListener(this, ButtonListener.NEW_ACTION));
        fileMenu.add(btnNew);
        JMenuItem btnOpen = new JMenuItem("Open");
        btnOpen.addActionListener(new ButtonListener(this, ButtonListener.OPEN_ACTION));
        fileMenu.add(btnOpen);
        JMenuItem btnSave = new JMenuItem("Save");
        btnSave.addActionListener(new ButtonListener(this, ButtonListener.SAVE_ACTION));
        fileMenu.add(btnSave);
        JMenuItem btnSaveAs = new JMenuItem("Save As");
        btnSaveAs.addActionListener(new ButtonListener(this, ButtonListener.SAVE_AS_ACTION));
        fileMenu.add(btnSaveAs);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        this.add(scrollPane);
    }

    public static void main(String[] args) {
        TextEditor wnd = new TextEditor();
        wnd.setVisible(true);
    }

    public void newActionEvent() {
        if (filename.equals("")) {
            if (!textArea.getText().equals("")) {
                this.saveAsActionEvent();
            }
        } else {
            if (!textArea.getText().equals("")) {
                File file = new File(filename);
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(textArea.getText());
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                filename = "";
            }
        }
    }

    public void openActionEvent() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        if (fileChooser.getSelectedFile() != null) {
            File file = new File(String.valueOf(fileChooser.getSelectedFile()));
            try {
                FileReader fileReader = new FileReader(file);
                textArea.setText("");
                int i = fileReader.read();
                while (i != -1) {
                    textArea.setText(textArea.getText() + (char) i);
                    i = fileReader.read();
                }
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            filename = file.getAbsolutePath();
            this.setTitle(file.getName());
        }
    }

    public void saveActionEvent() {
        if (filename.equals("")) {
            this.saveAsActionEvent();
            return;
        }
        File file = new File(filename);
        try {

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(textArea.getText());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAsActionEvent() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        if (fileChooser.getSelectedFile() != null) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(textArea.getText());
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            filename = fileChooser.getSelectedFile().getAbsolutePath();
            this.setTitle(file.getName());
        }
    }
}
