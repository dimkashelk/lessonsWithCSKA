import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.Vector;


public class Paint extends JFrame {

    public static final String LINE = "line";
    public static final String RECTANGLE = "rectangle";
    public static final String CIRCLE = "circle";
    public static final String PENCIL = "pencil";

    private Vector<Line> lines;
    private Vector<Rectangle> rectangles;
    private Vector<Circle> circles;
    private Vector<Pencil> pencils;


    private Stack<Vector<Line>> mnemonikaLinesLast;
    private Stack<Vector<Rectangle>> mnemonikaRectangleLast;
    private Stack<Vector<Circle>> mnemonikaCircleLast;
    private Stack<Vector<Pencil>> mnemonikaPencilsLast;

    private Stack<Vector<Line>> mnemonikaLinesNext;
    private Stack<Vector<Rectangle>> mnemonikaRectangleNext;
    private Stack<Vector<Circle>> mnemonikaCircleNext;
    private Stack<Vector<Pencil>> mnemonikaPencilsNext;

    private String mode = "line";

    public Color color = Color.BLACK;
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

        JMenuItem newPencil = new JMenuItem("Pencil");
        newPencil.addActionListener(new ButtonListener(this, ButtonListener.PENCIL));
        drawing.add(newPencil);

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

        JMenu color = new JMenu("Color");

        JMenuItem white = new JMenuItem("White");
        white.addActionListener(new ButtonListener(this, ButtonListener.WHITE));
        color.add(white);

        JMenuItem black = new JMenuItem("Black");
        black.addActionListener(new ButtonListener(this, ButtonListener.BLACK));
        color.add(black);

        JMenuItem red = new JMenuItem("Red");
        red.addActionListener(new ButtonListener(this, ButtonListener.RED));
        color.add(red);

        JMenuItem blue = new JMenuItem("Blue");
        red.addActionListener(new ButtonListener(this, ButtonListener.BLUE));
        color.add(red);

        JMenuItem green = new JMenuItem("Green");
        green.addActionListener(new ButtonListener(this, ButtonListener.GREEN));
        color.add(green);

        JMenuItem yellow = new JMenuItem("Yellow");
        yellow.addActionListener(new ButtonListener(this, ButtonListener.YELLOW));
        color.add(yellow);

        JMenuItem colorChooser = new JMenuItem("Own color");
        colorChooser.addActionListener(new ButtonListener(this, ButtonListener.COLOR_CHOOSER));
        color.add(colorChooser);


        jMenuBar.add(color);

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
        wnd.pencils = new Vector<>();
        wnd.mnemonikaCircleLast = new Stack<>();
        wnd.mnemonikaLinesLast = new Stack<>();
        wnd.mnemonikaRectangleLast = new Stack<>();
        wnd.mnemonikaPencilsLast = new Stack<>();
        wnd.mnemonikaCircleNext = new Stack<>();
        wnd.mnemonikaLinesNext = new Stack<>();
        wnd.mnemonikaRectangleNext = new Stack<>();
        wnd.mnemonikaPencilsNext = new Stack<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(this.color);
        for (Line line : lines) {
            line.drawLine(g);
        }
        for (Rectangle rectangle : rectangles) {
            rectangle.drawRectangle(g);
        }
        for (Circle circle : circles) {
            circle.drawCircle(g);
        }
        for (Pencil pencil : pencils) {
            pencil.drawPolyline(g);
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
        this.cleanForward();
    }

    public void addRectangle(Rectangle rectangle) {
        rectangles.add(rectangle);
        this.cleanForward();
    }

    public void addCircle(Circle circle) {
        circles.add(circle);
        this.cleanForward();
    }

    public void addPencil(Pencil pencil) {
        pencils.add(pencil);
        this.cleanForward();
    }

    public void clean() {
        this.addToMnemonika();
        lines.clear();
        rectangles.clear();
        circles.clear();
        pencils.clear();
        this.repaint();
    }

    public void stepForward() {
        if (mnemonikaLinesNext.size() > 0) {
            this.addToMnemonika();
            lines = mnemonikaLinesNext.get(0);
            rectangles = mnemonikaRectangleNext.get(0);
            circles = mnemonikaCircleNext.get(0);
            pencils = mnemonikaPencilsNext.get(0);
            mnemonikaLinesNext.remove(0);
            mnemonikaRectangleNext.remove(0);
            mnemonikaCircleNext.remove(0);
            mnemonikaPencilsNext.remove(0);
        }
        this.repaint();
    }

    public void stepBack() {
        if (mnemonikaLinesLast.size() > 0) {
            this.addToForward();
            lines = mnemonikaLinesLast.lastElement();
            rectangles = mnemonikaRectangleLast.lastElement();
            circles = mnemonikaCircleLast.lastElement();
            pencils = mnemonikaPencilsLast.lastElement();
            mnemonikaLinesLast.remove(mnemonikaLinesLast.size() - 1);
            mnemonikaRectangleLast.remove(mnemonikaRectangleLast.size() - 1);
            mnemonikaCircleLast.remove(mnemonikaCircleLast.size() - 1);
            mnemonikaPencilsLast.remove(mnemonikaPencilsLast.size() - 1);
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
        Vector<Pencil> pencilsCopy = new Vector<>();
        for (Pencil pencil : pencils) {
            pencilsCopy.add(pencil.copy());
        }
        mnemonikaLinesLast.add(linesCopy);
        mnemonikaRectangleLast.add(rectanglesCopy);
        mnemonikaCircleLast.add(circlesCopy);
        mnemonikaPencilsLast.add(pencilsCopy);
        while (mnemonikaLinesLast.size() > 10) {
            mnemonikaLinesLast.pop();
            mnemonikaRectangleLast.pop();
            mnemonikaCircleLast.pop();
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
        Vector<Pencil> pencilsCopy = new Vector<>();
        for (Pencil pencil : pencils) {
            pencilsCopy.add(pencil.copy());
        }
        mnemonikaLinesNext.add(0, linesCopy);
        mnemonikaRectangleNext.add(0, rectanglesCopy);
        mnemonikaCircleNext.add(0, circlesCopy);
        mnemonikaPencilsNext.add(0, pencilsCopy);
    }

    public void cleanForward() {
        mnemonikaPencilsNext.clear();
        mnemonikaRectangleNext.clear();
        mnemonikaLinesNext.clear();
        mnemonikaCircleNext.clear();
    }
}
