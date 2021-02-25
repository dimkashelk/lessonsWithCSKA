import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class MouseActionListener implements MouseListener, MouseMotionListener {

    private Paint wnd;

    private Line currentLine;

    public MouseActionListener(Paint wnd) {
        this.wnd = wnd;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (wnd.getMode().equals(wnd.LINE)) {
                currentLine = new Line(e.getX(), e.getY());
                wnd.addLine(currentLine);
            }
        }
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
        if (wnd.getMode().equals(wnd.LINE)) {
            currentLine.move(e.getX(), e.getY());
            wnd.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (wnd.getMode().equals(wnd.LINE)) {
                currentLine.move(e.getX(), e.getY());
                wnd.repaint();
            }
        }
    }
}
