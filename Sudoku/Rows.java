import java.util.*;

public class Rows {
    private int[] grid;
    private int valid;
    private HashMap<Integer, Integer> possible;
    private HashSet<Integer> possibleValues;
    private String type;
    
    public Rows (int[][] grid, int indexRowCol, String rowColState) {
        // check if input square is valid

        // initialize
        this.grid = new int[9];
        if (rowColState == "col") {
            // stream to simplify it
            for (int i = 0 ; i < 9; i++) {
                this.grid[i] = grid[i][indexRowCol];
            }
        } else {
            this.grid = Arrays.copyOfRange(grid[indexRowCol], 0, 9);
        }
        this.type = "row";
        this.initialize();
    }

    public Rows (int[][] grid, int indexRow, int indexCol) {
        // check if input square is valid

        // initialize
        this.grid = new int[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.grid[i * 3 + j] = grid[indexRow * 3 + i][indexCol * 3 + j]; 
            }
        }
        this.type = "region";
        this.initialize();
    }
    
    private void initialize() {
        this.valid = 9;
        this.possible = new HashMap<>();
        this.possibleValues = new HashSet<>();

        for (int i = 1; i <= 9; i++) {
            this.possible.put(i, 0);
            this.possibleValues.add(i);
        }

        int currentValue = 0;

        for (int i = 0; i < 9; i++) {
            if (this.grid[i] == 0) continue;
            currentValue = this.possible.get(this.grid[i]);
            this.possible.replace(this.grid[i], currentValue + 1);
            if (currentValue > 0) {
                if (currentValue == 1) {
                    this.valid--;
                }
            } else {
                this.possibleValues.remove(this.grid[i]);
            }
        }
    }

    public int[] getGrid() {
        return this.grid;
    }

    public int getValid() {
        return this.valid;
    }

    public boolean checkValid() {
        // check if grid is valid
        return this.valid == 9 ? true : false;
    }

    public HashSet<Integer> getPossibleValues() {
        // return possible value, in case the region is valid
        // use stream if possible
        if (!this.checkValid()) return null;
        return this.possibleValues;
    }

    public void edit(int pos, int new_val) {
        if (pos < 0 || pos >= 9) return;
        if (this.grid[pos] == new_val) return;
        int old_val = this.grid[pos];
        // change grid
        this.grid[pos] = new_val;
        // change possible hashmap
        int nextOldVal = -1, nextNewVal = -1;
        if (old_val != 0) {
            nextOldVal = this.possible.get(old_val) - 1;
            this.possible.replace(old_val, nextOldVal);
        }
        
        if (new_val != 0) {
            nextNewVal = this.possible.get(new_val) + 1;
            this.possible.replace(new_val, nextNewVal);
        }
        // change possible values (set)
        if (nextOldVal == 0) {
            possibleValues.add(old_val);
        } 
        if (nextNewVal == 1) {
            possibleValues.remove(new_val);
        }
        // change board state
        if (nextOldVal == 1) {
            this.valid++;
        }
        if (nextNewVal == 2) {
            this.valid--;
        }
    }  

    public void printGrid() {
        if (this.type == "row") {
            System.out.println("----------------");
            for (int i = 0; i < 9; i++) {
                System.out.print(this.grid[i] + "|");
            }
            System.out.println("\n----------------");
        } else if (this.type == "region") {
            System.out.println("----------------");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(this.grid[i * 3 + j] + "|");
                }
                System.out.print("\n----------------\n");
            }
        }
    }
}