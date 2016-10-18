package collections;

import collections.interfaces.Position;
import collections.interfaces.Tree;
import java.util.Iterator;

/**
 * @author yvesbeutler
 * @since 17.10.2016
 * Basic implementation of a tree using a linked list to store all of its children.
 */
public class MyTree<E> implements Tree<E> {

    private Node root;
    private int size;
    private int maxLevel;
    private Position deepest;

    // auxiliary class for positions
    class Node implements Position<E> {
        E element;
        Node parent;
        MyLinkedList<Node> children = new MyLinkedList<>();
        Position<Node> childPosition;
        Object creator = MyTree.this;

        @Override
        public E getElement() {
            return element;
        }

        Node(E o) {
            element = o;
        }
    }

    public static void main(String[] args) {
        MyTree<String> tree = new MyTree<>();
        Position<String> pos1 = tree.createRoot("Book");
        Position<String> pos2 = tree.addChild(pos1, "Chapter 1");
        Position<String> pos3 = tree.addChild(pos1, "Chapter 2");
        Position<String> pos4 = tree.addChild(pos1, "Chapter 3");
        Position<String> pos5 = tree.addChild(pos3, "Chapter 2.1");
        tree.print();

        // display deepest position
        System.out.println("Deepest position: " + tree.deepestPosition().getElement());

        // remove subtree
        tree.removeSubtree(pos3);

        // display deepest position
        System.out.println("Deepest position: " + tree.deepestPosition().getElement());
    }

    private Node checkAndCast(Position<E> pos) {
        Node node;
        try {
            node = (Node) pos;
        } catch (ClassCastException e) {
            throw new RuntimeException("Position doesn't belong to the tree");
        }
        if (node.creator == null) {
            throw new RuntimeException("Position was already deleted");
        } else if (node.creator != this) {
            throw new RuntimeException("Position belongs to another tree instance");
        }
        return node;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> createRoot(E o) {
        root = new Node(o);
        size++;
        return root;
    }

    @Override
    public Position<E> parent(Position<E> child) {
        return checkAndCast(child).parent;
    }

    @Override
    public Iterator<Position<E>> childrenPositions(Position<E> parent) {
        Node node = checkAndCast(parent);
        return new Iterator<Position<E>>() {
            Iterator<Node> iterator = node.children.elements();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Position<E> next() {
                return iterator.next();
            }
        };
    }

    @Override
    public Iterator<E> childrenElements(Position<E> parent) {
        return null;
    }

    @Override
    public int numberOfChildren(Position<E> parent) {
        return 0;
    }

    @Override
    public Position<E> insertParent(Position<E> p, E o) {
        return null;
    }

    @Override
    public Position<E> addChild(Position<E> parent, E o) {
        Node nodeParent = checkAndCast(parent);
        Node node = new Node(o);
        node.parent = nodeParent;
        size++;
        node.childPosition = nodeParent.children.insertLast(node);
        return node;
    }

    @Override
    public Position<E> addChildAt(int pos, Position<E> parent, E o) {
        return null;
    }

    @Override
    public Position<E> addSiblingAfter(Position<E> sibling, E o) {
        return null;
    }

    @Override
    public Position<E> addSiblingBefore(Position<E> sibling, E o) {
        return null;
    }

    @Override
    public void remove(Position<E> pos) {
        // TODO: implement remove method
        // Node node = checkAndCast(pos);
        // node.parent.children.remove(pos);
    }

    @Override
    public boolean isExternal(Position<E> p) {
        return false;
    }

    @Override
    public boolean isInternal(Position<E> p) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E replaceElement(Position<E> p, E o) {
        return null;
    }

    private void print() {
        print(root, "");
    }

    private void print(Node node, String index) {
        System.out.println(index + node.element);
        for (Node n : node.children) {
            print(n, index + "--|");
        }
    }

    private void removeSubtree(Position<E> pos) {
        Node node = checkAndCast(pos);
        Iterator<Node> iterator = node.children.elements();
        while (iterator.hasNext()) {
            remove(iterator.next());
        }
        remove(node);
    }

    private Position deepestPosition() {
        maxLevel = 0;
        deepest = null;
        if (size() != 0) {
            deepestNode(this, root(), 1);
        }
        return deepest;
    }

    private void deepestNode(Tree tree, Position pos, int level) {
        if (level > maxLevel) {
            maxLevel = level;
            deepest = pos;
        }

        Iterator<Position> iterator = tree.childrenPositions(pos);
        while (iterator.hasNext()) {
            deepestNode(tree, iterator.next(), level+1);
        }
    }

}
