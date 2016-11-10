package sorting;

/**
 * @author yvesbeutler
 * Mergesort is an efficient comparison-based sorting algorithm. It is stable, that means it preserves
 * the input order of equal elements in the sorted output. It divides the unsorted data into n sublists,
 * each containing only one element and merges them together to finally get one sublist which is sorted.
 * Unlike Quicksort it cannot operate in-place which means that the Mergesort uses an additional dataset.
 * The average complexity of the Mergesort is O(n log(n)).
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
            int middle = (from + to) / 2;
            mSort(a, from, middle);
            mSort(a, middle+1, to);
            merge(a, from, middle, to);
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
