/**
 * 
 */
package textgen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
		shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}

	/**
	 * Test if the get method is working correctly.
	 */
	/*
	 * You should not need to add much to this method. We provide it as an
	 * example of a thorough test.
	 */
	@Test
	public void testGet() {
		// test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		
		try {
			shortList.get(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
	}

	/**
	 * Test removing an element from the list. We've included the example from
	 * the concept challenge. You will want to add more tests.
	 */
	@Test
	public void testRemove() {
		// refuse to remove from a negative index
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// refuse to remove from an index greater than list size -1
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// remove from a valid index
		int elementRemovedFromFirstPosition = list1.remove(0);
		assertEquals("Remove: check elementRemovedFromFirstPosition is correct ", 65, elementRemovedFromFirstPosition);
		assertEquals("Remove: check new first element is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

		// TODO: Add more tests here
		int elementRemovedFromTheMiddle = longerList.remove(5);
		assertEquals("Remove: check elementRemovedFromTheMiddle is correct ", 5, elementRemovedFromTheMiddle);
		assertEquals("Remove: check new 5th element is correct ", (Integer) 6, longerList.get(5));
		assertEquals("Remove: check size is correct ", 9, longerList.size());

		int elementRemovedFromTheLastPosition = longerList.remove(8);
		assertEquals("Remove: check elementRemovedFromTheLastPosition is correct ", 9, elementRemovedFromTheLastPosition);
		try {
			longerList.get(8);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		assertEquals("Remove: check size is correct ", 8, longerList.size());
	}

	/**
	 * Test adding an element into the end of the list, specifically public
	 * boolean add(E element)
	 */
	@Test
	public void testAddEnd() {
		// TODO: implement this test
		assertEquals("Check prior ", "A", shortList.get(0));
		assertEquals("Check content ", "B", shortList.get(1));
		try {
			shortList.get(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		assertEquals("Check size is correct ", 2, shortList.size());
		
		// refuse to add a null value
		try {
			shortList.add(1, null);
			fail("Check for NullPointer");
		} catch (NullPointerException e) {

		}

	}

	/** Test the size of the list */
	@Test
	public void testSize() {
		assertEquals("Check size is correct ", 0, emptyList.size());
		assertEquals("Check size is correct ", 2, shortList.size());
		assertEquals("Check size is correct ", 3, list1.size());
		assertEquals("Check size is correct ", LONG_LIST_LENGTH, longerList.size());
	}

	/**
	 * Test adding an element into the list at a specified index, specifically:
	 * public void add(int index, E element)
	 */
	@Test
	public void testAddAtIndex() {
		// TODO: implement this test
		// refuse to add to a negative index
		try {
			shortList.add(-1, "C");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// refuse to add to an index greater than the list size
		try {
			shortList.add(3, "C");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			emptyList.add(1, 79);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// refuse to add a null value
		try {
			shortList.add(1, null);
			fail("Check for NullPointer");
		} catch (NullPointerException e) {

		}

		// add to valid indexes
		
		// add to the first position
		shortList.add(0, "C");
		// content at the index must match the element added
		assertEquals("Check content ", "C", shortList.get(0));
		// Check item before
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		assertEquals("Check item after ", "A", shortList.get(1));
		assertEquals("Check list size is correct ", 3, shortList.size());
		
		// add to the middle
		list1.add(1, 35);
		// content at the index must match the element added
		assertEquals("Check content ", (Integer) 35, list1.get(1));
		assertEquals("Check item before ", (Integer) 65, list1.get(0));
		assertEquals("Check item after ", (Integer) 21, list1.get(2));
		assertEquals("Check list size is correct ", 4, list1.size());
		
		// add after last position
		longerList.add(LONG_LIST_LENGTH, LONG_LIST_LENGTH);
		// content at the index must match the element added
		assertEquals("Check content ", (Integer) LONG_LIST_LENGTH, longerList.get(LONG_LIST_LENGTH));
		assertEquals("Check item before ", (Integer) 9, longerList.get(9));
		// Check item after
		try {
			longerList.get(11);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		assertEquals("Check list size is correct ", 11, longerList.size());
	}

	/** Test setting an element in the list */
	@Test
	public void testSet() {
		// TODO: implement this test
		// refuse to set at a negative index
		try {
			shortList.set(-1, "C");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// refuse to set at an index greater than list size -1
		try {
			shortList.set(2, "C");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// refuse to set a null value
		try {
			shortList.set(1, null);
			fail("Check for NullPointer");
		} catch (NullPointerException e) {

		}

		// set at a valid index
		assertEquals("Set: check the value to be changed is correct ", "A", shortList.set(0, "C"));
		assertEquals("Set: check the new value is correct ", "C", shortList.get(0));
		// must not change the list size
		assertEquals("Set: check list size is correct ", 2, shortList.size());
	}

	// TODO: Optionally add more test methods.

}