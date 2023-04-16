import javax.swing.*;
import java.awt.*;

public class Ball extends JPanel {
    private int x, y;
    private int dx, dy;
    private int size;
    private int bounces;
    private int screen_w, screen_h;
    private static int height_minus = 30;
    private Observer observer;
    private boolean moving;

    public Ball (int screen_w, int screen_h, Observer observer) {
        this.x = 150;
        this.y = 150;
        this.dx = 4;
        this.dy = 3;
        this.size = 15;
        this.bounces = 0;
        this.screen_w = screen_w;
        this.screen_h = screen_h;
        this.observer = observer;
        this.moving = false;
        this.observer.updateBall(this);
    }

    public void move() {
        if (!this.moving) return;
        this.x += this.dx;
        this.y += this.dy;

        if (this.x + this.size > this.screen_w || this.x < 0) {
            if (this.x + this.size > this.screen_w) {
                this.x = this.screen_w - this.size;
            }
            this.dx = -this.dx;
            this.bounces++;
            this.observer.updateBounces(this.bounces);
        }

        if (this.y + this.size - height_minus > this.screen_h || this.y < 0) {
            if (this.y + this.size > this.screen_h - height_minus) {
                this.y = this.screen_h - height_minus - this.size;
            }
            this.dy = -this.dy;
            this.bounces++;
            this.observer.updateBounces(this.bounces);
        }
    }

    public void startStop() {
        this.moving = !this.moving;
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(this.x, this.y, this.size, this.size);
    }
}
