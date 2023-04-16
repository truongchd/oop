import javax.swing.*;

public class Observer extends JFrame {
    private int bounces;
    private ActionButton startStop;
    private JLabel label;

    public Observer() {
        this.bounces = 0;
        this.initFrame();
        this.initLabel();
        this.initActionButton();
        this.setVisible(true); // set visibility 
    }

    public void initLabel () {
        this.label = new JLabel();
        this.label.setText("Bounce count: 0");
        this.label.setBounds(0, 0, 300, 50);
        this.add(this.label);
    }

    public void initActionButton () {
        this.startStop = new ActionButton();
        this.add(this.startStop);
    }

    public void initFrame () {
        this.setTitle("Observer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false); // prevent resizing
        this.setSize(300, 200); // set x-dim, y-dim
    }

    public void updateBounces(int bounces) {
        this.bounces = bounces;
        this.label.setText("Bounce count: " + Integer.toString(this.bounces));
    }

    public void updateBall(Ball ball) {
        this.startStop.updateBall(ball);
    }
}
