package collections;

import collections.interfaces.Locator;
import collections.interfaces.OrderedDictionary;

import java.util.Iterator;

/**
 * @author yvesbeutler
 * Basic implementation of a search tree.
 */
public class MyAVLTree<K extends Comparable<? super K>, E> implements OrderedDictionary<K, E> {

    private Node root;
    private int size;

    private class Node implements Locator<K, E> {
        K key;
        E element;
        int height;
        Node parent, left, right;
        Object creator = MyAVLTree.this;

        Node() {}

        Node(K key, E element) {
            this.key = key;
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }

        @Override
        public K key() {
            return key;
        }

        boolean isExternal() {
            return left == null;
        }

        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        boolean isRightChild() {
            return parent != null && parent.right == this;
        }

        void expand(K key, E element) {
            this.key = key;
            this.element = element;
            left = new Node();
            right = new Node();
            left.parent = this;
            right.parent = this;
            height = 1;
        }

    }

    public static void main(String[] args) {
        MyAVLTree<Integer, String> avl = new MyAVLTree<>();
        avl.addRoot(4, "");
        avl.insert(7, "Sascha");
        avl.insert(2, "Joy");
        avl.insert(5, "Joris");
        avl.printKeys();
    }

    private void addRoot(K key, E element) {
        root = new Node(key, element);
        root.left = new Node();
        root.right = new Node();
    }

    private void printKeys() {
        System.out.println("size: " + size());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Locator<K, E> find(K key) {
        return null;
    }

    @Override
    public Locator<K, E>[] findAll(K key) {
        return null;
    }

    @Override
    public Locator<K, E> insert(K key, E o) {
        Node node = root;

        while (!node.isExternal()) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        // add content to former external node
        node.expand(key, o);

        size++;
        return node;
    }

    @Override
    public void remove(Locator<K, E> loc) {

    }

    @Override
    public Locator<K, E> closestBefore(K key) {
        return null;
    }

    @Override
    public Locator<K, E> closestAfter(K key) {
        return null;
    }

    @Override
    public Locator<K, E> next(Locator<K, E> loc) {
        return null;
    }

    @Override
    public Locator<K, E> previous(Locator<K, E> loc) {
        return null;
    }

    @Override
    public Locator<K, E> min() {
        return null;
    }

    @Override
    public Locator<K, E> max() {
        return null;
    }

    @Override
    public Iterator<Locator<K, E>> sortedLocators() {
        return null;
    }
}
