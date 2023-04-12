import java.util.Arrays;

public class RowCol extends Region {
    public RowCol (int[][] grid, int indexRowCol, String rowColState) {
        super(getArray(grid, indexRowCol, rowColState));
    }

    public static int[] getArray(int[][] grid, int indexRowCol, String rowColState) {
        try {
            if (grid.length != 9 || grid[0].length != 9) {
                throw new Exception("Size of the grid input must be 9x9");
            }

            int[] res = new int[9];
            if (rowColState == "row") {
                res = Arrays.copyOfRange(grid[indexRowCol], 0, 9);
            } else {
                for (int i = 0 ; i < 9; i++) {
                    res[i] = grid[i][indexRowCol];
                }
            }
            
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }  
}
