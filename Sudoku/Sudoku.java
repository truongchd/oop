import java.util.*;

public class Sudoku {
    private int[][] grid;
    private Square[][] regions;
    private RowCol[] rows;
    private RowCol[] cols;

    public Sudoku (int[][] grid) {
        // check if grid has size 9x9

        // initialize
        this.grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            this.grid[i] = Arrays.copyOfRange(grid[i], 0, 9);
        }

        regions = new Square[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                regions[i][j] = new Square(this.grid, i, j);
            }
        }

        rows = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new RowCol(this.grid, i, "row");
        }

        cols = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            cols[i] = new RowCol(this.grid, i, "col");
        }
    }

    public Sudoku (String gridString) {
        this(parseString(gridString));
    }

    public Sudoku (String... gridStrings) {
        this(parseStrings(gridStrings));
    }

    public boolean solve () {
        // solve sudoku grid
        return false;
    }

    public int[][] getGrid () {
        return this.grid;
    }

    public String toString() {
        // return String of board, using StringBuilder
        return null;
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
