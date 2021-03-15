import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    public static final String LINE = "line";
    public static final String RECTANGLE = "rectangle";
    public static final String CIRCLE = "circle";
    public static final String PENCIL = "pencil";

    public static final String STEP_BACK = "step_back";
    public static final String STEP_FORWARD = "step_forward";
    public static final String CLEAN = "clean";

    public static final String WHITE = "white";
    public static final String BLACK = "black";
    public static final String RED = "red";
    public static final String BLUE = "blue";
    public static final String GREEN = "green";
    public static final String YELLOW = "yellow";
    public static final String COLOR_CHOOSER = "color_chooser";

    private Paint wnd;
    private String action;

    public ButtonListener(Paint wnd, String action) {
        this.wnd = wnd;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (action.equals(ButtonListener.RECTANGLE)) {
            wnd.setMode(ButtonListener.RECTANGLE);
        }
        if (action.equals(ButtonListener.LINE)) {
            wnd.setMode(ButtonListener.LINE);
        }
        if (action.equals(ButtonListener.CIRCLE)) {
            wnd.setMode(ButtonListener.CIRCLE);
        }
        if (action.equals(ButtonListener.PENCIL)) {
            wnd.setMode(ButtonListener.PENCIL);
        }
        if (action.equals(ButtonListener.STEP_BACK)) {
            wnd.stepBack();
        }
        if (action.equals(ButtonListener.STEP_FORWARD)) {
            wnd.stepForward();
        }
        if (action.equals(ButtonListener.CLEAN)) {
            wnd.clean();
        }
        if (action.equals(ButtonListener.BLACK)) {
            wnd.color = Color.BLACK;
        }
        if (action.equals(ButtonListener.WHITE)) {
            wnd.color = Color.WHITE;
        }
        if (action.equals(ButtonListener.RED)) {
            wnd.color = Color.RED;
        }
        if (action.equals(ButtonListener.BLUE)) {
            wnd.color = Color.BLUE;
        }
        if (action.equals(ButtonListener.GREEN)) {
            wnd.color = Color.GREEN;
        }
        if (action.equals(ButtonListener.YELLOW)) {
            wnd.color = Color.YELLOW;
        }
        if (action.equals(ButtonListener.COLOR_CHOOSER)) {
            wnd.color = JColorChooser.showDialog(wnd, "Choose own color", Color.WHITE);
        }
    }
}
