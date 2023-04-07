import java.util.*;

public class NumList {
    SortAlgo sortAlgorithm;
    double[] listOfNum;

    public NumList (double[] initial) {
        this.listOfNum = new double[initial.length];
        this.listOfNum = Arrays.copyOf(initial, 0, initial.length);
    }

    public int add (double num) {
        return 0; // code later
    }

    public double[] getNumList () {
        return this.listOfNum;
    }

    public void setBubleSort () {
        this.sortAlgorithm = new BubleSort(this.listOfNum);
    }

    public void setQuickSort () {
        this.sortAlgorithm = new QuickSort(this.listOfNum);
    }

    public void setOrder () {   
        // code later
    }
}
