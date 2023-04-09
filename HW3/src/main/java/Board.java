// Board.java

/**
 * CS108 Tetris Board.
 * Represents a Tetris board -- essentially a 2-d grid
 * of booleans. Supports tetris pieces and row clearing.
 * Has an "undo" feature that allows clients to add and remove pieces efficiently.
 * Does not do any drawing or have any idea of pixels. Instead,
 * just represents the abstract 2-d board.
 */
import java.util.*;

public class Board {
    // Some ivars are stubbed out for you:
    private int width;
    private int height;
    private int[] heights;
    private int[] widths;
    private int maxHeight;
    private boolean[][] grid;
    private int[] heights_backup;
    private int[] widths_backup;
    private int maxHeight_backup;
    private boolean[][] grid_backup;
    private boolean DEBUG = true;
    boolean committed;


    // Here a few trivial methods are provided:

    /**
     * Creates an empty board of the given width and height
     * measured in blocks.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new boolean[width][height];
        committed = true;

        // YOUR CODE HERE
        this.widths = new int[height];
        this.heights = new int[width];
        this.maxHeight = 0;
        this.widths_backup = new int[height];
        this.heights_backup = new int[width];
        this.maxHeight_backup = 0;
        this.grid_backup = new boolean[width][height];
    }


    /**
     * Returns the width of the board in blocks.
     */
    public int getWidth() {
        return width;
    }


    /**
     * Returns the height of the board in blocks.
     */
    public int getHeight() {
        return height;
    }


    /**
     * Returns the max column height present in the board.
     * For an empty board this is 0.
     */
    public int getMaxHeight() {
        
        return this.maxHeight; // YOUR CODE HERE
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
        int pos = 0;
        for (int i = 0; i < skirt.length; i++) {
            pos = Math.max(pos, this.heights[x + i] - skirt[i]);
        }
        return pos; // YOUR CODE HERE
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
        return this.widths[y]; // YOUR CODE HERE
    }


    /**
     * Returns true if the given block is filled in the board.
     * Blocks outside of the valid width/height area
     * always return true.
     */
    public boolean getGrid(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return true;
            // out of bounds
        } else if (this.grid[x][y]) {
            return true;
            // grid filled
        }
        return false; // YOUR CODE HERE
    }


    public static final int PLACE_OK = 0;
    public static final int PLACE_ROW_FILLED = 1;
    public static final int PLACE_OUT_BOUNDS = 2;
    public static final int PLACE_BAD = 3;

    /**
     * Attempts to add the body of a piece to the board.
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

        // BAD/OUT BOUNDS
        TPoint[] piece_body = piece.getBody();
        for (int i = 0; i < piece_body.length; i++) {
            if (piece_body[i].x + x < 0 || piece_body[i].y + y < 0 || piece_body[i].x + x >= this.width || piece_body[i].y + y >= this.height) {
                return PLACE_OUT_BOUNDS;
            }
            if (this.grid[piece_body[i].x + x][piece_body[i].y + y] == true) {
                return PLACE_BAD;
            }
        }

        // ROW_FILLED
        committed = false; // change board

        for (int i = 0; i < piece_body.length; i++) {
            this.grid[piece_body[i].x + x][piece_body[i].y + y] = true;
            this.maxHeight = Math.max(this.maxHeight, piece_body[i].y + y + 1);
            this.heights[piece_body[i].x + x] = Math.max(this.heights[piece_body[i].x + x] , piece_body[i].y + y + 1);
            this.widths[piece_body[i].y + y]++; 
            if (this.widths[piece_body[i].y + y] == this.width) {
                result = PLACE_ROW_FILLED;
            }
        }

        return result;
    }


    /**
     * Deletes widths that are filled all the way across, moving
     * things above down. Returns the number of widths cleared.
     */
    public int clearRows() {
        int widthsCleared = 0, i = 0;
        boolean have;
        while (i < this.maxHeight) {
            have = false;
            while (true) {
                for(boolean b : grid[i]) {
                    if (b) {
                        have = true;
                        break;
                    }
                }
                if (have == true) {
                    break;
                }
                widthsCleared++;
                i++;
            }
            if (widthsCleared == 0) continue;
            this.grid[i - widthsCleared] = this.grid[i];
            this.widths[i - widthsCleared] = this.widths[i];
            i++;
        }
        for (int j = this.maxHeight - widthsCleared; j < this.maxHeight; j++ ) {
            this.widths[j] = 0;
            this.grid[j] = new boolean[this.width];
        }
        for (int j = 0; j < this.width; j++) {
            this.heights[j] -= widthsCleared;
        }

        if (widthsCleared > 0) {
            this.maxHeight_backup = this.maxHeight;
            this.maxHeight -= widthsCleared;
            committed = false;
        }

        sanityCheck();
        return widthsCleared;
    }

    /**
     * Reverts the board to its state before up to one place
     * and one clearwidths();
     * If the conditions for undo() are not met, such as
     * calling undo() twice in a row, then the second undo() does nothing.
     * See the overview docs.
     */
    public void undo() {
        if (committed) return; // committed state don't have backup
        // widths and heights
        this.heights = Arrays.copyOfRange(this.heights_backup, 0, this.heights_backup.length);
        this.widths = Arrays.copyOfRange(this.widths_backup, 0, this.widths_backup.length);
        // grid
        for (int i = 0; i < this.width; i++) {
            this.grid[i] = this.grid_backup[i];
        }
        // maxHeight
        this.maxHeight = this.maxHeight_backup;
        committed = true;
    }


    /**
     * Puts the board in the committed state.
     */
    public void commit() {
        if (committed) return;
        // widths and heights
        this.heights_backup = Arrays.copyOfRange(this.heights, 0, this.heights.length);
        this.widths_backup = Arrays.copyOfRange(this.widths, 0, this.widths.length);
        // grid
        for (int i = 0; i < this.width; i++) {
            this.grid_backup[i] = this.grid[i];
        }
        // maxHeight
        this.maxHeight_backup = this.maxHeight;
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
        Piece ptr1 = ptr.computeNextRotation();
        Piece ptr2 = ptr1.computeNextRotation();
        Piece ptr3 = ptr2.computeNextRotation();
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