import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    Arcanoid arcanoid;

    public KeyboardListener(Arcanoid arcanoid) {
        this.arcanoid = arcanoid;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            arcanoid.move(Arcanoid.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            arcanoid.move(Arcanoid.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            arcanoid.setMode(Arcanoid.START_GAME);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            arcanoid.move(Arcanoid.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            arcanoid.move(Arcanoid.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            arcanoid.setMode(Arcanoid.START_GAME);
        }
    }
}
