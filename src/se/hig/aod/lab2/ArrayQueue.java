package se.hig.aod.lab2;

/**
 * A queue that uses an fixed-size array to store element
 * 
 * 
 * @param <V> type to store
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class ArrayQueue<V> implements Queue<V>
{
    int maxSize;
    int insertAt;
    int takeAt;
    boolean full = false;
    Object[] array;

    /**
     * Create ArrayQueue of specified size
     * 
     * @param maxSize the size of the ArrayQueue
     */
    public ArrayQueue(int maxSize)
    {
        array = new Object[maxSize];
        this.maxSize = maxSize;
    }

    @Override
    public void clear()
    {
        array = new Object[maxSize];
        full = false;
        insertAt = 0;
        takeAt = 0;
    }

    @Override
    public boolean isEmpty()
    {
        return !full && insertAt == takeAt;
    }

    /**
     * Check if the queue is full
     * 
     * @return true if the queue is full, else false
     */
    public boolean isFull()
    {
        return full;
    }

    @Override
    public void enqueue(V v)
    {
        if (isFull())
            throw new QueueIsFullException("Can't put stuff in a full queue!");

        array[insertAt] = v;
        insertAt = (insertAt + 1) % maxSize;
        if (insertAt == takeAt)
            full = true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V dequeue()
    {
        if (isEmpty())
            throw new QueueIsEmptyException("Can't take stuff from an empty queue!");

        V toTake = (V) array[takeAt];
        takeAt = (takeAt + 1) % maxSize;
        full = false;
        return toTake;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public V getFront()
    {
        if (isEmpty())
            throw new QueueIsEmptyException("Can't get front of an empty queue!");

        return (V) array[takeAt];
    }

    /**
     * Exception that signals that the queue is full
     * 
     * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
     */
    @SuppressWarnings("serial")
    public static class QueueIsFullException extends RuntimeException
    {
        /**
         * Construct
         * 
         * @param message
         *            that describes the error
         */
        public QueueIsFullException(String message)
        {
            super(message);
        }
    }

    /**
     * Exception that signals that the queue is empty
     * 
     * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
     */
    @SuppressWarnings("serial")
    public static class QueueIsEmptyException extends RuntimeException
    {
        /**
         * Construct
         * 
         * @param message
         *            that describes the error
         */
        public QueueIsEmptyException(String message)
        {
            super(message);
        }
    }
}
