package queues;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A circular queue that grows as needed.
 * 
 * @author Matt Boutell and Jack Richard and Ryan Coffman
 * @param <T>
 */
public class GrowableCircularArrayQueue<T> implements SimpleQueue<T> {
	// DONE: Declare this class to implement SimpleQueue<T>, then add the
	// missing methods (Eclipse will help).
	// DONE: The javadoc for overridden methods is in the MyQueue interface.
	// Read it!

	private static final int INITIAL_CAPACITY = 5;

	private T[] array;
	private Class<T> type;
	private int head;
	private int tail;

	/**
	 * Creates an empty queue with an initial capacity of 5
	 * 
	 * @param type
	 *            So that an array of this type can be constructed.
	 */
	@SuppressWarnings("unchecked")
	public GrowableCircularArrayQueue(Class<T> type) {
		this.type = type;
		// This is a workaround due to a limitation Java has with
		// constructing generic arrays.
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.head = 0;
		this.tail = 0;
	}

	/**
	 * Displays the contents of the queue in insertion order, with the
	 * most-recently inserted one last, in other words, not wrapped around. Each
	 * adjacent pair will be separated by a comma and a space, and the whole
	 * contents will be bounded by square brackets. See the unit tests for
	 * examples.
	 */
	@Override
	public String toString() {
		// DONE: implement this method
		String string = "[";
		if (this.isEmpty()) {
			return "[]";
			//if the head is farther than the tail, you must wrap around.
			//so get everything from the head to the end, then wrap around
			//until the tail.
		} else if (this.head > this.tail) {
			for (int i = this.head; i < this.array.length; i++)
				string += this.array[i] + ", ";
			for (int i = 0; i < this.tail; i++) {
				string += this.array[i] + ", ";
			}
			string += this.array[this.tail] + "]";
		} else if (this.tail > this.head) {
			for (int i = this.head; i <= this.tail; i++) {
				if (i == this.tail)
					string += this.array[i] + "]";
				else
					string += this.array[i] + ", ";
			}

		}
		return string;
	}

	@Override
	public void clear() {
		// DONE Auto-generated method stub.
		//creates a new instance of an array with reset head and tail
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.head = 0;
		this.tail = 0;
	}

	@Override
	public void enqueue(T item) {
		//adds the item in the most viable spot.
		// DONE Auto-generated method stub.
		boolean isAdded = false;
		//checks if empty
		if (this.isEmpty()) {
			this.array[0] = item;
			this.tail++;
		//checks if full
		} else if (this.isFull()) {
			this.resize();
			this.array[this.tail + 1] = item;
			this.tail++;
		} else if (this.head != 0) {
			for (int i = this.head; i < this.array.length; i++) {
				if (this.array[i] == null) {
					this.array[i] = item;
					this.tail = i;
					isAdded = true;
					break;
				}
			}
			if(!isAdded) {
				for (int i = 0; i < this.head; i++) {
					if (this.array[i] == null) {
						this.array[i] = item;
						this.tail = i;
						break;
					}
				}
			}
		} else if (this.head == 0) {
			for (int i = 0; i < this.array.length; i++) {
				if (this.array[i] == null) {
					this.array[i] = item;
					this.tail = i;
					break;
				}

			}
		}

	}

	@Override
	public T dequeue() throws NoSuchElementException {
		// DONE Auto-generated method stub.
		if (!this.isEmpty()) {
			T temp = this.array[this.head];
			this.array[this.head] = null;
			this.head++;
			return temp;
		}
		throw new NoSuchElementException();

	}

	@Override
	public T peek() throws NoSuchElementException {
		// DONE Auto-generated method stub.
		if (this.array[this.head] == null)
			throw new NoSuchElementException();
		return this.array[this.head];
	}

	@Override
	public boolean isEmpty() {
		// DONE Auto-generated method stub.
		for (int i = 0; i < this.array.length; i++) {
			if (this.array[i] != null) {
				return false;
			}
		}
		return true;
	}

	public void resize() {
		T[] newarg = (T[]) Array.newInstance(this.type, this.array.length * 2);
		if (this.tail > this.head) {
			for (int i = 0; i < this.array.length; i++) {
				newarg[i] = this.array[i];
			}
		} else if (this.head > this.tail) {
			for (int i = this.head; i < this.array.length; i++) {
				newarg[i - this.head] = this.array[i];
			}
			for (int i = 0; i <= this.tail; i++) {
				newarg[this.array.length - this.head + i] = this.array[i];
			}
		}
		int newhead = 0;
		int newtail = this.array.length - 1;
		this.array = newarg;
		this.head = newhead;
		this.tail = newtail;
	}

	public boolean isFull() {
		for (int i = 0; i < this.array.length; i++) {
			if (this.array[i] == null)
				return false;
		}
		return true;
	}

	@Override
	public int size() {
		// DONE Auto-generated method stub.
		int count = 0;
		for (int i = 0; i < this.array.length; i++) {
			if (this.array[i] != null) {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean contains(T item) {
		for (int i = 0; i < this.array.length; i++) {
			if (this.array[i] == null) {
				continue;
			}
			if (this.array[i].equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String debugString() {
		// DONE Auto-generated method stub.
		return null;
	}

}
