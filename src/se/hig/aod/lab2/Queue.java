/*
 * This is free to use in courses at University of Gävle.
 */
package se.hig.aod.lab2;

/**
 * An interface for the generic Abstract Data Type <code>Queue</code>.
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 * @author Magnus Hjelmblom
 * @author Anders Jackson
 * @author Peter Jenke
 * @version 2015-12-04
 * @param <V>
 *            the type of elements to store
 */

public interface Queue<V>
{
    /**
     * Removes all elements from this queue and writes a message on
     * {@link System}.out).
     */
    public void clear();

    /**
     * Checks if this queue is empty.
     * 
     * @return true if queue is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Add an element at the last position in this queue.
     * 
     * @param object
     *            added to this queue.
     */
    public void enqueue(V object);

    /**
     * Removes the first element from this queue. <br>
     * Will throw exception if this queue is empty.
     * 
     * @return the first element.
     */
    public V dequeue();

    /**
     * Returns the first element in this queue, without removing it. <br>
     * Will throw exception if this queue is empty.
     * 
     * @return the first element.
     */
    public V getFront();
}
