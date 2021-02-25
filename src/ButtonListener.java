import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    public static final String LINE = "line";
    public static final String RECTANGLE = "rectangle";
    public static final String CIRCLE = "circle";

    private Paint wnd;
    private String action;

    public ButtonListener(Paint wnd, String action) {
        this.wnd = wnd;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.equals(ButtonListener.LINE)) {
            wnd.setMode(ButtonListener.LINE);
        }
        if (e.equals(ButtonListener.CIRCLE)) {
            wnd.setMode(ButtonListener.CIRCLE);
        }
        if (e.equals(ButtonListener.RECTANGLE)) {
            wnd.setMode(ButtonListener.RECTANGLE);
        }
    }
}
