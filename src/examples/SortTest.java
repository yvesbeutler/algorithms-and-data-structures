package examples;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;


// TODO: src/collections ist vom build-prozess ausgeschlossen worden. neu integrieren durch: file > compiler > excludes

/**
 * @author ps
 * Various sort programs for int arrays (exercise)
 */
public class SortTest {

	public static long cnt;
	static Random rand = new Random();
	static int [] b;


	public static void heapSort(int[]a){
		int n = a.length;
		// for (int i=1;i<n;i++) upHeap(a,i);
		for (int i=n/2;i>=0;i--) downHeap(a,i,n);
		for (int i=n-1;i>0;i--){
			swap(a,i,0); // now
			downHeap(a,0,i);
		}
	}

	private static void downHeap(int []a, int pos,int len){
		// assume a[0..len] is a maxHeap with exception
		// of a[pos] which is possible too small.
		// swap a[pos] with the greater of its children until
		// heap condition is ok
		int left = pos*2+1;
		int right = left+1;
		while(left<len){
			int max = left;
			if (right < len && a[left]< a[right]) max=right;
			if (a[pos]>=a[max]) break;
			swap(a,pos,max);
			pos = max;
			left=pos*2+1;
			right=left+1;
		}

	}

	private static void upHeap(int []a, int pos){
		// assume a[0..pos-1] us a maxHeap
		// swap a[pos] with its parent until heap-condition ok
		while (pos > 0){
			int parent = (pos-1)/2;
			if (a[pos] <= a[parent]) break;
			swap(a,pos,parent);
			pos=parent;
		}
	}

	static public boolean checkMaxHeap(int[]a){
		for (int i=1;i<a.length;i++) if (a[i]>a[(i-1)/2]) return false;
		return true;
	}
	/**
	 * @param a int aray
	 * @return 'true' if 'a' is sorted
	 */
	public static boolean sortCheck(int[] a) {
		for (int i=0;i<a.length-1;i++){
			if (a[i]>a[i+1]) return false;
		}
		return true;
	}

	public static void mergeSort(int [] a){
		b = new int[a.length];
		mSort(a,0,a.length-1);
	}

	private static void mSort(int[] a, int from, int to) {
		if (from==to) return;
		int med = (from+to)/2;
		mSort(a,from,med);
		mSort(a,med+1,to);
		merge(a,from,med,to);
	}

	private static void merge(int[] a, int from, int med, int to) {
		// precondition:
		// a[from..med] and a[med+1..to] are already sorted
		// create a sorted sequence in b[from..to]
		int left=from,right=med+1,i=from;
		while (left<=med){
			if (right>to){
				// copy the rest of the first section
				while(left<=med) b[i++]=a[left++];
				break;
			}
			// take the smaller of the two candidates:
			else if (a[left]<=a[right]) b[i++]=a[left++];
			else b[i++]=a[right++];
		}
		// copy b[from..to] back to a[from..to]
		while(--i>= from){
			a[i]=b[i];
		}
	}

	/**
	 * Non optimized bubble sort for an int array
	 * @param a
	 */
	public static void bubbleSort(int[] a) {
		cnt=0;
		int m = a.length-1;
		for(int i=m; i>0; i--){

			for (int k=0; k < i; k++){
				if(a[k]>a[k+1]) swap(a,k,k+1);
			}
			// now a[i] is on its final position!
		}
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
		cnt++;
	}

	public static void main(String[] args) {
		long t1=0,t2=0,te1=0,te2=0,eTime=0,time=0;
		int n = 10000000;
		// we need a random generator
		Random rand=new Random(Integer.MAX_VALUE);
		//rand.setSeed(54326346); // initialize always in the same state
		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
		// new array
		int [] a = new int[n];
		// fill it randomly
		for (int i=0;i<a.length ;i++) {
			a[i]=i;// rand.nextInt(n);
		}

		// mix: a litle bit
		for (int i=0;i<a.length ;i++) {
			swap(a,i,rand.nextInt(n-1));
		}

		cnt=0;  // for statistcs reasons
		// get Time
		te1=System.nanoTime();
		t1 = threadBean.getCurrentThreadCpuTime();
		heapSort(a);
//		System.out.println(checkMaxHeap(a));
		te2 = System.nanoTime();
		t2 = threadBean.getCurrentThreadCpuTime();
		time=t2-t1;
		eTime=te2-te1;
		System.out.println("# elements: "+n);
		System.out.println("CPU-Time usage: "+time/1000000.0+" ms");
		System.out.println("elapsed time: "+eTime/1e6+" ms");
		System.out.println("sorted? "+sortCheck(a));
		System.out.println("swap operation needed: "+cnt);
		// ok
	}


}
