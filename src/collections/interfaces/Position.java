package collections.interfaces;

import java.io.Serializable;

/**
 * @author yvesbeutler
 * @since 30.09.2016
 * Position objects give the user immediate access to an object stored in a position based data collection.
 * Classes that implement this interface are usually auxiliary classes of the concrete data collection class.
 */
public interface Position<E> extends Serializable {
    public E getElement();
}