package edu.uwm.cs351;

public class LinkedQueue<E> implements Queue<E>, Cloneable {

	@Override // required
	public void enqueue(E elem) {
		// TODO
	}
	
	@Override // required
	public E dequeue() {
		return null; // TODO
	}
	
	@Override // required
	public E front() {
		return null; // TODO
	}
	
	@Override // required
	public int size() {
		return -1; // TODO
	}
	
	@Override // required
	public boolean isEmpty() {
		return false;
	}
	
	@Override // required
	public LinkedQueue<E> clone() {
		return this; // TODO
	}
}
