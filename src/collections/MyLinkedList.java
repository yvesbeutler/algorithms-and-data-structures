package collections;

import collections.interfaces.List;
import collections.interfaces.Position;

import java.util.Iterator;

/**
 * @author yvesbeutler
 * Basic implementation of a linked list using an auxiliary class called node.
 */
public class MyLinkedList<E> implements List<E>, Iterable<E> {

    private Node first;
    private Node last;
    private int size;

    // auxiliary class for list positions
    private class Node implements Position<E> {
        E element;
        Node prev, next;
        Object creator = MyLinkedList.this;

        Node(E o) {
            element = o;
        }

        @Override
        public E getElement() {
            return element;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        Position<Integer> pos1 = list.insertFirst(4);
        Position<Integer> pos2 = list.insertFirst(3);
        Position<Integer> pos3 = list.insertFirst(1);
        Position<Integer> pos4 = list.insertLast(5);
        Position<Integer> pos5 = list.insertAfter(pos3, 2);

        // log all current list elements
        Iterator<Position<Integer>> iterator = list.positions();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getElement());
        }
    }

    private MyLinkedList<E>.Node checkAndCast(Position<E> pos) {
        Node node;
        try {
            node = (Node) pos;
        } catch (ClassCastException e) {
            throw new RuntimeException("The position doesn't belong to the type of the linked list");
        }
        if (node.creator == null) {
            throw new RuntimeException("The position was already deleted");
        } else if (node.creator != this) {
            throw new RuntimeException("The position belongs to another linked list");
        }
        return node;
    }

    @Override
    public Position<E> first() {
        return first;
    }

    @Override
    public Position<E> last() {
        return last;
    }

    @Override
    public boolean isFirst(Position<E> pos) {
        return checkAndCast(pos) == first;
    }

    @Override
    public boolean isLast(Position<E> pos) {
        return checkAndCast(pos) == last;
    }

    @Override
    public Position<E> next(Position<E> pos) {
        return checkAndCast(pos).next;
    }

    @Override
    public Position<E> previous(Position<E> pos) {
        return checkAndCast(pos).prev;
    }

    @Override
    public E replaceElement(Position<E> pos, E o) {
        Node node = checkAndCast(pos);
        E element = node.element;
        node.element = o;
        return element;
    }

    @Override
    public Position<E> insertFirst(E o) {
        Node node = new Node(o);
        node.next = first;
        if (first != null) {
            first.prev = node;
        } else {
            last = node;
        }
        first = node;
        size++;
        return node;
    }

    @Override
    public Position<E> insertLast(E o) {
        Node node = new Node(o);
        node.prev = last;
        if (last != null) {
            last.next = node;
        } else {
            first = node;
        }

        last = node;
        size++;
        return node;
    }

    @Override
    public Position<E> insertBefore(Position<E> pos, E o) {
        Node nodePrev = checkAndCast(pos);
        Node node = new Node(o);
        node.next = nodePrev;

        // if previous node was first
        if (nodePrev.prev == null) {
            // set new first node
            first = node;
        } else {
            // insert node before previous
            node.prev = nodePrev.prev;
            nodePrev.prev.next = node;
        }
        nodePrev.prev = node;
        size++;
        return node;
    }

    @Override
    public Position<E> insertAfter(Position<E> pos, E o) {
        Node nodePrev = checkAndCast(pos);
        Node node = new Node(o);
        node.prev = nodePrev;

        // if previous node was last
        if (nodePrev.next == null) {
            // set new last node
            last = node;
        } else {
            // insert node after previous
            node.next = nodePrev.next;
            nodePrev.next.prev = node;
        }
        nodePrev.next = node;
        size++;
        return node;
    }

    @Override
    public void remove(Position<E> pos) {
        Node node = checkAndCast(pos);
        node.creator = null;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            last = node.prev;
        }
        size--;
    }

    @Override
    public Iterator<Position<E>> positions() {
        return new Iterator<Position<E>>() {
            Node current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Position<E> next() {
                Node node = current;
                current = current.next;
                return node;
            }
        };
    }

    @Override
    public Iterator<E> elements() {
        return new Iterator<E>() {
            Node current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return elements();
    }
}