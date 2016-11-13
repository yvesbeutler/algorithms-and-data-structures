package sorting;

/**
 * @author yvesbeutler
 * Quicksort is an efficient sorting algorithm which is based on partitioning arrays of data
 * into two smaller arrays which are less and greater than a specific value called pivot. The
 * pivot can be randomly chosen, mostly it's the first, last or the middle element of an array.
 * Quicksort is called recursively on its subarrays and is quite efficient for large datasets
 * with an average complexity of O(n log(n)). Worst case is if the pivot will be the smallest
 * or largest element of the array, so that one subarray has the length of zero. Then its
 * complexity can as slow as O(n^2).
 */
class QuickSort {

    static int[] sort(int[] a) {
        quickSort(a, 0, a.length-1);
        return a;
    }

    private static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int pivot = partition(a, left, right);
            quickSort(a, left, pivot);
            quickSort(a, pivot+1, right);
        }
    }

    static int partition(int[] a, int left, int right) {
        int pivot = a[left];
        int i = left-1;
        int j = right+1;

        while (true) {
            i++;
            j--;
            while (i < right && a[i] < pivot) {
                i++;
            }
            while (j > left && a[j] > pivot) {
                j--;
            }

            if (i < j) {
                swap(a, i, j);
            } else {
                return j;
            }
        }
    }

    private static void swap(int[] a, int i, int k){
        int tmp=a[i];
        a[i]=a[k];
        a[k]=tmp;
    }

}