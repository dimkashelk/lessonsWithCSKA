import java.awt.*;

public class Ball implements Figure {
    private int x;
    private int y;
    private int move_x = -1;
    private int move_y = -1;
    private int radius;
    private Color color;
    private Arcanoid pr;

    public Ball(int x, int y, int radius, Color color, Arcanoid pr) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.pr = pr;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius / 2, y - radius / 2, radius * 2, radius * 2);
    }

    @Override
    public void move(String direction) {
        if (x >= radius / 2 && x + radius * 2 <= pr.WIDTH)
            x += move_x;
        else {
            move_x *= -1;
            x += move_x;
        }
        if (y >= radius / 2)
            y += move_y;
        else {
            move_y *= -1;
            y += move_y;
        }
        if (y + radius >= pr.HEIGHT - radius * 2)
            pr.setMode(Arcanoid.STOP_GAME);
        check();
    }

    @Override
    public void check() {
        if (pr.getPlatform().getY() < y + radius * 2 &&
                (pr.getPlatform().getX() < x - radius &&
                        pr.getPlatform().getX() + Arcanoid.SIZE_WIDTH * 3 > x - radius ||
                        pr.getPlatform().getX() < x + radius &&
                                pr.getPlatform().getX() + Arcanoid.SIZE_WIDTH * 3 > x + radius)) {
            move_y *= -1;
        }
    }

    public Boolean check(Block block) {
        if ((block.getY() + Arcanoid.SIZE_HEIGHT >= y - radius && block.getY() <= y - radius ||
                block.getY() + Arcanoid.SIZE_HEIGHT >= y + radius && block.getY() <= y + radius) &&
                (block.getX() < x - radius && block.getX() + Arcanoid.SIZE_HEIGHT > x - radius ||
                        block.getX() < x + radius && block.getX() + Arcanoid.SIZE_HEIGHT > x + radius)) {
            move_y *= -1;
            return true;
        }
        if ((block.getX() + Arcanoid.SIZE_WIDTH >= x - radius && block.getX() <= x - radius ||
                block.getX() + Arcanoid.SIZE_WIDTH >= x + radius && block.getX() <= x + radius) &&
                (block.getY() < y - radius && block.getY() + Arcanoid.SIZE_WIDTH > y - radius ||
                        block.getY() < y + radius && block.getY() + Arcanoid.SIZE_WIDTH > y + radius)) {
            move_y *= -1;
            return true;
        }
        return false;
    }
}
