import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class MouseActionListener implements MouseListener, MouseMotionListener {

    private Paint wnd;

    public MouseActionListener(Paint wnd) {
        this.wnd = wnd;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Exited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Dragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Moved");
    }
}
