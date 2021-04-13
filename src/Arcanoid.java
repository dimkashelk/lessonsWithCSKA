import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Vector;


public class Arcanoid extends JPanel implements Runnable {
    public static final int TIME_DELTA = 10;
    public static int WIDTH;
    public static int HEIGHT;
    public static final int SIZE_WIDTH = 30;
    public static final int SIZE_HEIGHT = 15;
    public static final int BORDER = 2;
    public static final int MOVE = 5;
    private String mode = "stop";
    private final int RADIUS = 5;

    private Vector<Block> figures;

    public static final String RIGHT = "right";
    public static final String LEFT = "left";
    public static final String START_GAME = "start";
    public static final String STOP_GAME = "stop";

    public Color color = Color.BLACK;

    public Window wnd;
    private Block platform;
    private Ball ball;

    public Arcanoid(Window wnd) {
        super(true);

        this.wnd = wnd;
        WIDTH = wnd.getWidth();
        HEIGHT = wnd.getHeight();
        System.out.println(WIDTH + " " + HEIGHT);

        Random rand = new Random();

        figures = new Vector<>();
        for (int height = 0; height < HEIGHT / 3; height += SIZE_HEIGHT) {
            for (int width = 0; width < WIDTH - SIZE_WIDTH; width += SIZE_WIDTH) {
                figures.add(new Block(
                        width,
                        height,
                        SIZE_WIDTH,
                        SIZE_HEIGHT,
                        new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()),
                        this));
            }
        }

        platform = new Block(
                WIDTH / 2 - SIZE_WIDTH * 3 / 2,
                HEIGHT / 20 * 17,
                SIZE_WIDTH * 3,
                SIZE_HEIGHT,
                Color.BLACK,
                this
        );

        ball = new Ball(
                WIDTH / 2,
                HEIGHT / 20 * 16,
                RADIUS,
                new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()),
                this
        );

        KeyboardListener keyboardListener = new KeyboardListener(this);
        addKeyListener(keyboardListener);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        for (int i = 0; i < figures.size(); i++) {
            Boolean res = ball.check(figures.get(i));
            if (res) {
                figures.remove(i);
                i -= 1;
                if (i < 0)
                    break;
            }
            figures.get(i).paint(g);
        }
        platform.paint(g);
        if (mode.equals(Arcanoid.START_GAME))
            ball.move("r");
        ball.paint(g);
    }

    public void add(Block figure) {
        figures.add(figure);
    }

    public void move(String direction) {
        platform.move(direction);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(TIME_DELTA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMode(String mode) {
        this.mode = mode;
        System.out.println(mode);
    }

    public Block getPlatform() {
        return platform;
    }
}