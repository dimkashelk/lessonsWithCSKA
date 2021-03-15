import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class MouseActionListener implements MouseListener, MouseMotionListener {

    private final Paint wnd;

    private Line currentLine;
    private Rectangle currentRectangle;
    private Circle currentCircle;
    private Pencil currentPencil;

    public MouseActionListener(Paint wnd) {
        this.wnd = wnd;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.mouseDragged(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (wnd.getMode().equals(Paint.LINE)) {
                currentLine = new Line(e.getX(), e.getY(), wnd.color);
                wnd.add(currentLine);
                wnd.addToMnemonika();
            }
            if (wnd.getMode().equals(Paint.RECTANGLE)) {
                currentRectangle = new Rectangle(e.getX(), e.getY(), wnd.color);
                wnd.add(currentRectangle);
                wnd.addToMnemonika();
            }
            if (wnd.getMode().equals(Paint.CIRCLE)) {
                currentCircle = new Circle(e.getX(), e.getY(), wnd.color);
                wnd.add(currentCircle);
                wnd.addToMnemonika();
            }
            if (wnd.getMode().equals(Paint.PENCIL)) {
                currentPencil = new Pencil(e.getX(), e.getY(), wnd.color);
                wnd.add(currentPencil);
                wnd.addToMnemonika();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (wnd.getMode().equals(Paint.LINE)) {
            currentLine.move(e.getX(), e.getY());
            wnd.repaint();
        }
        if (wnd.getMode().equals(Paint.RECTANGLE)) {
            currentRectangle.move(e.getX(), e.getY());
            wnd.repaint();
        }
        if (wnd.getMode().equals(Paint.CIRCLE)) {
            currentCircle.move(e.getX(), e.getY());
            wnd.repaint();
        }
        if (wnd.getMode().equals(Paint.PENCIL)) {
            currentPencil.add(e.getX(), e.getY());
            wnd.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
