import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream fileStream = new PrintStream("./output1.txt");
        System.setOut(fileStream);
        Sudoku problem = new Sudoku ("3 7 0 0 0 0 0 8 0", "0 0 1 0 9 3 0 0 0", "0 4 0 7 8 0 0 0 3", "0 9 3 8 0 0 0 1 2", "0 0 0 0 4 0 0 0 0", "5 2 0 0 0 6 7 9 0", "6 0 0 0 2 1 0 4 0", "0 0 0 5 3 0 9 0 0", "0 3 0 0 0 0 0 5 1");
        Sudoku problem1 = new Sudoku ("3 7 0 0 0 0 0 8 0", "0 0 1 0 9 3 0 0 0", "0 4 0 7 8 0 0 0 3", "0 9 3 8 0 0 0 1 2", "0 0 0 2 4 0 0 0 0", "5 2 0 3 1 6 7 9 0", "6 0 0 9 2 1 0 4 0", "0 0 0 5 3 0 9 0 0","0 3 0 0 0 0 0 5 1");
        //problem.printGrid();

        System.out.println(problem.solve());
        System.out.println(problem.getSolution(null));
    }
}