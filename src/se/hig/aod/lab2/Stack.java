/*
 * This is free to use in courses at University of Gävle.
 */
package se.hig.aod.lab2;

/**
 * A Java Interface for the generic Abstract Data Type Stack.
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 * @author Magnus Hjelmblom
 * @author Anders Jackson
 * @author Peter Jenke
 * @version 2015-12-04
 * @param <V>
 *            the type of elements to store
 */

public interface Stack<V>
{
    /**
     * Removes all elements from this stack and writes a message on
     * {@link System}.out).
     */
    public void clear();

    /**
     * Checks if this stack is empty.
     * 
     * @return true if stack is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Adds an element on the top of this stack.
     * 
     * @param object
     *            - the object added on the top of the stack.
     */
    public void push(V object);

    /**
     * Removes the top element of this stack. <br>
     * Will throw exception if the stack is empty.
     * 
     * @return top element of the stack.
     */
    public V pop();

    /**
     * Returns the top element of this stack, without removing it. <br>
     * Will throw exception if the stack is empty.
     * 
     * @return top element of the stack.
     */
    public V top();
}
