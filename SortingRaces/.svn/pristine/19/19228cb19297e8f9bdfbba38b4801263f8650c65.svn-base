
public class BinaryHeap<T extends Comparable<? super T>> {
	public T[] heap;
	public int size;

	@SuppressWarnings("unchecked")
	public BinaryHeap() {
		size = 0;
		this.heap = (T[]) new Comparable[10];

	}

	@SuppressWarnings("unchecked")
	public void insert(T element) {
		if (this.size+1 >= heap.length) {
			T[] temp = heap;
			heap = (T[]) new Comparable[heap.length * 2];
			for (int i = 0; i < temp.length; i++) {
				heap[i] = temp[i];
			}
		}
		heap[this.size + 1] = element;
		size++;
		int index = size;
		while (index > 1 && heap[index / 2].compareTo(element) > 0) {
			heap[0] = heap[index / 2];
			heap[index / 2] = element;
			heap[index] = heap[0];
			heap[0] = null;
			index = index/2;
		}
	}

	public T deleteMin() {
		if (this.size == 0) {
			return null;
		}
		T min = heap[1];
		heap[1] = heap[size];
		heap[size] = null;
		size = size - 1;
		if (this.size == 0) {
			return min;
		}
		percolateDown(1);
		return min;
	}
	
	public void percolateDown(int index){
		while (2 * index <= size) {
			if (2 * index + 1 <= size && heap[2 * index].compareTo(heap[2 * index + 1]) > 0) {
				if (heap[index].compareTo(heap[2 * index + 1]) > 0) {
					heap[0] = heap[2 * index + 1];
					heap[2 * index + 1] = heap[index];
					heap[index] = heap[0];
					heap[0] = null;
					index = 2*index + 1;
				} else
					break;
			}
			else {
			if (heap[index].compareTo(heap[2 * index]) > 0) {
				heap[0] = heap[2 * index];
				heap[2 * index] = heap[index];
				heap[index] = heap[0];
				heap[0] = null;
				index = 2*index;
			} else
				break;
			}
		}
	}

	public String toString() {
		String s = "";
		s += "[";
		for (int i = 0; i <= size; i++) {
			s += heap[i] + ", ";
		}
		s = s.substring(0, s.length() - 2);
		s += "]";
		return s;
	}
	
	private void buildHeap(){
		for(int i = size/2; i>0; i--){
			percolateDown(i);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void sort(T[] j){
		for(int i = 1; i < j.length+1; i ++){
			heap[i] = j[i-1];
			if(i>=heap.length-1){
				T[] temp = heap;
				heap = (T[]) new Comparable[heap.length * 2];
				for (int k = 0; k < temp.length; k++) {
					heap[k] = temp[k];
				}
			}
		}
		size = j.length;
		buildHeap();
		
		T[] sorted = (T[]) new Comparable[size + 1];
		int ssize = size;
		for(int i = 1; i <= ssize; i ++){
			sorted[i] = this.deleteMin();
		}

		heap = sorted;
		size = ssize;
		for(int i = 1; i < j.length+1; i ++){
			j[i-1] = heap[i];
		}
		
	}
}

