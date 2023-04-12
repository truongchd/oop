import java.util.*;

import javax.management.RuntimeErrorException;

public class Sudoku {
    private int[][] grid;
    private Rows[][] regions;
    private Rows[] rows;
    private Rows[] cols;
    // next
    private int[][] nextGrid;

    public Sudoku (int[][] grid) {
        // check if grid has size 9x9

        // initialize
        // this.grid
        this.grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            this.grid[i] = this.nextGrid[i] = Arrays.copyOfRange(grid[i], 0, 9); 
        }

        // regions
        this.regions = new Rows[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.regions[i][j] = new Rows(this.grid, i, j);
            }
        }

        this.rows = new Rows[9];
        for (int i = 0; i < 9; i++) {
            this.rows[i] = new Rows(this.grid, i, "row");
        }

        this.cols = new Rows[9];
        for (int i = 0; i < 9; i++) {
            this.cols[i] = new Rows(this.grid, i, "col");
        }
    }

    public Sudoku (String gridString) {
        this(parseString(gridString));
    }

    public Sudoku (String... gridStrings) {
        this(parseStrings(gridStrings));
    }

    public Sudoku (Sudoku prev) {
        // grid
        this.grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            this.grid[i] = Arrays.copyOfRange(prev.getGrid()[i], 0, 9);
        }
        
        
        // regions
        this.regions = new Rows[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                
            }
        }

        this.rows = new Rows[9];
        for (int i = 0; i < 9; i++) {
            this.rows[i] = new Rows(this.grid, i, "row");
        }

        this.cols = new Rows[9];
        for (int i = 0; i < 9; i++) {
            this.cols[i] = new Rows(this.grid, i, "col");
        }
    }

    public void modify (int row, int col, int new_val) {

    }

    public Rows getRegion(int row, int col) {
        return regions[row][col];    
    }

    public Rows getRowCol(int rowCol, String state) {
        if (state == "row") return rows[rowCol];
        return cols[rowCol];    
    }

    public int[][] getGrid () {
        return this.grid;
    }

    public HashSet<Integer> getPossibleValues (int row, int col) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) return null;
        if (this.grid[row][col] != 0) return null;
        HashSet<Integer> res = new HashSet<>();
        res.addAll(this.regions[row / 3][col / 3].getPossibleValues());
        res.retainAll(this.rows[row].getPossibleValues());
        res.retainAll(this.cols[col].getPossibleValues());
        return res;
    }

    private static int BOARD_INSOLUBLE = 0; // board can't be solved
    private static int BOARD_SQUARE_AVAILABLE = 1;
    private static int BOARD_NO_SQUARE_AVAILABLE = 2; // board don't have square could be filled
    private static int BOARD_SOLVED = 3;

    public int findSquareOneAvailable () {
        // check if board is unable to solve + board have any numbers can be filled immediately
        HashSet<Integer> tmp = new HashSet<>();
        int state_of_board = BOARD_NO_SQUARE_AVAILABLE;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j] != 0) continue;
                tmp.clear();
                tmp.addAll(this.getPossibleValues(i, j));
                if (tmp.size() == 0) {
                    return BOARD_INSOLUBLE;
                }
                if (tmp.size() == 1) {
                    for (int tmpInt: tmp) {
                        this.nextGrid[i][j] = tmpInt;
                    }
                    state_of_board = BOARD_SQUARE_AVAILABLE;
                }
            }
        }
        return state_of_board;
    }

    public void AddFirstSquareRandom() {
        // assume that this.getPossibleValues(i, j) >= 2 with all i, j
        // add random value of one square
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j] != 0) continue;
                for (int tmp: this.getPossibleValues(i, j)) {
                    this.grid[i][j] = tmp;
                    break;
                }
            }
        }
    }

    public boolean full() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j] == 0) return false;
            }
        }
        return true;
    }

    public int solve () {
        // solve sudoku grid
        int state_of_board = this.findSquareOneAvailable();
        if (state_of_board == BOARD_INSOLUBLE) {
            return BOARD_INSOLUBLE;
        } 
        if (this.full()) {
            return BOARD_SOLVED;
        }
        if (state_of_board == BOARD_SQUARE_AVAILABLE) {

        } else if (state_of_board == BOARD_NO_SQUARE_AVAILABLE) {
            this.AddFirstSquareRandom();
        } 
        return BOARD_INSOLUBLE;
    }

    public String toString() {
        // return String of board, using StringBuilder
        String res = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                res = res + Integer.toString(this.grid[i][j]) + " ";
            }
        }
        return res;
    }

    public void printGrid() {
        System.out.println("----------------");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.grid[i][j] + "|");
            }  
            System.out.print("\n----------------\n");
        }
    }

    private static int[][] parseString(String gridString) {
        int[][] gridToParse = new int[9][9];
        int index = 0, middleware;
        StringTokenizer tok = new StringTokenizer(gridString);
        try {
            while (tok.hasMoreTokens()) {
                middleware = Integer.parseInt(tok.nextToken());
                gridToParse[index / 9][index % 9] = middleware;
                index++;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Could not parse x + string:" + gridString);
        }

        return gridToParse;
    }

    private static int[][] parseStrings(String... gridStrings) {
        int[][] gridToParse = new int[9][9];
        int height = 0, middleware;
        for (String gridString: gridStrings) {
            int width = 0;
            StringTokenizer tok = new StringTokenizer(gridString);
            try {
                while (tok.hasMoreTokens()) {
                    middleware = Integer.parseInt(tok.nextToken());
                    gridToParse[height][width] = middleware;
                    width++;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Could not parse x + string:" + gridString);
            }
            height++;
        }
        return gridToParse;
    }
}
