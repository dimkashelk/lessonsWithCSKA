import java.awt.*;

public class Block implements Figure {
    private int x;
    private int y;
    private int size_width;
    private int size_height;
    private Color color;
    private Arcanoid pr;
    private Boolean visible = true;

    public Block(int x, int y, int size_width, int size_height, Color color, Arcanoid pr) {
        this.x = x;
        this.y = y;
        this.size_width = size_width;
        this.size_height = size_height;
        this.color = color;
        this.pr = pr;
    }

    @Override
    public void paint(Graphics g) {
        if (visible) {
            g.setColor(this.color);
            g.fillRect(x + Arcanoid.BORDER,
                    y + Arcanoid.BORDER,
                    size_width - Arcanoid.BORDER,
                    size_height - Arcanoid.BORDER);
        }
    }

    @Override
    public void move(String direction) {
        if (direction.equals(Arcanoid.RIGHT)) {
            this.x = Math.min(pr.WIDTH, this.x + Arcanoid.MOVE);
        } else if (direction.equals(Arcanoid.LEFT)) {
            this.x = Math.max(0, this.x - Arcanoid.MOVE);
        }
    }

    @Override
    public void check() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setVisible(Boolean state) {
        this.visible = state;
    }

    public Boolean getVisible() {
        return visible;
    }
}
