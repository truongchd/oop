//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {
	private boolean grid[][];
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void shiftRow(int col) {
		int rows = this.grid.length, cols = this.grid[0].length;
		if (col >= cols) return;
		for (int i = col; i < cols - 1; i++) {
			for (int j = 0; j < rows; j++) {
				this.grid[j][i] = this.grid[j][i + 1];
			}
		}
		for (int i = 0; i < rows; i++) {
			this.grid[i][cols - 1] = false;
		}
	}
	
	public boolean checkFull(int col){
		int rows = this.grid.length, cols = this.grid[0].length;
		if (col >= cols) return false;
		for (int i = 0; i < rows; i++){
			if (this.grid[i][col] == false) return false;
		}
		return true;
	}

	public void clearRows() {
		int cols = this.grid[0].length;
		for (int i = 0; i < cols; i++) {
			if (checkFull(i) == true) {
				shiftRow(i);
			}
		}
	}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		clearRows();
		return this.grid; // YOUR CODE HERE
	}

	public static void main(String[] args) {
          
        boolean[][] before =
		{	
			{true, true, false, },
			{false, true, true, }
		};
		TetrisGrid a = new TetrisGrid(before);
		a.clearRows();
		for (int i = 0;i < 2;i++)
		{
			for (int j = 0;j < 3;j++) 
				System.out.print(a.grid[i][j] + " ");
			System.out.println();
		}
	}
}
