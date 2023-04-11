import java.util.*;

public class RowCol extends Region {
    private int[] grid;
    private boolean valid;
    private HashMap<Integer, Integer> possible;
    private Set<Integer> possibleValues;
    
    
    public RowCol (int[][] grid, int indexRowCol, String rowColState) {
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

        this.valid = true;
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
                this.valid = false;
            } else {
                this.possibleValues.remove(this.grid[i]);
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
        for (int i = 0; i < 9; i++) {
            System.out.print(this.grid[i] + "|");
        }
        System.out.println("\n----------------");
    }
}
