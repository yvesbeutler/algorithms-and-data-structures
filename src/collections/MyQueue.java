package collections;

import collections.interfaces.Queue;

/**
 * @author yvesbeutler
 * Basic implementation of a queue without the use of a linked list. It uses an array to store the elements
 * and if the array is completely full, the expand() method doubles the size of the array.
 */
public class MyQueue<E> implements Queue<E> {

    private E[] store = (E[]) new Object[1];
    private int in;
    private int out;
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
            out = (out==store.length) ? 0 : out;
            newStore[i] = store[out++];
        }

        // init variables
        in = size;
        out = 0;
        store = newStore;
    }

    @Override
    public void enqueue(E o) {
        if(size == store.length) {
            expand();
        }

        in = (in == store.length) ? 0 : in;
        store[in++] = o;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty!");
        }

        out = (out == store.length) ? 0 : out;
        size--;
        return store[out++];
    }

    @Override
    public E head() {
        if (!isEmpty() && out < size) {
            return store[out];
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
