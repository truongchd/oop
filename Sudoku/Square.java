public class Square extends Region {
    public Square (int[][] grid, int indexRow, int indexCol) {
        super(getArray(grid, indexRow, indexCol));
    }

    public static int[] getArray(int[][] grid, int indexRow, int indexCol) {
        try {
            if (grid.length != 9 || grid[0].length != 9) {
                throw new Exception("Size of the grid input must be 9x9");
            }

            int[] res = new int[9];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    res[i * 3 + j] = grid[indexRow * 3 + i][indexCol * 3 + j]; 
                }
            }
            
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }  
}
