package queues;

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueFromStacks<T> implements SimpleQueue<T>{
	private static final int INITIAL_CAPACITY = 5;

	private Class<T> type;
	private Stack<T> enStack = new Stack<T>();
	private Stack<T> deStack = new Stack<T>();

	@Override
	public void clear() {
		// makes new empty stacks
		enStack = new Stack();
		deStack = new Stack();
	}

	@Override
	public void enqueue(T item) {
		// puts item on the enqueue stack
		enStack.push(item);
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		// puts everything from enqueue and puts it into dequeue
		// pops off first thing and then puts everything back in order at enqueue
		T temp = null;
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
			while(!enStack.isEmpty()){
				deStack.push(enStack.pop());
			}
			temp = deStack.pop();
			while(!deStack.isEmpty()){
				enStack.push(deStack.pop());
			}
		return temp;
	}

	@Override
	public T peek() throws NoSuchElementException {
		// puts the first item into a dequeue
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		if(deStack.isEmpty()){
			deStack.push(enStack.pop());
		}
		return deStack.peek();
	}

	@Override
	public boolean isEmpty() {
		// checks if both are empty
		if(enStack.isEmpty() && deStack.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		// sums size of both stacks
		return enStack.size() + deStack.size();
	}

	@Override
	public boolean contains(T item) {
		// checks both stacks if it has the item
		if(enStack.contains(item) || deStack.contains(item)){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		// makes sure it is not empty
		if(this.isEmpty()){
			return "[]";
		}
		// dequeue is not empty then it takes everything in dequee puts into
		// enqueue then back to dequeue to be in correct order and then returns
		// the dequeue as a string
		if(!deStack.isEmpty()){
			while(!deStack.isEmpty()){
				enStack.push(deStack.pop());
			}
			while(!enStack.isEmpty()){
				deStack.push(enStack.pop());
			}
			return deStack.toString();
		}
		// if it is all in enqueue then it just needs to print
		return enStack.toString();
	}

	@Override
	public String debugString() {
		// not needed
		
		return null;
	}

}
