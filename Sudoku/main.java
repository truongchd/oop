import java.util.*;

public class main {
    public static void main(String[] args) {
        Sudoku problem = new Sudoku ("3 7 0 0 0 0 0 8 0", "0 0 1 0 9 3 0 0 0", "0 4 0 7 8 0 0 0 3", "0 9 3 8 0 0 0 1 2", "0 0 0 0 4 0 0 0 0", "5 2 0 0 0 6 7 9 0", "6 0 0 0 2 1 0 4 0", "0 0 0 5 3 0 9 0 0", "0 3 0 0 0 0 0 5 1");
        //problem.printGrid();
        Rows first = problem.getRegion(0, 0);
        Rows row1 = problem.getRowCol(0, "row");
        Set<Integer> row1Set = row1.getPossibleValues();
        problem.printGrid();
    }
}