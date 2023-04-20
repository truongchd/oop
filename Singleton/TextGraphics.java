import java.util.*;
import java.io.*;
import java.lang.*;

public class TextGraphics {
    private char[][] screen;
    private int width = -1;
    private int height = -1;

    private static final TextGraphics instance = new TextGraphics();

    private TextGraphics() {
        if (this.width != -1) {
            throw new AssertionError("You have already initiated");
        } else {
            this.screen = new char[10][10];
            this.width = 10;
            this.height = 10;
            
            for(char[] row : screen) {
                Arrays.fill(row, ' ');
            }
        }

    }

    public static TextGraphics getInstance() {
        return instance;
    }

    public void init(int width, int height) {
        this.screen = new char[width][height];
        this.width = width;
        this.height = height;
        for(char[] row : this.screen) {
            Arrays.fill(row, ' ');
        }
    }

    public void drawPoint(char c, int x, int y) {
        this.screen[x][y] = c;
    }

    public void drawLine(char c, int x1, int y1, int x2, int y2) {

    }

    public void fillRectPoint(char c, int x, int y, int w, int h) {
        for (int i = x; i < x + h; i++) {
            for (int j = y; j < y + w; j++){
                this.screen[i][j] = c;
            }
        }
    }

    public boolean occupied (int x, int y) {
        if (x < 0 || x >= this.height || y < 0 || y >= this.width) {
            return false;
        }
        if (this.screen[x][y] == ' ') {
            return false;
        }
        return true;
    }

    public void render() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                System.out.print(this.screen[i][j]);
            }
            System.out.print("\n");
        }
    }
    public static void main (String[] args) {
        TextGraphics graphics = TextGraphics.getInstance();
        graphics.init(15, 10);
        graphics.drawPoint('*', 1, 1);
        graphics.fillRectPoint('o', 5, 5, 3, 2);
        graphics.render();
    }
}