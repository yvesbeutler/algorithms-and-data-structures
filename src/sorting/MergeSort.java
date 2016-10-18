package sorting;

/**
 * @author yvesbeutler
 * @since 20.09.2016
 * Demonstrates the merge (or often called quick) sort algorithm.
 */
class MergeSort {

    private static int[] b;

    static int[] sort(int [] a) {
        b = new int[a.length];
        mSort(a, 0, a.length-1);
        return a;
    }

    private static void mSort(int[] a, int from, int to) {
        if (from != to) {
            int med = (from + to) / 2;
            mSort(a, from, med);
            mSort(a, med+1, to);
            merge(a, from, med, to);
        }
    }

    private static void merge(int[] a, int from, int med, int to) {
        int left = from;
        int right = med + 1;
        int i = from;

        while(true) {
            // first part already finished?
            if (left > med) {
                break;
            }
            // second part already finished?
            else if (right > to) {
                while (left <= med) {
                    b[i++] = a[left++];
                }
                break;
            } else {
                // copy smallest of two candidates
                if (a[left] <= a[right]) {
                    b[i++] = a[left++];
                } else {
                    b[i++] = a[right++];
                }
            }
        }

        // copy back
        while(--i >= from) {
            a[i] = b[i];
        }
    }

}
