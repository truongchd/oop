public class Main {
    public static void main (String[] args) {
        double[] num = new double[5];
        num[0] = 10.0;
        num[1] = 13.0;
        num[2] = 9.5;
        num[3] = 1.4;
        num[4] = 5.5;
        NumList numList = new NumList(num);
        numList.setSort(new QuickSort());
        for (int i = 0; i < numList.getNumList().length; i++) {
            System.out.println(numList.getNumList()[i]);
        }
        System.out.println("---------------");
        numList.setOrder();
        for (int i = 0; i < numList.getNumList().length; i++) {
            System.out.println(numList.getNumList()[i]);
        }
    }
}
