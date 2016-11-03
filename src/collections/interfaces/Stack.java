package collections.interfaces;

/**
 * @author yvesbeutler
 * @param <E> the type of the objects in this stack
 */
public interface Stack<E> {

    /**
     * Puts an object (on top) of this stack
     * @param o The object to be put to this stack
     */
    void push(E o);

    /**
     * @return (and remove) the last Object which was put to this stack
     */
    E pop();

    /**
     * @return the last Object which was put to this stack without removing it
     */
    E top();

    /**
     * @return the number of elements currently on this stack
     */
    int size();

    /**
     * @return true if there is no element on this stack
     */
    boolean isEmpty();

}