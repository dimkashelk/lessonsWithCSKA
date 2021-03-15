import java.awt.*;

public class Circle implements Figure {

    private int x;
    private int y;
    private int radius;
    private Color color;

    public Circle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Circle(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public void move(int x, int y) {
        radius = (int) Math.ceil(Math.hypot(x - 30 - this.x, y - 90 - this.y));
    }

    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public Figure copy() {
        return new Circle(x, y, radius, this.color);
    }
}
