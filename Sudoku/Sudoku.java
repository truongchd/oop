import java.util.*;

public class Sudoku {
    private int[][] grid;
    private Square[][] squares;
    private RowCol[] rows;
    private RowCol[] cols;
    // next
    private int[][] tmpGrid;
    private Square[][] tmpSquares;
    private RowCol[] tmpRows;
    private RowCol[] tmpCols;
    private boolean committed;
    // original
    private int[][] originalGrid;

    public Sudoku (int[][] grid) {
        // check if grid has size 9x9

        // initialize
        // this.grid
        this.grid = this.tmpGrid = this.originalGrid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            this.grid[i] = this.tmpGrid[i] = this.originalGrid[i] =  Arrays.copyOfRange(grid[i], 0, 9); 
        }

        // regions
        this.squares = this.tmpSquares = new Square[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.squares[i][j] = this.tmpSquares[i][j] = new Square(this.grid, i, j);
            }
        }

        this.rows = this.tmpRows = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            this.rows[i] = this.tmpRows[i] = new RowCol(this.grid, i, "row");
        }

        this.cols = this.tmpCols = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            this.cols[i] = this.tmpCols[i] = new RowCol(this.grid, i, "col");
        }

        this.committed = true;
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

    public HashSet<Integer> getTmpPossibleValues (int row, int col) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) return null;
        if (this.grid[row][col] != 0) return null;
        HashSet<Integer> res = new HashSet<>();
        res.addAll(this.tmpSquares[row / 3][col / 3].getPossibleValues());
        res.retainAll(this.tmpRows[row].getPossibleValues());
        res.retainAll(this.tmpCols[col].getPossibleValues());
        return res;
    }


    public boolean full() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean modify (int row, int col, int new_val) {
        try {
            if (this.tmpGrid[row][col] == new_val) {
                throw new Exception("New value must be different from old value");
            }

            committed = false;
            this.tmpGrid[row][col] = new_val;
            if (!this.tmpSquares[row / 3][col / 3].modify((row % 3) * 3 + (col % 3), new_val)) {
                if (row == 0 && col == 4) this.printGrid();
                throw new Exception("Error modifying region of squares, index" + row + " " + col);
            }

            if (!this.tmpCols[col].modify(row, new_val)) {
                throw new Exception("Error modifying column index" + col);
            }

            if (!this.tmpRows[row].modify(col, new_val)) {
                throw new Exception("Error modifying row index" + row);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.undo();
            return false;
        }
        return true;
    }

    // solve the problem

    public boolean addAllSquares () {
        // return whether the board can be solve, while also add values in the board
        HashSet<Integer> tmpSet = new HashSet<>();
        boolean modify = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.tmpGrid[i][j] != 0) continue;
                tmpSet.addAll(this.getTmpPossibleValues(i, j));
                modify = false;

                if (tmpSet.size() == 0) {
                    return false;
                    // 
                }

                if (tmpSet.size() == 1) {
                    this.modify(i, j, tmpSet.iterator().next());
                    modify = true;
                }

                if (modify == true) {
                    System.out.println(i + " " + j + " " + tmpSet);
                }
                tmpSet.clear();
            }
        }
        this.printTmpGrid();

        return true;
    }

    public boolean addRandom () {
        boolean state = false;
        committed = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.tmpGrid[i][j] != 0) continue;
                HashSet<Integer> tmpSet = new HashSet<>();
                tmpSet.addAll(this.getTmpPossibleValues(i, j));
                for (int tmpNum: tmpSet) {
                    this.modify(i, j, tmpNum);
                    state = this.solve();
                    if (state == true) {
                        return state;
                    }
                }
            }
        }

        return state;
    }

    public boolean solve () {
        // solve sudoku grid
        if (this.full()) {
            this.commit();
            return true;
        }
        boolean addSquares = this.addAllSquares();
        if (!addSquares) return false;
        boolean addRandom = this.addRandom();
        return addRandom;
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

    public void undo () {
        if (committed == true) return;
        // grid
        this.tmpGrid = new int[9][9]; 
        for (int i = 0; i < 9; i++) {
            this.tmpGrid[i] = Arrays.copyOfRange(this.grid[i], 0, 9);
        }

        // regions
        this.tmpSquares = new Square[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tmpSquares[i][j] = new Square(this.grid, i, j);
            }
        }

        this.tmpRows = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            this.tmpRows[i] = new RowCol(this.grid, i, "row");
        }

        this.tmpCols = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            this.tmpCols[i] = new RowCol(this.grid, i, "col");
        }

        this.committed = true;
    }

    public void commit () {
        if (committed == true) return;
        // grid
        this.grid = new int[9][9]; 
        for (int i = 0; i < 9; i++) {
            this.grid[i] = Arrays.copyOfRange(this.tmpGrid[i], 0, 9);
        }

        // regions
        this.squares = new Square[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.squares[i][j] = new Square(this.tmpGrid, i, j);
            }
        }

        this.rows = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            this.rows[i] = new RowCol(this.tmpGrid, i, "row");
        }

        this.cols = new RowCol[9];
        for (int i = 0; i < 9; i++) {
            this.tmpCols[i] = new RowCol(this.tmpGrid, i, "col");
        }

        this.committed = true;
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


    public void printTmpGrid() {
        System.out.println("----------------");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.tmpGrid[i][j] + "|");
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
