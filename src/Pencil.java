import java.awt.*;

public class Pencil implements Figure {

    private int size = 1000;
    private int tSize = 0;

    private int[] x = new int[size];
    private int[] y = new int[size];

    private Color color;

    public Pencil(int x, int y, Color color) {
        this.x[0] = x;
        this.y[0] = y;
        this.color = color;
    }

    public Pencil(int[] x, int[] y, int size, int tSize, Color color) {
        this.x = new int[size];
        this.y = new int[size];
        for (int i = 0; i < size; i++) {
            this.x[i] = x[i];
            this.y[i] = y[i];
        }
        this.size = size;
        this.tSize = tSize;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawPolyline(x, y, tSize + 1);
    }

    @Override
    public void move(int x, int y) {

    }

    public void add(int x, int y) {
        tSize++;
        if (tSize >= size - 100) {
            size += 1000;
            int[] dopX = new int[size];
            int[] dopY = new int[size];
            for (int i = 0; i < tSize; i++) {
                dopX[i] = this.x[i];
                dopY[i] = this.y[i];
            }
            this.x = new int[size];
            this.y = new int[size];
            for (int i = 0; i < tSize; i++) {
                this.x[i] = dopX[i];
                this.y[i] = dopY[i];
            }
        }
        this.x[tSize] = x;
        this.y[tSize] = y;
    }

    public Figure copy() {
        int[] dopX = new int[size];
        int[] dopY = new int[size];
        for (int i = 0; i < size; i++) {
            dopX[i] = this.x[i];
            dopY[i] = this.y[i];
        }
        return new Pencil(dopX, dopY, size, tSize, this.color);
    }
}
