package se.hig.aod.lab2;

/**
 * Extension of List that allows index-based operations
 * 
 * 
 * @param <T> type to store
 *  
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
@SuppressWarnings("hiding")
public interface ExtendedList<T> extends List<T>
{
    /**
     * Insert an element at index<br>
     * Negative index starts from the end of the list (-1 is last element,-2 is
     * the one before the last element)<br>
     * If index is greater than list size, the element is put last in the list
     * 
     * @param index
     *            to insert at
     * @param element
     *            to insert
     */
    public void insert(int index, T element);

    /**
     * Remove an element at index<br>
     * Negative index starts from the end of the list (-1 is last element,-2 is
     * the one before the last element)<br>
     * 
     * @param index
     *            to remove at
     * @throws IndexOutOfBoundsException
     *             if out of bounds
     */
    public void remove(int index);

    /**
     * Get element at index<br>
     * Negative index starts from the end of the list (-1 is last element,-2 is
     * the one before the last element)<br>
     * 
     * @param index
     *            to get element from
     * @return element at index
     * @throws IndexOutOfBoundsException
     *             if out of bounds
     */
    public T get(int index);

    /**
     * Exception that signals that we are trying to get an element at an index
     * that does not exist
     * 
     * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
     */
    @SuppressWarnings("serial")
    public static class IndexOutOfBoundsException extends RuntimeException
    {
        /**
         * Construct
         * 
         * @param message
         *            that describes the error
         */
        public IndexOutOfBoundsException(String message)
        {
            super(message);
        }
    }

    /**
     * Exception class that signals if an operation that is not allowed has been
     * done on an empty list.
     * 
     * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
     * @author Magnus Blom
     * @version 2015-11-27
     */
    @SuppressWarnings("serial")
    public static class ListIsEmptyException extends RuntimeException
    {
        /**
         * A constructor tha takes a message about which error has been
         * generated. This can be written to the user when the exception is
         * catched.
         * 
         * @param message
         *            that describes the error
         */
        public ListIsEmptyException(String message)
        {
            super(message);
        }
    }
}
