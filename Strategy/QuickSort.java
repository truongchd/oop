import java.util.*;
import java.util.concurrent.TimeUnit;

class QuickSort implements SortAlgo {
    private void sorting (double[] arr, int low, int high) {
        if (low >= high) return;

        double pivot = arr[high], tmp;
        int pos = low;

        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                if (pos != i) {
                    tmp = arr[i];
                    arr[i] = arr[pos];
                    arr[pos] = tmp;
                }
                pos++;
            }
        }
        if (pos == high) {
            sorting(arr, low, high - 1);
            return;
        }

        tmp = arr[pos];
        arr[pos] = arr[high];
        arr[high] = tmp;
        sorting(arr, low, pos);
        sorting(arr, pos + 1, high);
    }

    public void sort (NumList numList) {
        double[] newList = numList.getNumList();
        this.sorting(newList, 0, newList.length - 1);
        numList.modifyList(newList);
    }
}
