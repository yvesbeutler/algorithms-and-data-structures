package collections;

import collections.interfaces.Locator;
import collections.interfaces.PriorityQueue;

import java.util.Arrays;

/**
 * @author yvesbeutler
 * @since 21.10.2016
 * Basic implementation of a PriorityQueue using a Locator to store its data.
 */
public class MyPriorityQueue<K extends Comparable<? super K>, E> implements PriorityQueue<K, E> {

    private PQLoc<K, E> [] heap = new PQLoc[1];
    private int size;

    // auxiliary class for positions
    class PQLoc <K1 extends Comparable<? super K1>, E1> implements Locator<K1, E1> {
        K1 key;
        E1 element;
        int position;
        Object creator = MyPriorityQueue.this;

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
        MyPriorityQueue<Integer, String> pq = new MyPriorityQueue<>();
        pq.insert(2, "Joris");
        pq.insert(7, "Sascha");
        pq.insert(1, "Joy");
        pq.insert(10, "Mathew");
        pq.insert(4, "Tobi");

        System.out.println(pq.removeMin().getElement());
        System.out.println(pq.removeMin().getElement());
        System.out.println(pq.removeMin().getElement());
        System.out.println(pq.removeMin().getElement());
        System.out.println(pq.removeMin().getElement());
    }

    private PQLoc checkAndCast(Locator<K, E> pos) {
        PQLoc locator;
        try {
            locator = (PQLoc) pos;
        } catch (ClassCastException e) {
            throw new RuntimeException("This Locator doesn't belong to the PriorityQueue");
        }

        if (locator.creator == null) {
            throw new RuntimeException("Locator was already deleted");
        }

        if (locator.creator != this) {
            throw new RuntimeException("Locator doesn't belong to this PriorityQueue instance");
        }

        return locator;
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
