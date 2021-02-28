import java.awt.*;

public class Circle {

    private int x;
    private int y;
    private int radius;
    private Color color;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
    }
    public Circle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = Color.BLACK;
    }
    public Circle(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public void move(int x, int y) {
        radius = (int) Math.sqrt((double) ((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)));
    }

    public void drawCircle(Graphics g) {
        g.setColor(this.color);
        g.drawOval(x - radius / 2, y - radius / 2, radius + radius / 2, radius + radius / 2);
        g.fillOval(x - radius / 2, y - radius / 2, radius + radius / 2, radius + radius / 2);
    }

    public Circle copy() {
        return new Circle(x, y, radius, this.color);
    }
}
