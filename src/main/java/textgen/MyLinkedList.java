package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);

		head.next = tail;
		tail.prev = head;
		size = 0;
	}


	private boolean indexIsValid(int index) {
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException();
		}

		return true;
	}

	private LLNode<E> getNodeAt(int index) {
		LLNode<E> nodeAt = head;

		for (int i = 0; i <= index; i++) {
			nodeAt = nodeAt.next;
		}

		return nodeAt;
	}
	
	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		// TODO: Implement this method
		add(size(), element);
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		if (indexIsValid(index)) {
			return getNodeAt(index).data;
		}

		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		// TODO: Implement this method
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}

		if (element == null) {
			throw new NullPointerException();
		}

		LLNode<E> nodeAt = getNodeAt(index);

		LLNode<E> newNode = new LLNode<E>(element);
		newNode.prev = nodeAt.prev;
		newNode.next = nodeAt;
		nodeAt.prev.next = newNode;
		nodeAt.prev = newNode;

		size++;
	}

	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		if (indexIsValid(index)) {

			LLNode<E> nodeAt = getNodeAt(index);

			nodeAt.prev.next = nodeAt.next;
			nodeAt.next.prev = nodeAt.prev;

			size--;

			return nodeAt.data;
		}

		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		// TODO: Implement this method
		if (indexIsValid(index)) {

			if (element == null) {
				throw new NullPointerException();
			}

			LLNode<E> nodeAt = getNodeAt(index);

			E oldData = nodeAt.data;
			nodeAt.data = element;

			return oldData;
		}

		return null;
	}

}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}