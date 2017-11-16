
import java.util.Arrays;

@SuppressWarnings("unchecked")

//generic binary heap class
public class EventScheduler <T extends Comparable<T>>{
	private T[] heap; // binary heap
	private int size;
	private boolean min;
	private final int CAPACITY = 10;


    public EventScheduler(){
        size = 0;
        heap = (T[]) new Comparable[CAPACITY];
        min = true;
	}

	 public EventScheduler(T[] array){
	    size = 0;
	    heap = (T[]) new Comparable[CAPACITY];
	    for (T each : array)
		{
			push(each);
	    }
	}


	public void push(T newEvent){
		try{
			if(size == heap.length - 1){
				heap = this.increaseSize();
			}
			size++;
			heap[size] = newEvent;
			percUp();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//END push


	public T[] increaseSize(){
		return Arrays.copyOf(heap, heap.length + CAPACITY);
	}//end increaseSize

	public T peek(){
		if (isEmpty()) throw new IllegalStateException();
		return heap[1];
	}


	public void percUp(){
		int index = size;
		while(hasParent(index) && (parent(index).compareTo(heap[index]) > 0)){
		   swap(index, parentIndex(index));
		   index = parentIndex(index);
	    }
	}

	public void percDown(){
		int index = 1;
		while (hasLeftChild(index)){
			// find smaller of child values
			int smaller = leftIndex(index);
			if (hasRightChild(index) && heap[leftIndex(index)].compareTo(heap[rightIndex(index)]) > 0)
			{
				smaller = rightIndex(index);
			}
			if (heap[index].compareTo(heap[smaller]) > 0)
			{
				swap(index, smaller);
			}
			else break;

			index = smaller;
		}
	}

    //remove for first item
	public T remove(){
			T result = peek();

			swap(1, size);
			heap[size] = null;
			size--;
			percDown();
			return result;
	}

	public boolean remove(T value)
	{
		for (int i = 0; i < heap.length; i++)
		{
			if (value.equals(heap[i]))
			{
				System.out.println(i);
				swap(i, size);
				heap[size] = null;
				size--;
				//bubbleUp();
				percDown();
				return true;
			}
		}
		return false;
	}


	private int parentIndex(int i){
		return i / 2;
	}

	private T parent(int i){
		return heap[parentIndex(i)];
	}

	private boolean hasParent(int i){
		return i > 1;
	}

	private void swap(int index1, int index2)
		{
			T temp = heap[index1];
			heap[index1] = heap[index2];
			heap[index2] = temp;
	}

	private boolean hasLeftChild(int i){
    	return leftIndex(i) <= size;
	}

	private boolean hasRightChild(int i){
		return rightIndex(i) <= size;
	}

	private int leftIndex(int i){
		return i * 2;
	}

	private int rightIndex(int i){
		return i * 2 + 1;
	}

	public boolean isEmpty(){
			return size == 0;
	}

	public int size(){
		return size;
	}

	public T[] getHeap(){
		return heap;
	}

	@Override
	public String toString(){
	    String retval = "";
		for (T each : heap)
		{
			if (each != null) retval += each + "\n";
		}
		return retval + "\n";
	}

}