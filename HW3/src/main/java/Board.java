import java.util.Arrays;

// Board.java

/**
 * CS108 Tetris Board.
 * Represents a Tetris board -- essentially a 2-d grid
 * of booleans. Supports tetris pieces and row clearing.
 * Has an "undo" feature that allows clients to add and remove pieces efficiently.
 * Does not do any drawing or have any idea of pixels. Instead,
 * just represents the abstract 2-d board.
 */
public class Board {
    // Some ivars are stubbed out for you:
    private int width;
    private int height;
    private boolean[][] grid;
    private boolean DEBUG = true;
    private int[] widths;
    private int[] heights;
    private int maxHeight;

    // backup stuffs
    boolean committed;
    private int[] widths_backup;
    private int[] heights_backup;
    private boolean[][] grid_backup;
    private int maxHeight_backup;

    // Here a few trivial methods are provided:

    /**
     * Creates an empty board of the given width and height
     * measured in blocks.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new boolean[width][height];
        this.widths = new int[height];
        this.heights = new int[width];

        // backup
        this.widths_backup = new int[height];
        this.heights_backup = new int[width];
        this.grid_backup = new boolean[width][height];
        for(int i = 0; i < this.width; i ++){
            this.grid_backup[i] = null;
        }
        this.maxHeight_backup = -1;
        this.committed = true;
        // YOUR CODE HERE
    }


    /**
     * Returns the width of the board in blocks.
     */
    public int getWidth() {
        return this.width;
    }


    /**
     * Returns the height of the board in blocks.
     */
    public int getHeight() {
        return this.height;
    }


    /**
     * Returns the max column height present in the board.
     * For an empty board this is 0.
     */
    public int getMaxHeight() {
        return this.maxHeight;
    }


    /**
         * Checks the board for internal consistency -- used
         * for debugging.
     */
    public void sanityCheck() {
        if (DEBUG) {
            // check width
            int totalBlock = 0;
            for (int i = 0; i < this.height; i++) {
                totalBlock = 0;
                for (int j = 0; j < this.width; j++) {
                    if (this.grid[j][i]) {
                        totalBlock++;
                    }
                }
                if (totalBlock != this.widths[i]) {
                    throw new RuntimeException("Wrong width at row " + i + ": expected " + totalBlock + ", get " + this.widths[i]);
                }
            }
            // check height + maxHeight
            int realMaxHeight = 0, currentMaxHeight = 0;
            for (int i = 0; i < this.width; i++) {
                currentMaxHeight = 0;
                for (int j = this.height - 1; j >= 0; j--) {
                    if (this.grid[i][j]) {
                        realMaxHeight = Math.max(realMaxHeight, j + 1);
                        currentMaxHeight = j + 1;
                        break;
                    }
                }
                if (currentMaxHeight != this.heights[i]) {
                    throw new RuntimeException("Wrong height at column " + i + ": expected " + currentMaxHeight + ", get " + this.heights[i]);
                }
            }
            if (this.maxHeight != realMaxHeight) {
                throw new RuntimeException("Wrong maxHeight: expected " + currentMaxHeight + ", get " + this.maxHeight);
            }
        }
    }

    /**
     * Given a piece and an x, returns the y
     * value where the piece would come to rest
     * if it were dropped straight down at that x.
     *
     * <p>
     * Implementation: use the skirt and the col heights
     * to compute this fast -- O(skirt length).
     */
    public int dropHeight(Piece piece, int x) {
        int[] skirt = piece.getSkirt();
        int ans = 0;
        for(int i = 0; i < skirt.length; i ++)
            ans = Math.max(ans, this.heights[x+i]-skirt[i]);
        return ans; // YOUR CODE HERE
    }


    /**
     * Returns the height of the given column --
     * i.e. the y value of the highest block + 1.
     * The height is 0 if the column contains no blocks.
     */
    public int getColumnHeight(int x) {
        return this.heights[x]; // YOUR CODE HERE
    }


    /**
     * Returns the number of filled blocks in
     * the given row.
     */
    public int getRowWidth(int y) {
        return this.widths[y];
    }


