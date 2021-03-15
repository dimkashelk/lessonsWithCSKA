import java.awt.*;

public interface Figure {
    public void paint(Graphics g);

    public void move(int x, int y);

    public Figure copy();
}
