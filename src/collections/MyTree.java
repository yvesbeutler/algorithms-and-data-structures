package collections;

import collections.interfaces.Position;
import collections.interfaces.Tree;
import java.util.Iterator;

/**
 * @author yvesbeutler
 * This implementation of a Tree is using a Linked List to connect each node with its parent
 * and its children. It's not a binary tree so it can have more than two child nodes.
 */
public class MyTree<E> implements Tree<E> {

    private Node root;
    private int size;
    private int maxLevel;
    private Position deepest;

    // auxiliary class for positions
    private class Node implements Position<E> {
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
        Position<String> root = tree.createRoot("Book");
        Position<String> pos2 = tree.addChild(root, "Chapter 1");
        Position<String> pos3 = tree.addChild(root, "Chapter 2");
        Position<String> pos4 = tree.addChild(root, "Chapter 3");
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
        Node node = checkAndCast(parent);
        return new Iterator<E>() {
            Iterator<Node> iterator = node.children.elements();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                return iterator.next().getElement();
            }
        };
    }

    @Override
    public int numberOfChildren(Position<E> parent) {
        Node node = checkAndCast(parent);
        return node.children.size();
    }

    @Override
    public Position<E> insertParent(Position<E> pos, E o) {
        Node node = checkAndCast(pos);
        Node nodeParent = new Node(o);

        if (node == root) {
            root = nodeParent;
        } else {

            // new parent takes the former role of node
            nodeParent.parent = node.parent;
            nodeParent.childPosition = node.childPosition;
            nodeParent.parent.children.replaceElement(nodeParent.childPosition, nodeParent);
        }

        // add node as a child of the new parent
        node.parent = nodeParent;
        node.childPosition = nodeParent.children.insertFirst(node);
        size++;

        return nodeParent;
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
        Node nodeParent = checkAndCast(parent);

        if (pos > nodeParent.children.size() || pos < 0) {
            throw new RuntimeException("Can't insert child on an invalid rank");
        }

        Node node = new Node(o);
        node.parent = nodeParent;

        Position<Node> linkedListPosition;
        if (pos == 0) {
            linkedListPosition = nodeParent.children.insertFirst(node);
        } else if (pos == nodeParent.children.size()) {
            linkedListPosition = nodeParent.children.insertLast(node);
        } else {
            Iterator<Position<Node>> iterator = nodeParent.children.positions();
            // skip pos-2 nodes
            for (int i = 0; i < pos-1; i++) {
                iterator.next();
            }

            // create position before the insertion point
            Position<Node> prevPos = iterator.next();
            linkedListPosition = nodeParent.children.insertAfter(prevPos, node);
        }

        node.childPosition = linkedListPosition;
        size++;

        return node;
    }

    @Override
    public Position<E> addSiblingAfter(Position<E> sib, E o) {
        Node sibling = checkAndCast(sib);
        if (sib == root) {
            throw new RuntimeException("Root can't have siblings");
        }

        Node node = new Node(o);
        node.parent = sibling.parent;
        node.childPosition = sibling.parent.children.insertAfter(sibling.childPosition, node);
        size++;

        return node;
    }

    @Override
    public Position<E> addSiblingBefore(Position<E> sib, E o) {
        Node sibling = checkAndCast(sib);
        if (sib == root) {
            throw new RuntimeException("Root can't have siblings");
        }

        Node node = new Node(o);
        node.parent = sibling.parent;
        node.childPosition = sibling.parent.children.insertBefore(sibling.childPosition, node);
        size++;

        return node;
    }

    @Override
    public void remove(Position<E> pos) {
        Node node = checkAndCast(pos);
        if (node.children.size() != 0) {
            throw new RuntimeException("Can't remove node with children");
        }

        node.creator = null;
        size--;

        if (node == root) {
            root = null;
        } else {
            node.parent.children.remove(node.childPosition);
        }
    }

    @Override
    public boolean isExternal(Position<E> pos) {
        Node node = checkAndCast(pos);
        return node.children.size() == 0;
    }

    @Override
    public boolean isInternal(Position<E> pos) {
        Node node = checkAndCast(pos);
        return node.children.size() != 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E replaceElement(Position<E> pos, E o) {
        Node node = checkAndCast(pos);
        E element = node.getElement();
        node.element = o;
        return element;
    }

    private void print() {
        if (size != 0) {
            print(root, "");
        }
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
