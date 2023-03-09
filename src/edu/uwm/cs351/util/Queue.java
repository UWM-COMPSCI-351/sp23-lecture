package edu.uwm.cs351.util;

/**
 * Interface for CS 351 queue classes.
 * @param E element type
 */
public interface Queue<E> extends Cloneable {
	
	/**
	 * Return number of elements in the queue
	 * @return number of elements in queue.
	 */
	public int size();

	/**
	 * Return front element of queue
	 * @return front element of queue without removing it.
	 */
	public E front();
	
	/**
	 * Add element to end of queue.
	 * @param data element to add, must not be null
	 */
	public void enqueue(E data);

	/**
	 * Remove and return the front element
	 * @return former front element
	 */
	public E dequeue();

	/**
	 * Return whether the queue is empty
	 * @return whether the queue is empty
	 */
	public boolean isEmpty();

	/**
	 * Clone this queue for a new queue with same contents.
	 * @return fresh copy of this queue
	 */
	public Queue<E> clone();

}