import java.io.*;
import java.nio.BufferUnderflowException;
import java.util.*;
import java.util.concurrent.*;
 
public class Bug {
    private TextGraphics screen;
    private int width, height;
    private boolean [][] bugs;
    private static int[] dx = new int[] {0, 0, -1, 1};
    private static int[] dy = new int[] {-1, 1, 0, 0};
 
    public Bug(int width, int height, int bugs){
        this.screen = TextGraphics.getInstance();
        this.screen.init(width, height);
        this.width = width; 
        this.height = height; 
        
        this.bugs = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.bugs[i][j] = false;
            }
        }
        while (bugs > 0) {
            while (true) {
                int x = ThreadLocalRandom.current().nextInt(0, this.width);                    
                int y = ThreadLocalRandom.current().nextInt(0, this.height); ;
                if (this.bugs[x][y] == false){
                    this.bugs[x][y] = true; 
                    screen.drawPoint('*', x, y); 
                    break;  
                }                
            }
            bugs--;
        }
    }
 
    public void tick(){
        boolean[][] tmp = new boolean[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            tmp[i] = Arrays.copyOfRange(this.bugs[i], 0, this.height);
        }

        screen.render();
        int direction, new_i, new_j;

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++){
                if (this.bugs[i][j] == false) continue;
                int tries = 150;
                while (tries > 0) {
                    direction = ThreadLocalRandom.current().nextInt(0, 4);
                    new_i = i + dx[direction];
                    new_j = j + dy[direction];

                    if (new_i < 0 || new_i >= this.width || new_j < 0 || new_j >= this.height) {
                        tries--;
                        continue;
                    }

                    if (this.screen.occupied(i, j) && !this.bugs[new_i][new_j]) {
                        this.screen.drawPoint(' ', i, j);
                        this.screen.drawPoint('*', new_i, new_j);
                        this.bugs[new_i][new_j] = true;
                        this.bugs[i][j] = false;
                        break;
                    }

                    tries--;
                }
            }   
        }
        
        for (int i = 0; i < this.width; i++) {
            this.bugs[i] = Arrays.copyOfRange(tmp[i], 0, this.height);
        }
    }
 
    public static void main(String[] args) {
 
        Bug game = new Bug(5,5,3);
        game.screen.render();
 
        while(true){
            game.tick();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            //if (now == 1000) break;
        }
 
    }
}