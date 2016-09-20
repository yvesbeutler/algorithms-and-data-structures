package sorting;

import static sorting.SortTest.swap;

/**
 * @author yvesbeutler
 * Demonstrates the bubble sort algorithm.
 */
class BubbleSort {

    static int[] sort(int [] a) {
        int m = a.length-1;
        for(int i=m; i>0; i--){
            for (int k=0; k < i; k++){
                if(a[k]>a[k+1]) swap(a,k,k+1);
            }
        }
        return a;
    }
}
