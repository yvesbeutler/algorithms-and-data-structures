package sorting;

/**
 * @author yvesbeutler
 * Bubblesort is a simple sorting algorithm which is comparison-based. This means it compares
 * each element with the element right next to it and swaps them if they are not in order. This
 * algorithm isn't suitable for larger datasets because its average complexity is of O(n^2).
 */
class BubbleSort {

    static int[] sort(int [] a) {
        for (int n = a.length - 1; n > 0; n--){
            for (int j = 0; j < n; j++){
                if(a[j] > a[j+1]) {
                    swap(a, j, j+1);
                }
            }
        }
        return a;
    }

    private static void swap(int[] a, int i, int k){
        int tmp=a[i];
        a[i]=a[k];
        a[k]=tmp;
    }
}
