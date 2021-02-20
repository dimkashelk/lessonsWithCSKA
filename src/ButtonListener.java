import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    public static String NEW_ACTION = "New";
    public static String OPEN_ACTION = "Open";
    public static String SAVE_ACTION = "Save";
    public static String SAVE_AS_ACTION = "Save As";

    TextEditor wnd;
    public String operation;

    public ButtonListener(TextEditor textEditor, String newAction) {
        wnd = textEditor;
        operation = newAction;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (operation.equals(NEW_ACTION)) {
            wnd.newActionEvent();
        } else if (operation.equals(OPEN_ACTION)) {
            wnd.openActionEvent();
        } else if (operation.equals(SAVE_ACTION)) {
            wnd.saveActionEvent();
        } else if (operation.equals(SAVE_AS_ACTION)) {
            wnd.saveAsActionEvent();
        }
    }
}
