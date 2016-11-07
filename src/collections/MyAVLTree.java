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

    private Node checkAndCast(Locator<K,E> pos) {
        Node node;
        try {
            node = (Node) pos;
        } catch (ClassCastException e) {
            throw new RuntimeException("The locator doesn't belong to this LinkedList instance");
        }
        if (node.creator == null) throw new RuntimeException("The locator was already deleted");
        if (node.creator != this) throw new RuntimeException("The locator belongs to another LinkedList instance");
        return node;
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

    private void adjustHeightAboveAndBalance(Node node) {
        node = node.parent;

        while (node != null) {
            // get new height
            int h = 1 + Math.max(node.left.height, node.right.height);
            boolean balanced = Math.abs(node.left.height - node.right.height) < 2;

            if (node.height == h && balanced) {
                return;
            }

            // set new height
            node.height = h;

            if (!balanced) {
                // adjust structure
                node = restructure(node);
            }

            node = node.parent;
        }
    }

    private Node restructure(Node node) {

        Node parent = node.parent;
        Node x, y, z = node;
        Node a, b, c, t1, t2;

        // if left subtree > right subtree
        if (z.left.height > z.right.height) {

            c = z;
            y = z.left;

            // check if single or double rotation
            if (y.left.height > z.right.height) {
                x = y.left;
                t1 = x.right;
                t2 = y.right;
                b = y;
                a = x;
            } else {
                // double rotation
                x = y.right;
                t1 = x.left;
                t2 = x.right;
                a = y;
                b = x;
            }
        }

        // if right subtree > left subtree
        else {

            a = z;
            y = z.right;

            // check if single or double rotation
            if (y.right.height >= y.left.height) {
                x = y.right;
                b = y;
                c = x;
                t1 = y.left;
                t2 = x.left;
            } else {
                x = y.left;
                b = x;
                c = y;
                t1 = x.left;
                t2 = x.right;
            }
        }

        // switch nodes
        b.parent = parent;
        if (parent != null) {
            if (parent.left == z) {
                parent.left = b;
            } else {
                parent.right = b;
            }
        } else {
            root = b;
        }

        b.right = c;
        b.left = a;
        a.parent = b;
        c.parent = b;

        // for subtree
        a.right = t1;
        t1.parent = a;
        c.left = t2;
        t2.parent = c;

        // calculate new heights
        a.height = Math.max(a.left.height, a.right.height) + 1;
        c.height = Math.max(c.left.height, c.right.height) + 1;
        b.height = Math.max(b.left.height, b.right.height) + 1;

        return b;
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

        // set new height and balance
        adjustHeightAboveAndBalance(node);

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
        Node node = checkAndCast(loc);
        // next is above
        if (node.right.isExternal()) {
            // find first parent on right side
            while (node.isRightChild()) {
                node = node.parent;
            }
            node = node.parent;
        } else {
            // next is in right subtree
            node = node.right;
            // find most left
            while (! node.left.isExternal()) {
                node = node.left;
            }
        }
        return node;
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
