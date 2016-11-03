package collections.interfaces;

/**
 * @author yvesbeutler
 * @param <E> type of the elements stored in the queue
 */
public interface Queue<E>{
    /**
     * add object to the tail of the queue
     */
    void enqueue(E o);

    /**
     * return object at the head of the queue and remove it
     */
    E dequeue();

    /**
     * return object at the head of the queue but don't remove it
     */
    E head();

    /**
     * return the number of elements on the queue
     */
    int size();

    /**
     * return true if the queue is empty
     */
    boolean isEmpty();

}

