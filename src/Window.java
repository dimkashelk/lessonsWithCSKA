import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() throws HeadlessException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen_size = toolkit.getScreenSize();
        Dimension window_size = new Dimension(
                948,
                screen_size.height / 2 + 50
        );
        Point window_location = new Point(
                (screen_size.width - window_size.width) / 2,
                (screen_size.height - window_size.height) / 2
        );
        setTitle("Arcanoid");
        setLocation(window_location);
        setSize(window_size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        Arcanoid arcanoid = new Arcanoid(this);
        add(arcanoid, BorderLayout.CENTER);

        KeyboardListener keyboardListener = new KeyboardListener(arcanoid);
        addKeyListener(keyboardListener);

        Thread arcanoidThread = new Thread(arcanoid);
        arcanoidThread.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        Window wnd = new Window();
    }
}