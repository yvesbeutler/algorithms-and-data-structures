package collections.interfaces;

import java.util.Iterator;

/**
 * @author yvesbeutler
 * Position based multi-way tree. Children are always reached via the iterator interface.
 * @param <E> The type of the objects stored in this tree
 */
public interface Tree<E> {
    /**
     * @return the root Position of this tree (or null)
     */
    Position<E> root();

    /**
     * @param o the object to be stored
     * @return the root Position
     */
    Position<E> createRoot(E o);

    /**
     * @param child a valid position of this tree
     * @return the Position of the parent  of 'child' (or null)
     */
    Position<E> parent(Position<E> child);

    /**
     * @param parent a valid position of this tree
     * @return an Iterator on all children positions belonging to 'parent' in
     * the order they were added
     */
    Iterator<Position<E>> childrenPositions(Position<E> parent);

    /**
     * @param parent a valid position of this tree
     * @return an Iterator on all children elements belonging to 'parent' in
     * the order they were added
     */
    Iterator<E> childrenElements(Position<E> parent);

    /**
     * @param parent a valid position of this tree
     * @return the number of children of 'parent'
     */
    int numberOfChildren(Position<E> parent);

    /**
     * creates a new position which will become the parent of 'p' and
     * the former parent of 'p' will be the parent of the created new position
     * @param p a valid position of this tree
     * @param o the object which will be stored at the position to be created
     * @return the Position where o is stored (as last child)
     */
    Position<E> insertParent(Position<E> p, E o);

    /**
     * @param parent a valid position of this tree
     * @param o the object which will be stored at the position to be created
     * @return the Position where o is stored (as last child)
     */
    Position<E> addChild(Position<E> parent, E o);

    /**
     * @param pos the rank where the new child will be inserted (
     * rank 0 means the first rank in the sequence) The former element
     * at this rank will be shifted by 1.
     * @param parent a valid position of this tree
     * @param o the object which will be stored at the position to be created
     * @return the Position where o is stored
     */
    Position<E> addChildAt(int pos, Position<E> parent, E o);

    /**
     * @param sibling a valid position (not root)
     * after which a new sibling will be stored
     * @param o the object which will be stored at
     * the new Position
     * @return the created position where o is stored
     */
    Position<E> addSiblingAfter(Position<E> sibling, E o);

    /**
     * @param sibling a valid position (not root)
     * before which the new sibling will be stored
     * @param o the object which will be stored at
     * the new Position
     * @return the created position where o is stored
     */
    Position<E> addSiblingBefore(Position<E> sibling, E o);

    /**
     * @param p a (valid) Position to be removed from this tree
     * ( throws an exception if p has children)
     */
    void remove(Position<E> p);

    /**
     * @param p a valid Position belonging to this tree
     * @return true if 'p' has no children
     */
    boolean isExternal(Position<E> p);

    /**
     * @param p a valid Position belonging to this tree
     * @return true if 'p' has children
     */
    boolean isInternal(Position<E> p);

    /**
     * @return the number of elements in this Tree
     */
    int size();

    /**
     * @param p the position where the element will be replaced
     * @param o the new object to replace the former object at 'p'
     * @return the former object stored at 'p'
     */
    E replaceElement(Position<E> p, E o);
}
