package collections.interfaces;

/**
 * @author yvesbeutler
 * A Locator based Heap which allows to store key - element pairs and allows
 * for O(log n) removal and for O(log n) key replacement and insertion
 * @param <K> The type of the key (has to extend a comparable class)
 * @param <E> The type of the elements stored
 */
public interface Heap<K extends Comparable<? super K>,E> {

    /**
     * @return the Locator with a minimal key of this
     * PriorityQueue (does not remove this Locator from the PriorityQueue)
     */
    Locator<K,E> showMin();

    /**
     * @return the Locator with a minimal key of this
     * PriorityQueue (the Locator is removed from the PriorityQueue)
     */
    Locator<K,E> removeMin();

    /**
     * @return the Locator where  the ('key', 'element') pair is stored
     */
    Locator<K,E> insert(K key, E element);

    /**
     * @param loc a valid locator belonging to this PriorityQueue
     */
    void remove(Locator<K,E> loc);

    /**
     * @param newKey the new key to be stored at 'loc' (the element does not change)
     */
    void replaceKey(Locator<K,E> loc, K newKey );

    /**
     * @return true if there is no (valid) locator in this PriorityQueue
     */
    boolean isEmpty();

    /**
     * @return the number of locators to be stored at this PriorityQueue
     */
    int size();
}