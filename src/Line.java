import java.awt.*;

public class Line {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;

    public Line(int x, int y) {
        x1 = x2 = x;
        y1 = y2 = y;
        this.color = Color.BLACK;
    }
    public Line(int x, int y, Color color) {
        x1 = x2 = x;
        y1 = y2 = y;
        this.color = color;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = Color.BLACK;
    }
    public Line(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void move(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    public void drawLine(Graphics g) {
        g.setColor(this.color);
        g.drawLine(x1, y1, x2, y2);
    }

    public Line copy() {
        return new Line(x1, y1, x2, y2, this.color);
    }
}
