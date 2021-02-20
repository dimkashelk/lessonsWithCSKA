import javax.swing.*;

public class TextEditor extends JFrame {
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

        fileMenu.add(btnOpen);
        JMenuItem btnSave = new JMenuItem("Save");

        fileMenu.add(btnSave);
        JMenuItem btnSaveAs = new JMenuItem("Save As");

        fileMenu.add(btnSaveAs);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        this.add(scrollPane);
    }
    public static void main(String[] args) {
        TextEditor wnd = new TextEditor();

        wnd.setVisible(true);
    }

    public void newActionEvent() {
    }

    public void openActionEvent() {
    }

    public void saveActionEvent() {
    }

    public void saveAsActionEvent() {
    }
}
