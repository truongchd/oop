import javax.swing.*;

public class Observer {
    private JFrame observer;
    private int bounces;

    public Observer() {
        this.bounces = 0;
        this.observer = new JFrame(Integer.toString(bounces));
        this.initFrame();
    }

    public void initFrame () {
        this.observer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.observer.setLocationRelativeTo(null);
        this.observer.setResizable(false); // prevent resizing
        this.observer.setSize(300, 100); // set x-dim, y-dim
        this.observer.setVisible(true); // set visibility 
    }

    public void updateBounces(int bounces) {
        this.bounces = bounces;
        this.observer.setTitle(Integer.toString(bounces));
    }
}
