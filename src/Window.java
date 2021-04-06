import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() throws HeadlessException {
        setTitle("Painter");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        Paint pc = new Paint(this);
        add(pc, BorderLayout.CENTER);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu drawing = new JMenu("Drawing");

        JMenuItem newLine = new JMenuItem("Line");
        newLine.addActionListener(new ButtonListener(pc, ButtonListener.LINE));
        drawing.add(newLine);

        JMenuItem newRectangle = new JMenuItem("Rectangle");
        newRectangle.addActionListener(new ButtonListener(pc, ButtonListener.RECTANGLE));
        drawing.add(newRectangle);

        JMenuItem newCircle = new JMenuItem("Circle");
        newCircle.addActionListener(new ButtonListener(pc, ButtonListener.CIRCLE));
        drawing.add(newCircle);

        JMenuItem newPencil = new JMenuItem("Pencil");
        newPencil.addActionListener(new ButtonListener(pc, ButtonListener.PENCIL));
        drawing.add(newPencil);

        JMenuItem fillButton = new JMenuItem("Fill");
        fillButton.addActionListener(new ButtonListener(pc, ButtonListener.FILL));
        drawing.add(fillButton);

        jMenuBar.add(drawing);

        JMenu actions = new JMenu("Actions");

        JMenuItem stepBack = new JMenuItem("Step back");
        stepBack.addActionListener(new ButtonListener(pc, ButtonListener.STEP_BACK));
        actions.add(stepBack);

        JMenuItem stepForward = new JMenuItem("Step forward");
        stepForward.addActionListener(new ButtonListener(pc, ButtonListener.STEP_FORWARD));
        actions.add(stepForward);

        JMenuItem clean = new JMenuItem("Clean");
        clean.addActionListener(new ButtonListener(pc, ButtonListener.CLEAN));
        actions.add(clean);

        jMenuBar.add(actions);

        JMenu color = new JMenu("Color");

        JMenuItem white = new JMenuItem("White");
        white.addActionListener(new ButtonListener(pc, ButtonListener.WHITE));
        color.add(white);

        JMenuItem black = new JMenuItem("Black");
        black.addActionListener(new ButtonListener(pc, ButtonListener.BLACK));
        color.add(black);

        JMenuItem red = new JMenuItem("Red");
        red.addActionListener(new ButtonListener(pc, ButtonListener.RED));
        color.add(red);

        JMenuItem blue = new JMenuItem("Blue");
        red.addActionListener(new ButtonListener(pc, ButtonListener.BLUE));
        color.add(red);

        JMenuItem green = new JMenuItem("Green");
        green.addActionListener(new ButtonListener(pc, ButtonListener.GREEN));
        color.add(green);

        JMenuItem yellow = new JMenuItem("Yellow");
        yellow.addActionListener(new ButtonListener(pc, ButtonListener.YELLOW));
        color.add(yellow);

        JMenuItem colorChooser = new JMenuItem("Own color");
        colorChooser.addActionListener(new ButtonListener(pc, ButtonListener.COLOR_CHOOSER));
        color.add(colorChooser);

        jMenuBar.add(color);

        this.setJMenuBar(jMenuBar);

        setVisible(true);
    }

    public static void main(String[] args) {
        Window wnd = new Window();
    }
}
