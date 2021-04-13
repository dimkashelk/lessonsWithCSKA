import java.awt.*;

public interface Figure {
    public void paint(Graphics g);

    public void move(String direction);

    public void check();
}