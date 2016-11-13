package sorting;

/**
 * @author yvesbeutler
 * Quickselect is a selection algorithm which is based on Quicksort. It is able to find the n-smallest
 * element in an array of unsorted data. Unless Quicksort it just recursivly sorts the one partition where
 * the wanted element is in. The average performance is half of Quicksort which means that complexity is
 * about O(n) and worst-case is O(n^2). After the n-smallest element is selected, the unsorted input is now
 * partitially sorted.
 */
class QuickSelect {

    private static int N = 0;

    static int select(int[] a, int rank) {
        quickSelect(a, 0, a.length-1, rank);
        return N;
    }

    private static void quickSelect(int[] a, int left, int right, int rank) {
        int pivot = QuickSort.partition(a, left, right);
        if (a[pivot] == rank) {
            N = pivot;
        } else if (pivot < rank) {
            quickSelect(a, pivot+1, right, rank);
        } else {
            quickSelect(a, left, pivot-1, rank);
        }
    }
}