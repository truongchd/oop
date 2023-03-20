// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int rows = this.grid.length;
		int cols = this.grid[0].length;
		int lw = 0, rw = 0, th = 0, bh = 0;
		boolean init = false;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == ch) {
					if (init == false) {
						init = true;
						lw = i;
						rw = i;
						th = j;
						bh = j;
					} else {
						lw = Math.min(lw, i);
						rw = Math.max(rw, i);
						th = Math.min(th, j);
						bh = Math.max(bh, j);
					}
				}
			} 
		}
		int area = (rw - lw + 1) * (bh - th + 1);
		return area; // YOUR CODE HERE
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */

	public int countPlus() {
		
		int rows = this.grid.length;
		int cols = this.grid[0].length;
		boolean[][] visited = new boolean[rows][cols];
		int total = 0;
		for (int row = 0; row < rows - 1; row++) {
			for (int col = 0; col < cols - 1; col++) {
				if (visited[row][col] == true) continue;
				if (this.grid[row][col] == ' ') continue;
				if (this.grid[row][col] == this.grid[row + 1][col]) {
					int counter = 0;
					while (row + counter < rows - 1 && this.grid[row + counter][col] == this.grid[row + counter + 1][col]) {
						counter++;
					}
					if (counter % 2 == 0 && counter >= 2) {
						counter /= 2;
						if (col - counter >= 0 && col + counter < cols) {
    						int have = 1;
    						for (int k = col - counter; k < col + counter; k++) {
    							if (this.grid[row + counter][k] != this.grid[row + counter][k + 1]) {
    								have = 0;
    								break;
    							}
    						}
    						if (have == 1){
    						    for (int k = row; k <= row + (counter * 2); k++) {
    						        visited[k][col] = true;
    						    }
    						    for (int k = col - counter; k <= col + counter; k++) {
    						        visited[row + counter][k] = true;
    						    }
    						    total++;
    						}   
						}
					}
				}
			}
		}
		return total; // YOUR CODE HERE
	}
	
}
