package collections.interfaces;

import java.io.Serializable;

/**
 * @author yvesbeutler
 * Position objects give the user immediate access to an object stored in a position based data collection.
 * Classes that implement this interface are usually auxiliary classes of the concrete data collection class.
 */
public interface Position<E> extends Serializable {
    E getElement();
}