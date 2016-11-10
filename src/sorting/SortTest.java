package sorting;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Random;

/**
 * @author yvesbeutler
 * Demonstrates the runtime of different sorting algorithms.
 */
class SortTest {

    // number of elements to sort
    private static final int N = 40000;

    public static void main(String[] args) {

        int[] a = createRandomArray();
        int[] b = Arrays.copyOf(a, a.length);
        int[] c = Arrays.copyOf(a, a.length);

        long t0, t1, t2, t3;
        ThreadMXBean thread = ManagementFactory.getThreadMXBean();

        t0 = thread.getCurrentThreadCpuTime();
        BubbleSort.sort(a);
        t1 = thread.getCurrentThreadCpuTime() - t0;

        t0 = thread.getCurrentThreadCpuTime();
        MergeSort.sort(b);
        t2 = thread.getCurrentThreadCpuTime() - t0;

        t0 = thread.getCurrentThreadCpuTime();
        QuickSort.sort(c);
        t3 = thread.getCurrentThreadCpuTime() - t0;

        // print results
        System.out.println("number of elements:\t" + N);
        System.out.println();
        System.out.println("algorithm\t|\tvalid\t|\ttime [ms]");
        System.out.println("------------|-----------|------------");
        System.out.println("bubblesort\t|\t" + sortCheck(a) + "\t|\t" + t1 / 1e6);
        System.out.println("mergesort\t|\t" + sortCheck(b) + "\t|\t" + t2 / 1e6);
        System.out.println("quicksort\t|\t" + sortCheck(c) + "\t|\t" + t3 / 1e6);
    }

    private static int[] createRandomArray() {

        // create ordered array of size N
        int[] array = new int[N];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        // mix up the array
        final Random rnd = new Random(Integer.MAX_VALUE);
        for (int i = 0; i < array.length; i++) {
            swap(array, i, rnd.nextInt(N-1));
        }
        return array;
    }

    private static boolean sortCheck(int[] a) {
        for (int i=0;i<a.length-1;i++){
            if (a[i]>a[i+1]) return false;
        }
        return true;
    }

    private static void swap(int[] a, int i, int k){
        int tmp=a[i];
        a[i]=a[k];
        a[k]=tmp;
    }

}
