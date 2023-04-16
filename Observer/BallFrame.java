import javax.swing.*;
import java.awt.*;

public class BallFrame extends JFrame {
    private Ball ball;

    public BallFrame(Observer observer) {
        this.initBall(observer);
        this.initFrame();
    }

    public void initBall (Observer observer) {
        this.ball = new Ball(500, 500, observer);
        this.add(this.ball, BorderLayout.CENTER);
    }

    public void initFrame () {
        this.setTitle("Ball Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setLocationRelativeTo(null);
        this.setResizable(false); // prevent resizing
        this.setSize(500, 500); // set x-dim, y-dim
        this.setVisible(true); // set visibility 
    }

    public void moveBall() {
        this.ball.move();
    }

    public void repaintBall() {
        this.ball.repaint();
    }
}
