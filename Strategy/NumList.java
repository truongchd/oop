import java.util.*;

public class NumList {
    SortAlgo sortAlgorithm;
    double[] listOfNum;

    public NumList (double[] initial) {
        this.listOfNum = new double[initial.length];
        this.listOfNum = Arrays.copyOfRange(initial, 0, initial.length);
        this.sortAlgorithm = new BubleSort();
    }

    public int add (double num) {
        return 0; // code later
    }

    public double[] getNumList () {
        return this.listOfNum;
    }

    public void modifyList (NumList modifier) {
        double[] modify = modifier.getNumList();
        this.listOfNum = new double[modify.length];
        this.listOfNum = Arrays.copyOfRange(modify, 0, modify.length);
    }

    public void modifyList (double[] modifier) {
        this.listOfNum = new double[modifier.length];
        this.listOfNum = Arrays.copyOfRange(modifier, 0, modifier.length);
    }

    public void setSort (SortAlgo sortAlgo) {
        this.sortAlgorithm = sortAlgo;
    }

    public void setOrder () {   
        this.sortAlgorithm.sort(this);
    }
}
