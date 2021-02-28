import java.awt.*;
import java.util.Vector;

public class Pencil {

    private int size = 1000;
    private int tSize = 0;

    private int[] x = new int[size];
    private int[] y = new int[size];

    private Color color;

    public Pencil(int x, int y) {
        this.x[0] = x;
        this.y[0] = y;
        this.color = Color.BLACK;
    }

    public Pencil(int x, int y, Color color) {
        this.x[0] = x;
        this.y[0] = y;
        this.color = color;
    }

    public Pencil(int[] x, int[] y, int size, int tSize) {
        this.x = new int[size];
        this.y = new int[size];
        for (int i = 0; i < size; i++) {
            this.x[i] = x[i];
            this.y[i] = y[i];
        }
        this.size = size;
        this.tSize = tSize;
        this.color = Color.BLACK;
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

    public void drawPolyline(Graphics g) {
        g.setColor(this.color);
        g.drawPolyline(x, y, tSize + 1);
    }

    public void add(int x, int y) {
        tSize++;
        if (tSize == size) {
            size += 1000;
            int[] dopX = new int[size];
            int[] dopY = new int[size];
            for (int i = 0; i < size; i++) {
                dopX[i] = this.x[i];
                dopY[i] = this.y[i];
            }
        }
        this.x[tSize] = x;
        this.y[tSize] = y;
    }

    public Pencil copy() {
        int[] dopX = new int[size];
        int[] dopY = new int[size];
        for (int i = 0; i < size; i++) {
            dopX[i] = this.x[i];
            dopY[i] = this.y[i];
        }
        return new Pencil(dopX, dopY, size, tSize, this.color);
    }
}
