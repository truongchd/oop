import java.util.*;
import java.io.*;
import java.lang.*;

public class TextGraphics {
    private char[][] screen;
    private int width;
    private int height;

    private static final TextGraphics instance = new TextGraphics();

    private TextGraphics(int w, int h) {
        this.screen = new char[w][h];
        this.width = w;
        this.height = h;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.screen[i][j] = ' ';
            }
        }
    }

    /*public TextGraphics getInstance() {
        return this;
    }*/

    public void drawPoint(char c, int x, int y) {
        this.screen[x - 1][y - 1] = c;
    }

    public void drawLine(char c, int x1, int y1, int x2, int y2) {

    }

    public void fillRectPoint(char c, int x, int y, int w, int h) {
        for (int i = x - 1; i < x + w; i++) {
            for (int j = y - 1; j < y + h; j++){
                this.screen[i][j] = c;
            }
        }
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
        TextGraphics graphics = new TextGraphics();
        graphics.init(15, 10);
        graphics.drawPoint('*', 1, 1);
        graphics.fillRectPoint('o', 5, 5, 3, 2);
        graphics.render();
    }

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }
}