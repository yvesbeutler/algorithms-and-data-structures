package collections;

import collections.interfaces.Locator;
import collections.interfaces.Heap;

import java.util.Arrays;

/**
 * @author yvesbeutler
 * Implementation of a Heap using a Locator to store its data. The Heap data structure
 * is sorted along its branches. There are two types of Heaps, the Min- and Max-Heap.
 * The root node is the smallest, so this is a Min-Heap.
 */
public class MyHeap<K extends Comparable<? super K>, E> implements Heap<K, E> {

    private PQLoc<K, E> [] heap = new PQLoc[1];
    private int size;

    // auxiliary class for positions
    private class PQLoc <K1 extends Comparable<? super K1>, E1> implements Locator<K1, E1> {
        K1 key;
        E1 element;
        int position;
        Object creator = MyHeap.this;

        PQLoc(K1 key, E1 element) {
            this.key = key;
            this.element = element;
        }
        @Override
        public E1 getElement() {
            return element;
        }

        @Override
        public K1 key() {
            return key;
        }
    }

    public static void main(String[] args) {
        MyHeap<Integer, String> heap = new MyHeap<>();
        heap.insert(2, "Joris");
        heap.insert(7, "Sascha");
        heap.insert(1, "Joy");
        heap.insert(9, "Mathew");
        heap.insert(4, "Tobi");

        System.out.println(heap.removeMin().getElement());
        System.out.println(heap.removeMin().getElement());

        System.out.println(heap.removeMin().getElement());
        System.out.println(heap.removeMin().getElement());
        System.out.println(heap.removeMin().getElement());
    }

    @Override
    public Locator<K, E> showMin() {
        return heap[1];
    }

    @Override
    public Locator<K, E> removeMin() {
        // set last element as root
        swap(1, size--);

        // sort heap
        downHeap(1);

        return heap[size+1];
    }

    @Override
    public Locator<K, E> insert(K key, E element) {
        // double the storage size
        if (size == heap.length - 1) {
            heap = Arrays.copyOf(heap, 2 * heap.length);
        }

        // create and insert new location
        PQLoc<K, E> location = new PQLoc<>(key, element);
        heap[++size] = location;
        location.position = size;

        // sort the heap
        upHeap(size);

        return location;
    }

    // sort heap upwards
    private void upHeap(int pos) {
        while(pos > 1) {
            int parent = pos/2;
            if (heap[pos].key.compareTo(heap[parent].key) < 0) {
                swap(pos, parent);
                pos = parent;
            } else {
                break;
            }
        }
    }

    // sort heap downwards
    private void downHeap(int pos) {
        int left = pos*2;
        while (left <= size) {
            int right = left + 1;
            int min = left;
            if (right <= size && heap[left].key.compareTo(heap[right].key) > 0) {
                min = right;
            }
            if (heap[pos].key.compareTo(heap[min].key) > 0) {
                swap(pos, min);
                pos = min;
                left = pos*2;
            } else {
                break;
            }
        }
    }

    private void swap(int one, int two) {
        PQLoc<K, E> temp = heap[one];
        heap[one] = heap[two];
        heap[two] = temp;
        heap[one].position = one;
        heap[two].position = two;
    }

    @Override
    public void remove(Locator<K, E> loc) {

    }

    @Override
    public void replaceKey(Locator<K, E> loc, K newKey) {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
