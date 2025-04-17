
/**
 * Implementation of a Queue. Oldest element will be removed first (FIFO)
 * @param <E> Element type
 */

// implementing Iterable is part of task 32, but keep this here anyway.
// the skeleton has a ready "implementation"
public interface TRAI_24_X7<E> extends  Iterable<E>   {

    /**
     * Adds an element to tail of the queue.
     *
     * @param x element to add
     * @return true
     */
    public boolean add(E x);

    /**
     * Removes and returns the head of the queue
     * @return element that was removed
     * @throws java.util.NoSuchElementException if queue was empty
     */
    public E remove();

    /**
     * Is queue empty or not?
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Empties the queue.
     * This can be made more efficiently in the implementing class as soon
     * as the implementation is ready.
     * Not need to do it.
     */
    public default void clear() {
        while (! isEmpty())
            remove();
    }
}
