import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.Vector;


public class Paint extends JFrame {

    public static final String LINE = "line";
    public static final String RECTANGLE = "rectangle";
    public static final String CIRCLE = "circle";

    private Vector<Line> lines;
    private Vector<Rectangle> rectangles;
    private Vector<Circle> circles;

    private Stack<Vector<Line>> mnemonikaLinesLast;
    private Stack<Vector<Rectangle>> mnemonikaRectangleLast;
    private Stack<Vector<Circle>> mnemonikaCircleLast;

    private Stack<Vector<Line>> mnemonikaLinesNext;
    private Stack<Vector<Rectangle>> mnemonikaRectangleNext;
    private Stack<Vector<Circle>> mnemonikaCircleNext;

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
        JMenu drawing = new JMenu("Drawing");

        JMenuItem newLine = new JMenuItem("Line");
        newLine.addActionListener(new ButtonListener(this, ButtonListener.LINE));
        drawing.add(newLine);

        JMenuItem newRectangle = new JMenuItem("Rectangle");
        newRectangle.addActionListener(new ButtonListener(this, ButtonListener.RECTANGLE));
        drawing.add(newRectangle);

        JMenuItem newCircle = new JMenuItem("Circle");
        newCircle.addActionListener(new ButtonListener(this, ButtonListener.CIRCLE));
        drawing.add(newCircle);

        jMenuBar.add(drawing);

        JMenu actions = new JMenu("Actions");

        JMenuItem stepBack = new JMenuItem("Step back");
        stepBack.addActionListener(new ButtonListener(this, ButtonListener.STEP_BACK));
        actions.add(stepBack);

        JMenuItem stepForward = new JMenuItem("Step forward");
        stepForward.addActionListener(new ButtonListener(this, ButtonListener.STEP_FORWARD));
        actions.add(stepForward);

        JMenuItem clean = new JMenuItem("Clean");
        clean.addActionListener(new ButtonListener(this, ButtonListener.CLEAN));
        actions.add(clean);

        jMenuBar.add(actions);

        JPanel jPanel = new JPanel();
        jPanel.setDoubleBuffered(true);
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
        wnd.circles = new Vector<>();
        wnd.mnemonikaCircleLast = new Stack<>();
        wnd.mnemonikaLinesLast = new Stack<>();
        wnd.mnemonikaRectangleLast = new Stack<>();
        wnd.mnemonikaCircleNext = new Stack<>();
        wnd.mnemonikaLinesNext = new Stack<>();
        wnd.mnemonikaRectangleNext = new Stack<>();
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
        for (Circle circle : circles) {
            circle.drawCircle(g);
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

    public void addCircle(Circle circle) {
        circles.add(circle);
    }

    public void clean() {
        this.addToMnemonika();
        lines.clear();
        rectangles.clear();
        circles.clear();
        this.repaint();
    }

    public void stepForward() {
        if (mnemonikaLinesNext.size() > 0) {
            this.addToMnemonika();
            lines = mnemonikaLinesNext.get(0);
            rectangles = mnemonikaRectangleNext.get(0);
            circles = mnemonikaCircleNext.get(0);
            mnemonikaLinesNext.remove(0);
            mnemonikaRectangleNext.remove(0);
            mnemonikaCircleNext.remove(0);
        }
        this.repaint();
    }

    public void stepBack() {
        if (mnemonikaLinesLast.size() > 0) {
            this.addToForward();
            lines = mnemonikaLinesLast.lastElement();
            rectangles = mnemonikaRectangleLast.lastElement();
            circles = mnemonikaCircleLast.lastElement();
            mnemonikaLinesLast.remove(mnemonikaLinesLast.size() - 1);
            mnemonikaRectangleLast.remove(mnemonikaRectangleLast.size() - 1);
            mnemonikaCircleLast.remove(mnemonikaCircleLast.size() - 1);
        }
        this.repaint();
    }

    public void addToMnemonika() {
        Vector<Line> linesCopy = new Vector<>();
        for (Line line : lines) {
            linesCopy.add(line.copy());
        }
        Vector<Rectangle> rectanglesCopy = new Vector<>();
        for (Rectangle rectangle : rectangles) {
            rectanglesCopy.add(rectangle.copy());
        }
        Vector<Circle> circlesCopy = new Vector<>();
        for (Circle circle : circles) {
            circlesCopy.add(circle.copy());
        }
        mnemonikaLinesLast.add(linesCopy);
        mnemonikaRectangleLast.add(rectanglesCopy);
        mnemonikaCircleLast.add(circlesCopy);
        while (mnemonikaLinesLast.size() > 10) {
            mnemonikaLinesLast.pop();
            mnemonikaRectangleLast.pop();
            mnemonikaCircleLast.pop();
        }
    }

    public void addToForward() {
        Vector<Line> linesCopy = new Vector<>();
        for (Line line : lines) {
            linesCopy.add(line.copy());
        }
        Vector<Rectangle> rectanglesCopy = new Vector<>();
        for (Rectangle rectangle : rectangles) {
            rectanglesCopy.add(rectangle.copy());
        }
        Vector<Circle> circlesCopy = new Vector<>();
        for (Circle circle : circles) {
            circlesCopy.add(circle.copy());
        }
        mnemonikaLinesNext.add(0, linesCopy);
        mnemonikaRectangleNext.add(0, rectanglesCopy);
        mnemonikaCircleNext.add(0, circlesCopy);
    }
}
