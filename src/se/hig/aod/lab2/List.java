package se.hig.aod.lab2;

/**
 * A Java interface for the Abstract Data Type List.
 * 
 * @author Magnus Blom
 * @author Anders Jackson
 * @author Peter Jenke
 * @version 2014-02-10
 */

public interface List<T>
{
	/**
	 * Check if the list is empty.
	 * 
	 * @return true if the list is empty, otherwise false.
	 */
	public boolean isEmpty ();

	/**
	 * Clear the list of all elements stored in it.
	 */
	public void clear ();

	/**
	 * Return the number of elements in the list.
	 * 
	 * @return Number of elements in the list.
	 */
	public int numberOfElements ();

	/**
	 * Add an element at the first possition in the list.
	 * 
	 * @param t
	 *            objekt to add.
	 */
	public void insertFirst (T t);

	/**
	 * Add an element at the last possition in the list.
	 * 
	 * @param t
	 *            objekt to add.
	 */
	public void insertLast (T t);

	/**
	 * Remove the first element from the list.
	 * 
	 * @return the first element from the list.
	 */
	public T removeFirst ();

	/**
	 * Remove the last element from the list.
	 * 
	 * @return the last element from the list.
	 */
	public T removeLast ();

	/**
	 * Peek at the first element in the list, without removing it.
	 * 
	 * @return the first element in the list.
	 */
	public T getFirst ();

	/**
	 * Peek at the last element in the list, without removing it.
	 * 
	 * @return the last element in the list.
	 */
	public T getLast ();

	/**
	 * Check if the element in t exist somewhere in the list.
	 * 
	 * @param t
	 *            the element.
	 * @return true if the element exist somewhere in the list, otherwise false.
	 */
	public boolean contains (T t);

	/**
	 * Write the contents of the list to System.out using iteration.
	 * 
	 * Skriv ut listan till System.out (med hjälp av iteration).
	 */
	public void printList ();

	/**
	 * Write the contents of the list to System.out using recursion.
	 */
	public void printListR ();

	/**
	 * Write the contents of the list to System.out in reversed order.
	 */
	public void reversePrintList ();
}
