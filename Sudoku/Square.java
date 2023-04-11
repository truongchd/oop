import java.util.*;

public class Square extends Region {
    private int[][] grid;
    private boolean valid;
    private HashMap<Integer, Integer> possible;
    private Set<Integer> possibleValues;
    
    
    public Square (int[][] grid, int indexRow, int indexColumn) {
        // check if input square is valid

        // initialize
        this.grid = new int[3][3];
        for (int i = 0; i < 3; i++) {
            this.grid[i] = Arrays.copyOfRange(grid[i + 3 * indexRow], indexColumn * 3, indexColumn * 3 + 3);
        }

        this.valid = true;
        this.possible = new HashMap<>();
        this.possibleValues = new HashSet<>();

        for (int i = 1; i <= 9; i++) {
            this.possible.put(i, 0);
            this.possibleValues.add(i);
        }

        int currentValue = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grid[i][j] == 0) continue;
                currentValue = this.possible.get(this.grid[i][j]);
                this.possible.replace(this.grid[i][j], currentValue + 1);
                if (currentValue > 0) {
                    this.valid = false;
                } else {
                    this.possibleValues.remove(this.grid[i][j]);
                }
            }
        }
    }

    public boolean checkValid() {
        // check if grid is valid
        return this.valid;
    }

    public Set<Integer> getPossibleValues() {
        // return possible value, in case the region is valid
        // use stream if possible
        if (!this.valid) return null;
        return this.possibleValues;
    }

    public void printGrid() {
        System.out.println("----------------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.grid[i][j] + "|");
            }  
            System.out.print("\n----------------\n");
        }
    }
}
