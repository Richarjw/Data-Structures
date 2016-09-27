package stacks;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * An implementation of a PurgeableStack.
 * 
 * @author Done: Jack Richard.
 * @param <T>
 *            Any type you want this stack to store.
 */
public class MyPurgeableStack<T> implements PurgeableStack<T> {
	// DONE: Declare this class to implement PurgeableStack<T>, then add the
	// missing methods (Eclipse will help with all EXCEPT toString,
	// which you must add that manually).

	// TODO: The javadoc for overridden methods is in the PurgeableStack
	// interface. Read it!

	// If you use a growable array implementation, you'll probably want these
	// and the code in the constructor, so I include them for your convenience.
	// If you don't, feel free to delete them.
	//private static final int INITIAL_CAPACITY = 5;
	//private T[] array;
	private Class<T> type;
	private Stack<T> stack;

	// You may add any other fields you like.

	/**
	 * Creates an empty stack
	 * 
	 * @param type
	 *            So that an array of this type can be constructed.
	 */
	@SuppressWarnings("unchecked")
	public MyPurgeableStack(Class<T> type) {
		// Please keep the type parameter so the tests will run (it's needed for
		// a growable array implementation), but you may ignore it if you choose
		// a different implementation.
		// Otherwise, you may modify this method however you see fit.
		this.type = type;
		//this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.stack = new Stack<>();

	}

	@Override
	public void push(T item) {
		this.stack.push(item);

	}

	@Override
	public T pop() throws EmptyStackException {
		if (this.stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return this.stack.pop();
	}

	@Override
	public T peek() throws EmptyStackException {
		if (this.stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return this.stack.peek();
	}

	@Override
	public boolean isEmpty() {
		return this.stack.isEmpty();
	}

	@Override
	public int size() {
		return this.stack.size();
	}

	@Override
	public void clear() {
		while (!this.stack.isEmpty()) {
			this.stack.pop();
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Stack<T> temp = new Stack<>();
		T tempObj = null;
		if (this.stack.isEmpty()) {
			return "[]";
		}
		sb.append("[");
		while (!this.stack.isEmpty()) {
			tempObj = this.stack.pop();
			sb.append(tempObj);
			sb.append(", ");
			temp.push(tempObj);
		}
		while (!temp.isEmpty()) {
			this.stack.push(temp.pop());
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}

	@Override
	public int depth(T item) {
		int depth = 0;
		Stack<T> temp = new Stack<>();
		if (this.stack.isEmpty()) {
			return -1;
		}
		while (this.stack.peek() != item) {
			if (this.stack.size() == 1) {
				while (!temp.isEmpty()) {
					this.stack.push(temp.pop());
				}
				return -1;
			}
			temp.push(this.stack.pop());
			depth++;
		}
		while (!temp.isEmpty()) {
			this.stack.push(temp.pop());
		}
		return depth;
	}

	@Override
	public void purge() {
		Stack<T> temp = new Stack<>();
		int count = 2;
		while (!this.stack.isEmpty()) {
			if (count % 2 == 0) {
				this.stack.pop();
			} else {
				temp.push(this.stack.pop());
			}
			count++;
		}
	
		while (!temp.isEmpty()) {
			this.stack.push(temp.pop());
		}
	}
}
