import java.util.Arrays;

class BubleSort implements SortAlgo {
    public void sort (NumList numList) {
        boolean done = false;
        double[] newList = new double[numList.getNumList().length];
        newList = Arrays.copyOfRange(numList.getNumList(), 0, numList.getNumList().length);
        double tmp;
        while (!done) {
            done = true;
            for (int i = 0; i < newList.length - 1; i++) {
                boolean current = (newList[i] < newList[i + 1]);
                if (current) continue;
                tmp = newList[i];
                newList[i] = newList[i + 1];
                newList[i + 1] = tmp;
                done = false;
            }
        }
        numList.modifyList(newList);
    }
}
