package edu.uwm.cs351.util;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E>, Cloneable {

	private static class Node<T> {
		T data;
		Node<T> next;
		Node(T d, Node<T> n) { data = d; next = n; }
	}
	
	private Node<E> head, tail;
	
	@Override // required
	public void enqueue(E elem) {
		if (elem == null) throw new NullPointerException();
		Node<E> n = new Node<>(elem, null);
		if (tail != null) {
			tail.next = n;
		} else {
			head = n;
		}
		tail = n;
	}
	
	@Override // required
	public E dequeue() {
		if (head == null) throw new NoSuchElementException("");
		E result = head.data;
		head = head.next;
		if (head == null) tail = null;
		return result;
	}
	
	@Override // required
	public E front() {
		if (head == null) throw new NoSuchElementException("");
		return head.data;
	}
	
	@Override // required
	public int size() {
		int count = 0;
		for (Node<E> n = head; n != null; n = n.next) {
			++count;
		}
		return count;
	}
	
	@Override // required
	public boolean isEmpty() {
		return head == null;
	}
	
	@Override // required
	public LinkedQueue<E> clone() {
		return this; // TODO
	}
}
