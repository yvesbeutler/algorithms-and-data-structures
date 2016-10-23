package collections.interfaces;

/**
 * @author yvesbeutler
 * @since 21.10.2016
 * Locators allow to store (key,Object) pairs, where the keys have
 * to implement the Comparable interface.
 * @param <K> The type of the key (which has to implement Comparable)
 * @param <E> The type of the element stored at this position
 */
public interface Locator<K extends Comparable<? super K>,E>
        extends Position<E> {
    /**
     * @return the key object stored at this locator
     */
    public K key();
}