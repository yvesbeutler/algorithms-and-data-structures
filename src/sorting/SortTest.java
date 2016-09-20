package sorting;

import java.util.Random;

/**
 * @author yvesbeutler
 * Demonstrates the use of different sorting algorithms.
 */
public class SortTest {

    private static int counter;
    private static final int N = 10000;

    public static void main(String[] args) {

        long t1=0, t2=0, time=0;
        Random rnd = new Random(Integer.MAX_VALUE);

        // create ordered array of size N
        int[] a = new int[N];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        // mix up the array
        for (int i = 0; i < a.length; i++) {
            swap(a, i, rnd.nextInt(N-1));
        }

        counter = 0;
        t1 = System.currentTimeMillis();

        BubbleSort.sort(a);

        t2 = System.currentTimeMillis();

        time=t2-t1;
        System.out.println("# elements: " + N);
        System.out.println("CPU-Time usage: " + time + " ms");
        System.out.println("Sort-Check: " + sortCheck(a));
        System.out.println("Swap-Number: " + counter);
    }

    /**
     * swap the array elements a[i] and a[k]
     * @param a int array
     * @param i position in the array 'a'
     * @param k position in the array 'a'
     */
     static void swap(int [] a, int i, int k){
        int tmp=a[i];
        a[i]=a[k];
        a[k]=tmp;
        counter++;
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
