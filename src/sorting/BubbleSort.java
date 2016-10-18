package sorting;

/**
 * @author yvesbeutler
 * @since 19.09.2016
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

    static void swap(int[] a, int i, int k){
        int tmp=a[i];
        a[i]=a[k];
        a[k]=tmp;
        SortTest.counter++;
    }

}
