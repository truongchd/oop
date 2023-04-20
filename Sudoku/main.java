import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream fileStream = new PrintStream("./output1.txt");
        System.setOut(fileStream);
        Sudoku problem = new Sudoku ("3 7 0 0 0 0 0 8 0", "0 0 1 0 9 3 0 0 0", "0 4 0 7 8 0 0 0 3", "0 9 3 8 0 0 0 1 2", "0 0 0 0 4 0 0 0 0", "5 2 0 0 0 6 7 9 0", "6 0 0 0 2 1 0 4 0", "0 0 0 5 3 0 9 0 0", "0 3 0 0 0 0 0 5 1");
        Sudoku problem1 = new Sudoku ("3 7 0 0 0 0 0 8 0", "0 0 1 0 9 3 0 0 0", "0 4 0 7 8 0 0 0 3", "0 9 3 8 0 0 0 1 2", "0 0 0 2 4 0 0 0 0", "5 2 0 3 1 6 7 9 0", "6 0 0 9 2 1 0 4 0", "0 0 0 5 3 0 9 0 0","0 3 0 0 0 0 0 5 1");
        Sudoku problem2 = new Sudoku (
            "0 0 5 0 0 8 0 9 0", 
            "1 0 0 0 6 0 0 0 7", 
            "0 6 0 4 3 0 0 0 0", 
            "0 0 0 7 0 6 0 0 5", 
            "0 0 9 0 4 0 0 3 0", 
            "0 0 0 0 0 0 0 0 1", 
            "0 0 4 0 1 0 0 0 0", 
            "5 0 0 0 8 0 0 0 0",
            "8 1 0 5 0 3 4 0 0");
        //problem.printGrid();

        System.out.println(problem2.solve());
        problem2.getSolution(null);
        Sudoku tmp = problem2;
        tmp.printGrid();
        while(tmp.getNext() != null) {
            String current = tmp.toString();
            tmp = tmp.getNext();
            String nueva = tmp.toString();
            for (int i = 0; i < current.length(); i+= 2) {
                if (current.charAt(i) != nueva.charAt(i)) {
                    Sudoku.printString(nueva.substring(0, i + 1) + current.substring(i + 1, current.length()));
                }
            }
        }
    }
}