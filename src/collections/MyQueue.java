package collections;

import collections.interfaces.Queue;

/**
 * @author yvesbeutler
 * Basic implementation of a queue without the use of a linked list. It uses an array to store the elements
 * and if the array is completely full, the expand() method doubles the size of the array. The FIFO principle
 * is implemented by two pointers. One points behind the last element, the other points at the first element.
 */
public class MyQueue<E> implements Queue<E> {

    private E[] store = (E[]) new Object[1];
    private int pointerIn;
    private int pointerOut;
    private int size;

    public static void main(String[] args) {
        Queue<String> queue = new MyQueue<>();
        queue.enqueue("Tobias");
        queue.enqueue("Joris");
        queue.enqueue("Joy");

        System.out.println("remove:\t" + queue.dequeue());
        System.out.println("remove:\t" + queue.dequeue());
        queue.enqueue("Sascha");
        System.out.println("remove:\t" + queue.dequeue());
        System.out.println("remove:\t" + queue.dequeue());
    }

    /**
     * Doubles the size of the array if it's full and copies the old values into the new one.
     */
    private void expand() {
        E[] newStore = (E[]) new Object[store.length*2];
        // copy values into new array
        for (int i=0; i < size; i++) {
            pointerOut = (pointerOut == store.length) ? 0 : pointerOut;
            newStore[i] = store[pointerOut++];
        }

        // init variables
        pointerIn = size;
        pointerOut = 0;
        store = newStore;
    }

    @Override
    public void enqueue(E o) {
        if(size == store.length) {
            expand();
        }

        pointerIn = (pointerIn == store.length) ? 0 : pointerIn;
        store[pointerIn++] = o;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty!");
        }

        pointerOut = (pointerOut == store.length) ? 0 : pointerOut;
        size--;
        return store[pointerOut++];
    }

    @Override
    public E head() {
        if (!isEmpty() && pointerOut < size) {
            return store[pointerOut];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

}
