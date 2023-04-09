import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class BallFrame {
    private JFrame ballFrame;
    private JPanel ball;

    public void initFrame (int width, int height) {
        this.ballFrame = new JFrame();
        this.ballFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ballFrame.setSize(width, height);
    }

    public void initBall () {
        this.ball = new Ball(1, 0, 100, 100, 15, 300, 300);
        this.ballFrame.add(this.ball.ball);
    }

    public void active() {
        this.initFrame(300, 300);
        this.initBall();
        this.ballFrame.setVisible(true);
    }
}