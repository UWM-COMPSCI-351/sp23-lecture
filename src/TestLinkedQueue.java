
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import edu.uwm.cs351.util.LinkedQueue;
import edu.uwm.cs351.util.Queue;
import junit.framework.TestCase;


public class TestLinkedQueue extends TestCase {
	protected void assertException(Class<? extends Throwable> c, Runnable r) {
		try {
			r.run();
			assertFalse("Exception should have been thrown",true);
		} catch (RuntimeException ex) {
			if (!c.isInstance(ex)) {
				ex.printStackTrace();
			}
			assertTrue("should throw exception of " + c + ", not of " + ex.getClass(), c.isInstance(ex));
		}
	}

	/**
	 * Print a queue as a square bracket delimited comma-separate series of elements.
	 * The front of the queue is to the left, the rear to the right.
	 * @param q queue to print
	 * @return a string for the queue
	 */
	protected String toString(Queue<?> q) {
		if (q == null) return "[]";
		q = q.clone();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean first = true;
		while (!q.isEmpty()) {
			if (first) first = false;
			else sb.append(",");
			sb.append(q.dequeue());
		}
		sb.append("]");
		return sb.toString();
	}
	
	protected String asString(Supplier<?> sup) {
		try {
			Object x = sup.get();
			if (x instanceof Queue<?>) return toString((Queue<?>) x);
			return ""+x;
		} catch (RuntimeException ex) {
			return ex.getClass().getSimpleName();
		}
	}
	
	LinkedQueue<Integer> queue;

	@Override
	public void setUp(){
		try {
			assert 1/0 == 42 : "OK";
			System.err.println("Assertions must be enabled to use this test case.");
			System.err.println("In Eclipse: add -ea in the VM Arguments box under Run>Run Configurations>Arguments");
			assertFalse("Assertions must be -ea enabled in the Run Configuration>Arguments>VM Arguments",true);
		} catch (ArithmeticException ex) {
			// GOOD
		}
		queue = new LinkedQueue<>();
	}

	
	
	/// Simple tests
	
	public void test00() {
		assertTrue(queue.isEmpty());
	}
	
	public void test01() {
		assertEquals(0, queue.size());
	}
	
	public void test02() {
		assertException(NoSuchElementException.class, () -> queue.front());
	}
	
	
	public void test10() {
		queue.enqueue(10);
		assertFalse(queue.isEmpty());
	}
	
	public void test11() {
		queue.enqueue(11);
		assertEquals(1, queue.size());
	}
	
	public void test12() {
		queue.enqueue(12);
		assertEquals(Integer.valueOf(12), queue.front());
	}
	
	public void test13() {
		assertException(NullPointerException.class, () -> queue.enqueue(null));
	}
	
	
	public void test20() {
		queue.enqueue(2);
		queue.enqueue(0);
		assertFalse(queue.isEmpty());
	}
	
	public void test21() {
		queue.enqueue(2);
		queue.enqueue(1);
		assertEquals(Integer.valueOf(2), queue.front());
	}
	
	public void test22() {
		queue.enqueue(2);
		queue.enqueue(2);
		assertEquals(2, queue.size());
	}
	
	public void test23() {
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(2);
		assertEquals(3, queue.size());
	}
	
	
	public void test30() {
		assertException(NoSuchElementException.class, () -> queue.dequeue());
	}
	
	public void test31() {
		queue.enqueue(31);
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}
	
	public void test32() {
		queue.enqueue(3);
		queue.enqueue(2);
		assertEquals(Integer.valueOf(3), queue.dequeue());
	}
	
	public void test33() {
		queue.enqueue(3);
		queue.enqueue(3);
		queue.enqueue(3);
		queue.dequeue();
		assertEquals(2, queue.size());
	}
	
	public void test34() {
		queue.enqueue(3);
		queue.enqueue(4);
		queue.dequeue();
		queue.enqueue(5);
		queue.dequeue();
		queue.enqueue(6);
		assertEquals(Integer.valueOf(5), queue.dequeue());
	}
	
	
	public void test40() {
		Queue<Integer> c = queue.clone();
		assertEquals(0, c.size());
	}
	
	public void test41() {
		queue.enqueue(41);
		Queue<Integer> c = queue.clone();
		assertEquals(Integer.valueOf(41), c.front());
	}
	
	public void test42() {
		queue.enqueue(42);
		Queue<Integer> c = queue.clone();
		c.dequeue();
		c.enqueue(24);
		c.dequeue();
		c.enqueue(0);
		assertEquals(Integer.valueOf(42), queue.front());
	}
	
	public void test43() {
		queue.enqueue(4);
		queue.enqueue(3);
		Queue<Integer> c = queue.clone();
		queue.enqueue(43);
		queue.dequeue();
		queue.enqueue(0);
		queue.dequeue();
		queue.enqueue(34);
		queue.dequeue();
		queue.enqueue(0);
		assertEquals(Integer.valueOf(4), c.front());
	}
	
	
	

}
