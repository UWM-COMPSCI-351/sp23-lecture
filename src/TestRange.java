

import java.util.Collection;

import edu.uwm.cs351.RangeCollection;
import junit.framework.TestCase;

public class TestRange extends TestCase {

	public void test00() {
		Collection<Integer> r = new RangeCollection(1,10);
		for (Integer x : r) {
			System.out.println(x);
		}
		assertFalse(r.isEmpty());
		assertTrue(r.contains(5));
		assertFalse(r.contains(0));
		assertFalse(r.contains(10));
	}
	
	public void test01() {
		Collection<Integer> r = new RangeCollection(10,10);
		assertEquals(0,r.size());
		assertTrue(r.isEmpty());
		r.clear();
	}
}
