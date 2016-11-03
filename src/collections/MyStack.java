package collections;

import collections.interfaces.Stack;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yvesbeutler
 * Basic implementation of a stack using an generic array to store its elements.
 * A comparison between the stack and an array list shows that the stack has got
 * an equal or even better performance than the list.
 */
public class MyStack<E> implements Stack<E> {

    private int counter;
    private int pointer;
    private E[] store = (E[]) new Object[1];

    public static void main(String[] args) {
        final int N = 16 * 1024 * 1024 + 2;
        long start, end;

        // monitor array list
        ArrayList<Integer> list = new ArrayList<>();
        start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        end = System.nanoTime();
        System.out.println("ArrayList:\t store " + N + " elements in " + 1E-9*(end - start) + "s");

        // monitor stack
        MyStack<Integer> stack = new MyStack<>();
        start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            stack.push(i);
        }
        end = System.nanoTime();
        System.out.println("Stack:\t\t store " + N + " elements in " + 1E-9*(end - start) + "s");
        System.out.println();
        System.out.println("Expanded the stack " + stack.counter + " times");
    }

    private void expand() {
        counter++;
        store = Arrays.copyOf(store, store.length * 2);
    }

    @Override
    public void push(E o) {
        if (pointer == store.length) {
            expand();
        }
        store[pointer++] = o;
    }

    @Override
    public E pop() {
        if (pointer == 0) {
            throw new RuntimeException("Can't pop from empty stack");
        }
        return store[--pointer];
    }

    @Override
    public E top() {
        if (pointer == 0) {
            throw new RuntimeException("Can't pop from empty stack");
        }
        return store[pointer - 1];
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }
}
