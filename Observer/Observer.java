import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Observer implements ActionListener {
    private JButton button;
 
    public void go() {
        button = new JButton("click me");
        button.addActionListener(this);
 
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
 
    // override
    public void actionPerformed(ActionEvent actionEvent) {
        button.setText("I've been clicked!");
    }
 
    public static void main(String[] args) {
        Observer gui = new Observer();
        gui.go();
    }
}