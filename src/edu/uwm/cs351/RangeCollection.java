package edu.uwm.cs351;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeCollection extends AbstractCollection<Integer> {

	private final int lo, hi;
	
	public RangeCollection(int i, int j) {
		lo = i;
		hi = j;
	}

	@Override // required
	public Iterator<Integer> iterator() {
		return new MyIterator();
	}

	@Override // required
	public int size() {
		return hi-lo;
	}

	@Override // efficiency
	public boolean contains(Object o) {
		if (!(o instanceof Integer)) return false;
		return lo <= (Integer)o && (Integer)o < hi;
	}

	private class MyIterator implements Iterator<Integer> {
		private int nextToReturn = lo;
		
		@Override //required
		public boolean hasNext() {
			return nextToReturn < hi;
		}

		@Override //required
		public Integer next() {
			if (!hasNext()) throw new NoSuchElementException("no more");
			int result = nextToReturn;
			++nextToReturn;
			return result;
		}
		
	}
}
