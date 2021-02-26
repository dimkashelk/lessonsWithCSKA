import java.awt.*;

public class Line {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Line(int x, int y) {
        x1 = x2 = x;
        y1 = y2 = y;
    }

    public void move(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    public void drawLine(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
    }
}
