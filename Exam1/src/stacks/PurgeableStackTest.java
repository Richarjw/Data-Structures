package stacks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the given data structure.
 *
 * @author Matt Boutell.
 */
public class PurgeableStackTest {
	private static int points = 0;
	private PurgeableStack<Integer> intStack;
	private PurgeableStack<String> stringStack;

	/**
	 * Creates some test cases.
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.intStack = new MyPurgeableStack<Integer>(Integer.class);
		this.stringStack = new MyPurgeableStack<String>(String.class);
	}

	@Test
	public void testPushUsingSize() {
		assertEquals(0, this.intStack.size());
		this.intStack.push(15);
		assertEquals(1, this.intStack.size());
		this.intStack.push(10);
		assertEquals(2, this.intStack.size());
		this.intStack.push(8);
		assertEquals(3, this.intStack.size());
		this.intStack.push(23);
		assertEquals(4, this.intStack.size());

		assertEquals(0, this.stringStack.size());
		this.stringStack.push("nice");
		assertEquals(1, this.stringStack.size());
		this.stringStack.push("work");
		assertEquals(2, this.stringStack.size());
		this.stringStack.push("so");
		assertEquals(3, this.stringStack.size());
		this.stringStack.push("far");
		assertEquals(4, this.stringStack.size());
		points += 2;
	}

	private void fillStacks() {
		this.intStack.push(15);
		this.intStack.push(10);
		this.intStack.push(8);
		this.intStack.push(23);

		this.stringStack.push("nice");
		this.stringStack.push("work");
		this.stringStack.push("so");
		this.stringStack.push("far");
	}

	@Test
	public void testPushUsingToString() {
		fillStacks();
		assertEquals("[23, 8, 10, 15]", this.intStack.toString());
		assertEquals("[far, so, work, nice]", this.stringStack.toString());
		points += 3;
	}

	@Test
	public void testPopAndPushUsingSize() {
		fillStacks();
		assertEquals(23, (int) this.intStack.pop());
		assertEquals(8, (int) this.intStack.pop());
		assertEquals(10, (int) this.intStack.pop());
		this.intStack.push(100);
		this.intStack.push(200);
		assertEquals(200, (int) this.intStack.pop());
		assertEquals(100, (int) this.intStack.pop());
		assertEquals(15, (int) this.intStack.pop());
		this.intStack.push(300);
		assertEquals(300, (int) this.intStack.pop());
		assertTrue(this.intStack.isEmpty());
		points += 6;
	}

	@Test
	public void testPopEmptyStack() {
		try {
			this.intStack.pop();
			fail("Should have thrown an EmptyStack exception");

		} catch (EmptyStackException e) {
			points += 1;
		}
	}

	@Test
	public void testPopUsingToString() {
		fillStacks();
		assertEquals(23, (int) this.intStack.pop());
		assertEquals("[8, 10, 15]", this.intStack.toString());
		assertEquals(8, (int) this.intStack.pop());
		assertEquals("[10, 15]", this.intStack.toString());
		assertEquals(10, (int) this.intStack.pop());
		assertEquals("[15]", this.intStack.toString());
		this.intStack.push(100);
		this.intStack.push(200);
		assertEquals("[200, 100, 15]", this.intStack.toString());
		assertEquals(200, (int) this.intStack.pop());
		assertEquals("[100, 15]", this.intStack.toString());
		assertEquals(100, (int) this.intStack.pop());
		assertEquals("[15]", this.intStack.toString());
		assertEquals(15, (int) this.intStack.pop());
		assertEquals("[]", this.intStack.toString());
		this.intStack.push(300);
		assertEquals("[300]", this.intStack.toString());
		assertEquals(300, (int) this.intStack.pop());
		assertEquals("[]", this.intStack.toString());
		points += 3;
	}

	@Test
	public void testPeekUsingPush() {
		String[] words = "This is a great sentence. The last item pushed is always returned by peek".split(" ");
		for (String word : words) {
			this.stringStack.push(word);
			assertEquals(word, this.stringStack.peek());
		}
		points += 1;
	}

	@Test
	public void testPeekEmptyStack() {
		try {
			this.intStack.peek();
			fail("Should have thrown an EmptyStack exception");

		} catch (EmptyStackException e) {
			points += 1;
		}
	}

	@Test
	public void testIsEmpty() {
		assertTrue(this.intStack.isEmpty());
		assertTrue(this.stringStack.isEmpty());
		this.stringStack.push("an item");
		assertFalse(this.stringStack.isEmpty());
		points += 2;
	}

	@Test
	public void testClearUsingSize() {
		this.intStack.push(15);
		this.intStack.push(10);
		this.intStack.push(8);
		this.intStack.push(23);
		assertEquals(4, this.intStack.size());
		this.intStack.clear();
		assertEquals(0, this.intStack.size());
		// Make sure it can handle new items inserted.
		this.intStack.push(17);
		assertEquals(1, this.intStack.size());
		points += 2;
	}

	@Test
	public void testSize() {
		assertEquals(0, this.intStack.size());
		assertEquals(0, this.stringStack.size());
		stringStack.push("solo");
		assertEquals(1, this.stringStack.size());
		points += 1;
		
	}

	@Test
	public void testPurgeEmptyStack() {
		this.stringStack.purge();
		assertEquals("[]", stringStack.toString());
		points += 1;
	}

	@Test
	public void testPurgeOneElementStack() {
		this.stringStack.push("solo");
		this.stringStack.purge();
		assertEquals("[]", stringStack.toString());
		points += 1;
	}

	@Test
	public void testPurgeTwoElementStack() {
		this.stringStack.push("only");
		this.stringStack.push("two");
		this.stringStack.purge();
		assertEquals("[only]", stringStack.toString());
		points += 1;
	}

	@Test
	public void testPurgeOddNumberOfElements() {
		String[] words = "first second third fourth fifth sixth seventh".split(" ");

		for (String word : words) {
			this.stringStack.push(word);
		}
		// seventh on top.

		this.stringStack.purge();
		assertEquals("[sixth, fourth, second]", stringStack.toString());
		points += 2;
	}

	@Test
	public void testPurgeEvenNumberOfElements() {
		String[] words = "first second third fourth fifth sixth".split(" ");

		for (String word : words) {
			this.stringStack.push(word);
		}
		// sixth on top.

		this.stringStack.purge();
		assertEquals("[fifth, third, first]", stringStack.toString());
		points += 2;
	}

	@Test
	public void testDepth() {
		fillStacks();
		assertEquals(3, this.intStack.depth(15));
		assertEquals(0, this.intStack.depth(23));
		assertEquals(1, this.intStack.depth(8));
		assertEquals(2, this.intStack.depth(10));
		assertEquals(-1, this.intStack.depth(0));
		assertEquals(-1, this.intStack.depth(100));

		assertEquals(2, this.intStack.depth(10));
		assertEquals(3, this.stringStack.depth("nice"));
		assertEquals(2, this.stringStack.depth("work"));
		assertEquals(0, this.stringStack.depth("far"));
		assertEquals(-1, this.stringStack.depth("help"));
		assertEquals(-1, this.stringStack.depth("not"));
		assertEquals(-1, this.stringStack.depth("here"));
		points += 5;
	}

	@Test
	public void testToStringEmpty() {
		assertEquals("[]", this.intStack.toString());
		assertEquals("[]", this.stringStack.toString());
		points += 1;
	}

	@AfterClass
	public static void showPoints() {
		System.out.println(String.format("%2d/35 unit test points", points));
		System.out.println("Up to 5 more points are for meeting efficiency requirements");
	}
}
