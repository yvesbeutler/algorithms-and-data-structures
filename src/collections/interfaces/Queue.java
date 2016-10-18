package collections.interfaces;

/**
 * @author yvesbeutler
 * @since 14.10.2016
 * @param <E> type of the elements stored in the queue
 */
public interface Queue<E>{
    /**
     * add object to the tail of the queue
     */
    public void enqueue(E o);

    /**
     * return object at the head of the queue and remove it
     */
    public E dequeue();

    /**
     * return object at the head of the queue but don't remove it
     */
    public E head();

    /**
     * return the number of elements on the queue
     */
    public int size();

    /**
     * return true if the queue is empty
     */
    public boolean isEmpty();

    /**
     * prints the current state of the queue
     */
    public void log();
}

