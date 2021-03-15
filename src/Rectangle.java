import java.awt.*;

public class Rectangle implements Figure {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;


    public Rectangle(int x1, int y1, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
    }

    public Rectangle(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void move(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        g.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

    public Figure copy() {
        return new Rectangle(x1, y1, x2, y2, this.color);
    }
}