    /**
     * Returns true if the given block is filled in the board.
     * Blocks outside of the valid width/height area
     * always return true.
     */
    public boolean getGrid(int x, int y) {
        if(Math.min(x, y) >= 0 && x < this.width && y < this.height && !this.grid[x][y])
            return false;
        return true; // YOUR CODE HERE
    }


    public static final int PLACE_OK = 0;
    public static final int PLACE_ROW_FILLED = 1;
    public static final int PLACE_OUT_BOUNDS = 2;
    public static final int PLACE_BAD = 3;

    /**
     * Attempts to add the piece_body of a piece to the board.
     * Copies the piece blocks into the board grid.
     * Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
     * for a regular placement that causes at least one row to be filled.
     *
     * <p>Error cases:
     * A placement may fail in two ways. First, if part of the piece may falls out
     * of bounds of the board, PLACE_OUT_BOUNDS is returned.
     * Or the placement may collide with existing blocks in the grid
     * in which case PLACE_BAD is returned.
     * In both error cases, the board may be left in an invalid
     * state. The client can use undo(), to recover the valid, pre-place state.
     */
    public int place(Piece piece, int x, int y) {
        // flag !committed problem
        if (!committed) throw new RuntimeException("place commit problem");
        
        int result = PLACE_OK;
        TPoint[] piece_body = piece.getBody();
        int put_x, put_y;
        for(int i = 0; i < piece_body.length; i ++){
            put_x = x + piece_body[i].x;
            put_y = y + piece_body[i].y;
            if (put_x < 0 || put_x >= this.width || put_y < 0 || put_y >= this.height) {
                return PLACE_OUT_BOUNDS;
            }
            if (this.grid[put_x][put_y]) {
                return PLACE_BAD;
            }
        }

        this.committed = false;
        for (int i = 0; i < piece_body.length; i++) {
            put_x = piece_body[i].x + x;
            put_y = piece_body[i].y + y;

            // height + widths
            this.heights[put_x] = Math.max(this.heights[put_x], put_y + 1);
            this.widths[put_y] += 1; 

            // maxHeight + backup
            if (this.heights[put_x] > this.maxHeight) {
                if(this.maxHeight_backup == -1){
                   this.maxHeight_backup = this.maxHeight; 
                }
                this.maxHeight = this.heights[put_x];
            }

            // grid + backup
            if (this.grid_backup[put_x] == null) {
                this.grid_backup[put_x] = Arrays.copyOfRange(this.grid[put_x], 0, this.grid[put_x].length);
            }
            this.grid[put_x][put_y] = true;

            // check row filled
            if (this.getRowWidth(put_y) == this.width) {
                result = PLACE_ROW_FILLED;
            }
        }

        return result;
    }

    /**
     * Deletes rows that are filled all the way across, moving
     * things above down. Returns the number of rows cleared.
     */
	public void shiftRow(int col) {
		int rows = this.grid.length, cols = this.grid[0].length;
		if (col >= cols || col < 0) return; 
        // shift row i + 1 to row i 
		for (int i = col; i < cols - 1; i++) {
			for (int j = 0; j < rows; j++) {
				this.grid[j][i] = this.grid[j][i + 1];
			}
            this.widths[i] = this.widths[i + 1];
		}

        // false top row 
		for (int i = 0; i < rows; i++) {
			this.grid[i][cols - 1] = false;
		}
        this.widths[cols - 1] = 0;
	}
	
	public boolean checkFull(int col){
		int rows = this.grid.length, cols = this.grid[0].length;
		if (col >= cols) return false;
		for (int i = 0; i < rows; i++){
			if (this.grid[i][col] == false) return false;
		}
		return true;
	}

