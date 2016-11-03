package collections.interfaces;

import java.util.Iterator;

/**
 * @author yvesbeutler
 * @param <E> type of the elements stored in the list
 */
public interface List<E>{
    /**
     * @return the first position of this list or 'null'
     */
    Position<E> first();

    /**
     * @return the last position of this list or 'null'
     */
    Position<E> last();

    /**
     * @param p a position of this list
     * @return true if 'p' is the first position of this list
     */
    boolean isFirst(Position<E> p);

    /**
     * @param p a position of this list
     * @return true if 'p' is the last position of this list
     */
    boolean isLast(Position<E> p);

    /**
     * @param p a position of this list
     * @return the positionj after 'p' or 'null'
     */
    Position<E> next(Position<E> p);

    /**
     * @param p a position of this list
     * @return the position before 'p' or 'null
     */
    Position<E> previous(Position<E> p);

    /**
     * @param p the poition where the element will be replaced
     * @param o the new object to replace the former object at 'p'
     * @return the former object stored at 'p'
     */
    E replaceElement(Position<E> p, E o);

    /**
     * @param o the object which will be inserted before position 'p'
     * @return the new Position object where 'o' is stored
     */
    Position<E> insertFirst(E o);

    /**
     * @param o the object which will be inserted after the last position
     * @return the new Position object where 'o' is stored
     */
    Position<E> insertLast(E o);

    /**
     * @param p the position which will be after the element to be stored
     * @param o the object to be stored before the position 'p'
     * @return the new position containing the object 'o'
     */
    Position<E> insertBefore(Position<E> p, E o);

    /**
     * @param p the position which will be before the element to be stored
     * @param o the object to be stored after the position 'p'
     * @return the new position containing the object 'o'
     */
    Position<E> insertAfter(Position<E> p, E o);

    /**
     * @param p the position to be removed from this list
     */
    void remove(Position<E> p);

    /**
     * @return an iterator over all positions of this list in proper order
     */
    Iterator<Position<E>> positions();

    /**
     * @return an iterator over all objects stored in this list in proper order
     */
    Iterator<E> elements();

    /**
     * @return the number of elements currently in this list
     */
    int size();

    /**
     * @return true if there is no element in this list
     */
    boolean isEmpty();

}
