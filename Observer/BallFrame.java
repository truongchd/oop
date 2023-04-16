import javax.swing.*;
import java.awt.*;

public class BallFrame extends JFrame {
    private JFrame ballFrame;
    private Ball ball;

    public BallFrame() {
        this.ballFrame = new JFrame("Ball Frame");
        this.initBall();
        this.initFrame();
    }

    public void initFrame () {
        this.ballFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.ballFrame.setLocationRelativeTo(null);
        this.ballFrame.setResizable(false); // prevent resizing
        this.ballFrame.setSize(500, 500); // set x-dim, y-dim
        this.ballFrame.setVisible(true); // set visibility 
    }

    public void initBall () {
        this.ball = new Ball(500, 500);
        this.ballFrame.add(ball, BorderLayout.CENTER);
    }

    public void moveBall() {
        this.ball.move();
    }

    public void repaintBall() {
        this.ball.repaint();
    }
}
