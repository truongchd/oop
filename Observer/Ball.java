import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Ball extends JPanel {
    private int size;
    private Pair<Double> velocity;
    private Pair<Double> position;
    private int bounces;
    private Pair<Integer> screen_size;

    public Ball (double dx, double dy, double x, double y, int size, int width, int height) {
        this.velocity = new Pair<Double>(dx, dy);
        this.position = new Pair<Double>(x, y);
        this.size = size;
        this.screen_size = new Pair<Integer>(width, height);
        this.bounces = 0;
    }

    public void move() {
        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;

        if (this.position.x < 0) {
            this.velocity.x = -this.velocity.x;
            this.position.x = 0;
            this.bounces++;
        } else if (this.position.x + this.size >= this.screen_size.x) {
            this.velocity.x = -this.velocity.x;
            this.position.x = this.screen.size.x - this.size;
            this.bounces++;
        }

        if (this.position.y < 0) {
            this.velocity.y = -this.velocity.y;
            this.position.y = 0;
            this.bounces++;
        } else if (this.position.y + this.size >= this.screen_size.y) {
            this.velocity.y = -this.velocity.y;
            this.position.y = this.screen.size.y - this.size;
            this.bounces++;
        }
    }

    public int bounceCount() {
        return this.bounces;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillOval(this.position.x, this.position.y, this.size, this.size);
    }
}
