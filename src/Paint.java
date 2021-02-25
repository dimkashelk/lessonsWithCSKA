import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class Paint extends JFrame {

    public static final String LINE = "line";
    public static final String RECTANGLE = "rectangle";
    public static final String CIRCLE = "circle";

    private Vector<Line> lines;
    private Vector<Rectangle> rectangles;

    private String mode = "line";

    public Paint() {
        setSize(640, 480);
        setLocation(100, 100);
        setTitle("Paint");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(
                0, 0,
                1, 1,
                0, 0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5),
                0, 0
        );

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Drawing");

        JMenuItem newLine = new JMenuItem("Line");
        newLine.addActionListener(new ButtonListener(this, ButtonListener.LINE));
        jMenu.add(newLine);

        JMenuItem newRectangle = new JMenuItem("Rectangle");
        newRectangle.addActionListener(new ButtonListener(this, ButtonListener.RECTANGLE));
        jMenu.add(newRectangle);

        JMenuItem newCircle = new JMenuItem("Circle");
        newCircle.addActionListener(new ButtonListener(this, ButtonListener.CIRCLE));
        jMenu.add(newCircle);

        jMenuBar.add(jMenu);

        JPanel jPanel = new JPanel();
        add(jPanel, gbc);
        this.setJMenuBar(jMenuBar);

        MouseActionListener mouseActionListener = new MouseActionListener(this);
        addMouseListener(mouseActionListener);
        addMouseMotionListener(mouseActionListener);
    }

    public static void main(String[] args) {
        Paint wnd = new Paint();
        wnd.setVisible(true);

        wnd.lines = new Vector<>();
        wnd.rectangles = new Vector<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Line line : lines) {
            line.drawLine(g);
        }
        for (Rectangle rectangle : rectangles) {
            rectangle.drawRectangle(g);
        }
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void addRectangle(Rectangle rectangle) {
        rectangles.add(rectangle);
    }
}
