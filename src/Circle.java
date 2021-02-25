import java.awt.*;

public class Circle {

    private int x;
    private int y;
    private int radius;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        radius = (int) Math.sqrt((double) ((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)));
    }

    public void drawCircle(Graphics g) {
        g.drawOval(x - radius / 2, y - radius / 2, radius + radius / 2 , radius+ radius / 2);
    }
}
