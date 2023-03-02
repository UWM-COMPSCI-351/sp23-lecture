package edu.uwm.cs351;

import java.util.AbstractCollection;
// ### \subsection{Extra Imports}
import java.util.ConcurrentModificationException; //##
import java.util.Iterator;
import java.util.NoSuchElementException; // ##

/**
 * A variant of the ElementTypeSeq ADT that follows the Collection model.
 * In particular, it has no sense of a current element.
 * All access to elements by the client must be through the iterator.
 * The {@link #add(ElementType)} method should add at the end of the collection.
 * The {@link #iterator()} will satisfy the extended {@link AddableIterator} interface.
 */
public class ArrayCollection<ElementType> extends AbstractCollection<ElementType> implements Cloneable {
	private boolean report(String error) {
		System.out.println("Invariant error: " + error);
		return false;
	}
	
	@SuppressWarnings("unchecked") // evil snicker
	private ElementType[] makeArray(int cap) {
		return (ElementType[]) new Object[cap]; // lying
	}
	
	// ### \subsection{Class ElementTypeCollection}
	// #(
	private static final int INITIAL_CAPACITY = 1;

	public ElementType[] data;
	private int manyItems;

	private int version;


	private boolean wellFormed() {
		// Check the invariant.

		// 1. data array is never null
		if (data == null) return report("data is null"); // test the NEGATION of the condition

		// 2. The data array has at least as many items in it as manyItems
		//    claims the collection has
		if (data.length < manyItems) return report("data is too short");

		// If no problems discovered, return true
		return true;
	}

	/**
	 * Initialize an empty particle collection with an initial capacity of INITIAL_CAPACITY. 
	 * The {@link #add(ElementType)} method works
	 * efficiently (without needing more memory) until this capacity is reached.
	 * @param - none
	 * @postcondition
	 *   This collection has an initial capacity of INITIAL_CAPACITY
	 **/   
	public ArrayCollection() {
		this(INITIAL_CAPACITY);
	}

	/**
	 * Initialize an empty collection with a specified capacity.
	 * The {@link #add(ElementType)} method works
	 * efficiently (without needing more memory) until this capacity is reached.
	 * @param capacity
	 *   The initial capacity of this collection, must not be negative
	 * @postcondition
	 *   This collection is empty, has capacity requested.
	 * @throws IllegalArgumentException
	 *    If the capacity is out of the legal range.
	 **/   
	public ArrayCollection(int capacity) {
		if (capacity < 0) throw new IllegalArgumentException("negative capacity not allowed");
		data = makeArray(capacity);
		assert wellFormed() : "constructor did not satisfy invariant!";
	}


	/**
	 * Add a new element to the end of this collection. 
	 * If the new element would take this collection beyond its current capacity,
	 * then the capacity is increased before adding the new element.
	 * @param element
	 *   the new element that is being added
	 * @postcondition
	 *   A new copy of the element has been added to the end of this collection.
	 **/
	@Override // implementation
	public boolean add(ElementType element)
	{
		assert wellFormed() : "invariant failed at start of insert";
		ensureCapacity(manyItems+1);

		data[manyItems] = element;
		++manyItems;
		++version;

		assert wellFormed() : "invariant failed at end of insert";
		return true;
	}

	/**
	 * Change the current capacity of this collection as needed so that
	 * the capacity is at least as big as the parameter.
	 * This code must work correctly and efficiently if the minimum
	 * capacity is (1) smaller or equal to the current capacity (do nothing)
	 * (2) at most double the current capacity (double the capacity)
	 * or (3) more than double the current capacity (new capacity is the
	 * minimum passed).
	 * @param minimumCapacity
	 *   a lower bound on the needed capacity for this collection
	 * @postcondition
	 *   This collection's capacity has been changed to at least minimumCapacity.
	 *   If the capacity was already at or greater than minimumCapacity,
	 *   then the capacity is left unchanged.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for: new array of minimumCapacity elements.
	 **/
	private void ensureCapacity(int minimumCapacity)
	{
		if (data.length >= minimumCapacity) return;
		int newCap = data.length*2;
		if (newCap < minimumCapacity) newCap = minimumCapacity;
		ElementType[] newData = makeArray(newCap);
		for (int i=0; i < manyItems; ++i) {
			newData[i] = data[i];
		}
		data = newData;
		return;
	}

