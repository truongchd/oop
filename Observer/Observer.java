import javax.swing.*;
import java.awt.*;

public class Observer extends JFrame {
    private int bounces;
    private ActionButton startStop;

    public Observer() {
        this.bounces = 0;
        this.initActionButton();
        this.initFrame();
    }

    public void initActionButton () {
        this.startStop = new ActionButton();
        this.add(this.startStop, BorderLayout.CENTER);
    }

    public void initFrame () {
        this.setTitle(Integer.toString(bounces));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setLocationRelativeTo(null);
        this.setResizable(false); // prevent resizing
        this.setSize(300, 100); // set x-dim, y-dim
        this.setVisible(true); // set visibility 
    }

    public void updateBounces(int bounces) {
        this.bounces = bounces;
        this.setTitle(Integer.toString(bounces));
    }

    public void updateBall(Ball ball) {
        this.startStop.updateBall(ball);
    }
}
