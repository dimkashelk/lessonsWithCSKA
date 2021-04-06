import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;


public class Paint extends JPanel {
    private int WIDTH = 300;
    private int HEIGHT = 300;

    public static final String LINE = "line";
    public static final String RECTANGLE = "rectangle";
    public static final String CIRCLE = "circle";
    public static final String PENCIL = "pencil";
    public static final String FILL = "fill";

    private Vector<Figure> figures;
    private Vector<Figure> figuresLast;
    private int pos = 1;

    private String mode = "line";

    public Color color = Color.BLACK;

    private Window wnd;

    public Paint(Window wnd) {
        super(true);

        this.wnd = wnd;

        figures = new Vector<>();
        figuresLast = new Vector<>();
        pos = 0;

        MouseActionListener mal = new MouseActionListener(this);
        addMouseListener(mal);
        addMouseMotionListener(mal);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        buffer.getGraphics().setColor(Color.WHITE);
        buffer.getGraphics().fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
        buffer.getGraphics().setColor(color);
        for (int i = 0; i < pos; i++)
            figures.get(i).paint(buffer.getGraphics());
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

    public void fillArea(int x, int y) {
        Pencil fillingArea = new Pencil(x, y, this.color);
        BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        buffer.getGraphics().setColor(Color.white);
        buffer.getGraphics().fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
        for (int i = 0; i < pos; i++) {
            figures.get(i).paint(buffer.getGraphics());
        }
        Color toChange = new Color(buffer.getRGB(x, y));

        fill(x, y, toChange, buffer, fillingArea);
        this.add(fillingArea);
        repaint();
    }

    private void fill(int x, int y, Color color, BufferedImage bufferedImage, Pencil fillingArea) {
        Vector<Integer> X = new Vector<>();
        Vector<Integer> Y = new Vector<>();
        X.add(x);
        Y.add(y);
        bufferedImage.setRGB(x, y, this.color.getRGB());
        Vector<Integer> dopX = new Vector<>();
        Vector<Integer> dopY = new Vector<>();
        if (x - 1 >= 0 && x - 1 < bufferedImage.getHeight() && y >= 0 && y < bufferedImage.getWidth()) {
            Color t = new Color(bufferedImage.getRGB(x - 1, y));
            if (t.equals(color)) {
                dopX.add(x - 1);
                dopY.add(y);
                fillingArea.add(x - 1, y);
                bufferedImage.setRGB(x - 1, y, this.color.getRGB());
            }
        }
        if (x + 1 >= 0 && x + 1 < bufferedImage.getHeight() && y >= 0 && y < bufferedImage.getWidth()) {
            Color t = new Color(bufferedImage.getRGB(x + 1, y));
            if (t.equals(color)) {
                dopX.add(x + 1);
                dopY.add(y);
                fillingArea.add(x + 1, y);
                bufferedImage.setRGB(x + 1, y, this.color.getRGB());
            }
        }
        if (x >= 0 && x < bufferedImage.getHeight() && y - 1 >= 0 && y - 1 < bufferedImage.getWidth()) {
            Color t = new Color(bufferedImage.getRGB(x, y - 1));
            if (t.equals(color)) {
                dopX.add(x);
                dopY.add(y - 1);
                fillingArea.add(x, y - 1);
                bufferedImage.setRGB(x, y - 1, this.color.getRGB());
            }
        }
        if (x >= 0 && x < bufferedImage.getHeight() && y + 1 >= 0 && y + 1 < bufferedImage.getWidth()) {
            Color t = new Color(bufferedImage.getRGB(x, y));
            if (t.equals(color)) {
                dopX.add(x);
                dopY.add(y + 1);
                fillingArea.add(x, y + 1);
                bufferedImage.setRGB(x, y + 1, this.color.getRGB());
            }
        }
        X = new Vector<>(dopX);
        Y = new Vector<>(dopY);
        while (X.size() != 0) {
            dopX = new Vector<>();
            dopY = new Vector<>();
            for (int i = 0; i < X.size(); i++) {
                if (X.get(i) - 1 >= 0 && X.get(i) - 1 < bufferedImage.getHeight() && Y.get(i) >= 0 && Y.get(i) < bufferedImage.getWidth()) {
                    Color t = new Color(bufferedImage.getRGB(X.get(i) - 1, Y.get(i)));
                    if (t.equals(color)) {
                        dopX.add(X.get(i) - 1);
                        dopY.add(Y.get(i));
                        fillingArea.add(X.get(i) - 1, Y.get(i));
                        bufferedImage.setRGB(X.get(i) - 1, Y.get(i), Color.GRAY.getRGB());
                    }
                }
                if (X.get(i) + 1 >= 0 && X.get(i) + 1 < bufferedImage.getHeight() && Y.get(i) >= 0 && Y.get(i) < bufferedImage.getWidth()) {
                    Color t = new Color(bufferedImage.getRGB(X.get(i) + 1, Y.get(i)));
                    if (t.equals(color)) {
                        dopX.add(X.get(i) + 1);
                        dopY.add(Y.get(i));
                        fillingArea.add(X.get(i) + 1, Y.get(i));
                        bufferedImage.setRGB(X.get(i) + 1, Y.get(i), Color.GRAY.getRGB());
                    }
                }
                if (X.get(i) >= 0 && X.get(i) < bufferedImage.getHeight() && Y.get(i) - 1 >= 0 && Y.get(i) - 1 < bufferedImage.getWidth()) {
                    Color t = new Color(bufferedImage.getRGB(X.get(i), Y.get(i) - 1));
                    if (t.equals(color)) {
                        dopX.add(X.get(i));
                        dopY.add(Y.get(i) - 1);
                        fillingArea.add(X.get(i), Y.get(i) - 1);
                        bufferedImage.setRGB(X.get(i), Y.get(i) - 1, Color.GRAY.getRGB());
                    }
                }
                if (X.get(i) >= 0 && X.get(i) < bufferedImage.getHeight() && Y.get(i) + 1 >= 0 && Y.get(i) + 1 < bufferedImage.getWidth()) {
                    Color t = new Color(bufferedImage.getRGB(X.get(i), Y.get(i) + 1));
                    if (t.equals(color)) {
                        dopX.add(X.get(i));
                        dopY.add(Y.get(i) + 1);
                        fillingArea.add(X.get(i), Y.get(i) + 1);
                        bufferedImage.setRGB(X.get(i), Y.get(i) + 1, Color.GRAY.getRGB());
                    }
                }
            }
            X = new Vector<>(dopX);
            Y = new Vector<>(dopY);
        }
    }
}
