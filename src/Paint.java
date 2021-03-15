import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;


public class Paint extends JFrame {

    public static final String LINE = "line";
    public static final String RECTANGLE = "rectangle";
    public static final String CIRCLE = "circle";
    public static final String PENCIL = "pencil";

    private Vector<Figure> figures;
    private Vector<Figure> figuresLast;
    private int pos = 1;

    private String mode = "line";

    private BufferedImage canvas;

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

        canvas = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);

        Graphics g = canvas.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setColor(Color.white);

        MouseActionListener mouseActionListener = new MouseActionListener(this);
        addMouseListener(mouseActionListener);
        addMouseMotionListener(mouseActionListener);
    }

    public static void main(String[] args) {
        Paint wnd = new Paint();
        wnd.setVisible(true);

        wnd.figures = new Vector<>();
        wnd.figuresLast = new Vector<>();
        wnd.pos = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage buffer = new BufferedImage(canvas.getWidth(), canvas.getHeight(), canvas.getType());
        buffer.getGraphics().setColor(Color.white);
        buffer.getGraphics().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < pos; i++) {
            figures.get(i).paint(buffer.getGraphics());
        }
        g.drawImage(buffer, 0, 0, null);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void add(Figure figure) {
        this.cleanForward();
        figures.add(figure);
        pos += 1;
        this.repaint();
    }

    public void clean() {
        this.addToMnemonika();
        figures.clear();
        pos = 0;
        this.repaint();
    }

    public void stepForward() {
        pos = Math.min(figures.size(), pos + 1);
        this.repaint();
    }

    public void stepBack() {
        if (pos == 0) {
            if (figuresLast.size() > 0) {
                for (Figure figure : figuresLast) {
                    figures.add(figure.copy());
                }
                figuresLast = new Vector<>();
                pos = figures.size();
                this.repaint();
                return;
            }
        }
        pos = Math.max(0, pos - 1);
        this.repaint();
    }

    public void addToMnemonika() {
        if (figures.size() > 0)
            for (Figure figure : figures) {
                figuresLast.add(figure.copy());
            }
        this.repaint();
    }

    public void cleanForward() {
        for (int i = figures.size() - 1; i >= pos; i--) {
            figures.remove(i);
        }
        this.repaint();
    }
}
