import javax.swing.*;

public class ActionButton extends JButton {
    private boolean running;
    private static String[] stringTitle = {"Start", "Stop"};
    private Ball ball;

    public ActionButton () {
        this.setBounds(0, 100, 100, 50);
        this.setText("Start");
        this.running = false;
        this.addActionListener(e -> this.changeState());
    }

    public void changeState() {
        this.running = !this.running;
        this.setText(this.stringTitle[this.running ? 1 : 0]);
        this.ball.startStop();
    }

    public void updateBall(Ball ball) {
        this.ball = ball;
    }
}