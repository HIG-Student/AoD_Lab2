package se.hig.aod.lab2;

/**
 * A stack that uses an fixed-size array to store element
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class ArrayStack<V> implements Stack<V>
{
    int maxSize;
    int size;
    Object[] array;

    public ArrayStack(int maxSize)
    {
        this.maxSize = maxSize;
        array = new Object[maxSize];
    }

    @Override
    public void clear()
    {
        array = new Object[maxSize];
        size = 0;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public boolean isFull()
    {
        return size == maxSize;
    }

    @Override
    public void push(V v)
    {
        if (isFull())
            throw new StackIsFullException("Can't push stuff to a full stack!");

        array[size++] = v;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V pop()
    {
        if (isEmpty())
            throw new StackIsEmptyException("Can't pop stuff from an empty stack!");

        return (V) array[size-- - 1];
    }

    @SuppressWarnings("unchecked")
    @Override
    public V top()
    {
        if (isEmpty())
            throw new StackIsEmptyException("Can't top on an empty stack!");

        return (V) array[size - 1];
    }

    /**
     * Exception that signals that the stack is full
     * 
     * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
     */
    @SuppressWarnings("serial")
    public static class StackIsFullException extends RuntimeException
    {
        /**
         * Construct
         * 
         * @param message
         *            that describes the error
         */
        public StackIsFullException(String message)
        {
            super(message);
        }
    }

    /**
     * Exception that signals that the stack is empty
     * 
     * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
     */
    @SuppressWarnings("serial")
    public static class StackIsEmptyException extends RuntimeException
    {
        /**
         * Construct
         * 
         * @param message
         *            that describes the error
         */
        public StackIsEmptyException(String message)
        {
            super(message);
        }
    }
}