	/**
	 * Generate a copy of this collection.
	 * @param - none
	 * @return
	 *   The return value is a copy of this collection. Subsequent changes to the
	 *   copy will not affect the original, nor vice versa.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for creating the clone.
	 **/ 
	@SuppressWarnings("unchecked") // actually just fine
	@Override // extend implementation
	public ArrayCollection<ElementType> clone( ) { 
		assert wellFormed() : "invariant failed at start of clone";
		ArrayCollection<ElementType> answer;

		try
		{
			answer = (ArrayCollection<ElementType>) super.clone( );
		}
		catch (CloneNotSupportedException e)
		{  // This exception should not occur. But if it does, it would probably
			// indicate a programming error that made super.clone unavailable.
			// The most common error would be forgetting the "Implements Cloneable"
			// clause at the start of this class.
			throw new RuntimeException
			("This class does not implement Cloneable");
		}

		// all that is needed is to clone the data array.
		// (Exercise: Why is this needed?)
		answer.data = data.clone( );

		assert wellFormed() : "invariant failed at end of clone";
		assert answer.wellFormed() : "invariant on answer failed at end of clone";
		return answer;
	}

	@Override // required
	public Iterator<ElementType> iterator() {
		assert wellFormed() : "invariant broken in iterator()";
		return new MyIterator();
	}

	@Override // required
	public int size() {
		assert wellFormed() : "invariant broken in size()";
		return manyItems;
	}

	@Override // efficiency
	public void clear() {
		assert wellFormed() : "invariant broken in clear()";
		if (manyItems == 0) return;
		++version;
		manyItems = 0;
		assert wellFormed() : "Invariant broken by clear()";
	}
	// #)
	// TODO: Add all the contents here.
	// Remember:
	// - All public methods not marked @Override must be fully documented with javadoc
	// - You need to define and check the data structure invariant
	//   (essentially the same as in Homework #2)
	// - You should define a nested iterator class called MyIterator (with its own data structure), 
	//   and then the iterator() method simply returns a new instance.
	// You are permitted to copy in any useful code/comments from the Homework #2 solution.
	// But do not include any of the cursor-related methods, and in particular,
	// make sure you have no "currentIndex" field.

	private class MyIterator // TODO: what should this implement?	
	implements Iterator<ElementType> // ### \subsection{Iterator class implements}
	{
		// #(# \subsection{Iterator class data structure}
		private int currentIndex;
		private boolean isCurrent;
		private int colVersion = version;

		// #)
		// The nested MyIterator class should use the following
		// invariant checker:
		public boolean wellFormed() {
			if (!ArrayCollection.this.wellFormed()) return false;
			if (version != colVersion) return true; // not my fault if invariant broken
			if (currentIndex < 0 || currentIndex > manyItems) return report("current out of range: " + currentIndex + " not in range [0," + manyItems + "]");
			if (isCurrent && currentIndex == manyItems) return report("not current at end");
			return true;
		}
		// #(# \subsection{Iterator class contents}
		
		
		private void checkVersion() {
			if (colVersion != version) throw new ConcurrentModificationException("stale iterator");
		}

		MyIterator() {
			currentIndex = 0;
			isCurrent = false;
			assert wellFormed() : "iterator invariant broken";
		}
		
		@Override // required
		public boolean hasNext() {
			assert wellFormed() : "invariant broken in hasNext";
			checkVersion();
			return (isCurrent ? currentIndex + 1 : currentIndex) < manyItems;
		}

		@Override // required
		public ElementType next() {
			assert wellFormed() : "invariant broken in next";
			if (!hasNext()) throw new NoSuchElementException("no more!");
			if (isCurrent) ++currentIndex;
			else isCurrent = true;
			ElementType result = data[currentIndex];
			assert wellFormed() : "invariant broken by next";
			return result;
		}

		
		@Override // implementation
		public void remove() {
			assert wellFormed() : "invariant broken in remove";
			checkVersion();
			if (!isCurrent) throw new IllegalStateException("cannot remove now");
			for (int i=currentIndex; i+1 < manyItems; ++i) {
				data[i] = data[i+1];
			}
			isCurrent = false;
			--manyItems;
			colVersion = ++version;
			assert wellFormed() : "invariant broken by remove";
		}
		// #)
	}
}