	public int clearRows() {
        int rowsCleared = 0;
		int cols = this.grid[0].length;
		for (int i = 0; i < cols; i++) {
			if (checkFull(i) == true) {
                rowsCleared++;
                if (rowsCleared == 1) {
                    // backup before change
                    for (int j = 0; j < this.width; i++) {
                        if (this.grid_backup[j] == null)
                            this.grid_backup[j] = Arrays.copyOfRange(this.grid[j], 0, this.grid[j].length);
                    }
                }
				shiftRow(i);
			}
		}

        if(rowsCleared > 0){
            // backup maxHeight
            if (this.maxHeight_backup == -1) {
                this.maxHeight_backup = this.maxHeight;
            }            
            this.maxHeight -= rowsCleared;

            // change heights            
            for (int i = 0; i < this.width; i++) {
                this.heights[i] = 0;
                for (int j = 0; j < this.height; j++) {
                    if (this.grid[i][j] == true) {
                        this.heights[i]++;
                    }
                }
            }

            // uncommitted 
            this.committed = false;
        }
        
        this.sanityCheck();
        return rowsCleared;

	}

    /**
     * Reverts the board to its state before up to one place
     * and one clearRows();
     * If the conditions for undo() are not met, such as
     * calling undo() twice in a row, then the second undo() does nothing.
     * See the overview docs.
     */
    public void undo() {
        if (this.committed) return;

        // copy widths and height
        this.widths = Arrays.copyOfRange(this.widths_backup, 0, this.widths.length);
        this.heights = Arrays.copyOfRange(this.heights_backup, 0, this.heights.length);

        // copy to grid and maxHeight empty grid and maxHeight backup
        for(int i = 0; i < this.width; i ++){
            if (this.grid_backup[i] != null) {
                this.grid[i] = Arrays.copyOfRange(this.grid_backup[i], 0, this.grid_backup[i].length);
            }
            this.grid_backup[i] = null;
        }


        if(this.maxHeight_backup != -1){
            this.maxHeight = this.maxHeight_backup;
        }
        this.maxHeight_backup = -1;

        this.committed = true;
    }


    /**
     * Puts the board in the committed state.
     */
    public void commit() {
        if (committed) return;

        // copy to backup
        this.widths_backup = Arrays.copyOfRange(this.widths, 0, this.widths.length);
        this.heights_backup = Arrays.copyOfRange(this.heights, 0, this.heights.length);

        // empty grid and maxHeight
        for (int i = 0; i < this.width; i++) {
            this.grid_backup[i] = null;
        }
        this.maxHeight_backup = -1;


        committed = true;
    }


    /*
     Renders the board state as a big String, suitable for printing.
     This is the sort of print-obj-state utility that can help see complex
     state change over time.
     (provided debugging utility)
     */
    public String toString() {
        StringBuilder buff = new StringBuilder();
        for (int y = height - 1; y >= 0; y--) {
            buff.append('|');
            for (int x = 0; x < width; x++) {
                if (getGrid(x, y)) buff.append('+');
                else buff.append(' ');
            }
            buff.append("|\n");
        }
        for (int x = 0; x < width + 2; x++) buff.append('-');
        return (buff.toString());
    }
    public static void main(String[] arg){
        // first test
        Board b = new Board(3, 6);
        
        Piece ptr = new Piece(Piece.PYRAMID_STR);
        /* Piece ptr1 = ptr.computeNextRotation();
        Piece ptr2 = ptr1.computeNextRotation();
        Piece ptr3 = ptr2.computeNextRotation();*/
        System.out.println(b.place(ptr, 0, 0));
        for(int i = 0; i < b.getWidth(); i ++)
            System.out.println("column " + i + ": " + b.getColumnHeight(i));
        for (int i = 0; i < b.getHeight(); i++)
            System.out.println("row " + i + ": " + b.getRowWidth(i));
        System.out.println(b.getMaxHeight());
        System.out.println(b.toString());
        // second test
        b.commit();
        Piece s = new Piece(Piece.S1_STR);
        Piece sRotated = s.computeNextRotation();
        int result = b.place(sRotated, 1, 1);
        System.out.println(result);
        for(int i = 0; i < b.getWidth(); i ++)
            System.out.println("column " + i + ": " + b.getColumnHeight(i));
        for (int i = 0; i < b.getHeight(); i++)
            System.out.println("row " + i + ": " + b.getRowWidth(i));
        System.out.println(b.getMaxHeight());
        System.out.println(b.toString());
    }
}