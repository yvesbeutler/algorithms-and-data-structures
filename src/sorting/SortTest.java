package sorting;

import java.util.Random;

/**
 * @author yvesbeutler
 * @since 19.09.2016
 * Demonstrates the use of different sorting algorithms.
 */
class SortTest {

    static int counter;
    private static final int N = 10000;

    public static void main(String[] args) {

        long t1, t2, time;
        Random rnd = new Random(Integer.MAX_VALUE);

        // create ordered array of size N
        int[] a = new int[N];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        // mix up the array
        for (int i = 0; i < a.length; i++) {
            BubbleSort.swap(a, i, rnd.nextInt(N-1));
        }

        counter = 0;
        t1 = System.currentTimeMillis();

        BubbleSort.sort(a);
        //MergeSort.sort(a);

        t2 = System.currentTimeMillis();

        time=t2-t1;
        System.out.println("# elements: " + N);
        System.out.println("CPU-Time usage: " + time + " ms");
        System.out.println("Sort-Check: " + sortCheck(a));
        System.out.println("Swap-Number: " + counter);
    }

    /**
     * @param a int array
     * @return 'true' if 'a' is sorted
     */
    private static boolean sortCheck(int[] a) {
        for (int i=0;i<a.length-1;i++){
            if (a[i]>a[i+1]) return false;
        }
        return true;
    }

}
