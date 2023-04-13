import java.util.*;

public class Sudoku {
    private int[][] grid;
    private Square[][] squares;
    private RowCol[] rows;
    private RowCol[] cols;
    // original
    private int[][] originalGrid;
    private int[][] nextGrid;
    private String solution;
    private Sudoku next;

    public Sudoku (int[][] grid) {
        // check if grid has size 9x9

        // initialize
        this.solution = "";
        // this.grid
        this.grid = this.nextGrid = this.originalGrid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            this.grid[i] = this.nextGrid[i] = this.originalGrid[i] =  Arrays.copyOfRange(grid[i], 0, 9); 
        }

        // regions
        this.squares = new Square[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.squares[i][j] = this.squares[i][j] = new Square(this.grid, i, j);
            }
        }

        this.rows = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            this.rows[i] = this.rows[i] = new RowCol(this.grid, i, "row");
        }

        this.cols = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            this.cols[i] = this.cols[i] = new RowCol(this.grid, i, "col");
        }
    }

    public Sudoku (String gridString) {
        this(parseString(gridString));
    }

    public Sudoku (String... gridStrings) {
        this(parseStrings(gridStrings));
    }

    public Square getSquare(int row, int col) {
        return this.squares[row][col];    
    }

    public RowCol getRowCol(int rowCol, String state) {
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
        res.addAll(this.squares[row / 3][col / 3].getPossibleValues());
        res.retainAll(this.rows[row].getPossibleValues());
        res.retainAll(this.cols[col].getPossibleValues());
        return res;
    }

    public String getSolution (Sudoku nextSudoku) {
        if (this.solution != "") return this.solution; 
        if (this.full() ) {
            this.solution = this.toString();
        } else {
            this.solution = nextSudoku.getSolution(nextSudoku.getNext()); 
        }

        return this.solution;
    }

    public Sudoku getNext() {
        return this.next;
    }

    public boolean full() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j] == 0) return false;
            }
        }
        this.printGrid();
        return true;
    }

    public boolean modify (int row, int col, int new_val) {
        try {
            if (this.grid[row][col] == new_val) {
                throw new Exception("New value must be different from old value");
            }

            this.grid[row][col] = new_val;
            if (!this.squares[row / 3][col / 3].modify((row % 3) * 3 + (col % 3), new_val)) {
                throw new Exception("Error modifying region of squares, index" + row + " " + col);
            }

            if (!this.cols[col].modify(row, new_val)) {
                throw new Exception("Error modifying column index" + col);
            }

            if (!this.rows[row].modify(col, new_val)) {
                throw new Exception("Error modifying row index" + row);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    // solve the problem
    private static int BOARD_INSOLUBLE = 0;
    private static int BOARD_AVAILABLE = 1;
    private static int BOARD_FILLED = 2;

    public int addAllSquares () {
        // return whether the board can be solve, while also add values in the board
        HashSet<Integer> tmpSet = new HashSet<>();
        int modify = BOARD_FILLED;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j] != 0) continue;
                tmpSet.addAll(this.getPossibleValues(i, j));
                //System.out.println("square " + i + " " + j + " " + tmpSet);
                if (tmpSet.size() == 0) {
                    System.out.println(i + " " + j) ;
                    return BOARD_INSOLUBLE;
                    // 
                }

                if (tmpSet.size() == 1) {
                    if (!this.modify(i, j, tmpSet.iterator().next())) return BOARD_INSOLUBLE;
                    modify = BOARD_AVAILABLE;
                    System.out.println(tmpSet + " " + i + " " + j) ;
                }

                tmpSet.clear();
            }
        }

        return modify;
    }

    public boolean addNext() {
        boolean added = false;
        int row = -1, col = -1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j] != 0) continue;
                row = i;
                col = j;
                break;
            }
            if (added) break;
        }
        HashSet<Integer> tmpSet = new HashSet<>();
        tmpSet.addAll(this.getPossibleValues(row, col));
        for (int tmpInt: tmpSet) {
            this.nextGrid[row][col] = tmpInt;
            Sudoku tmpSudoku = new Sudoku(this.nextGrid);
            if (tmpSudoku.solve()) {
                this.next = tmpSudoku;
                this.getSolution(tmpSudoku);
                return true;
            }
        }
        return false;
    }

    public boolean solve () {
        // solve sudoku grid
        if (this.full()) {
            return true;
        }
        int addOnes = this.addAllSquares();
        while (addOnes == BOARD_AVAILABLE) {
            addOnes = this.addAllSquares();
        }
        if (addOnes == BOARD_INSOLUBLE) return false;
        if (this.full()) {
            return true;
        }
        return this.addNext();
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
        System.out.println("****************");
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