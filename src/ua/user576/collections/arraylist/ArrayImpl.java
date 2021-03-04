package ua.user576.collections.arraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
	
	private Object[] arr;
	private int length;
	public static final int INITIAL_CAPACITY = 10;
	private int capacity = INITIAL_CAPACITY;
	public static final double GROWTH_RATE = 1.5;
	
	public ArrayImpl() {
		arr = new Object[capacity];
	}
	
	public ArrayImpl(int capacity) {
		this.capacity = capacity;
		arr = new Object[capacity];
	}
	
	@Override
	public void add(Object o) {
		if (length + 1 > capacity) {
			grow();
		}
		arr[length] = o;
		++length;
	}
	
	private void grow() {
		if (capacity < INITIAL_CAPACITY) {
			capacity += INITIAL_CAPACITY;
		} else {
			capacity *= GROWTH_RATE;
		}
		
		Object[] newArr = new Object[capacity];
		System.arraycopy(arr, 0, newArr, 0, length);
		arr = newArr;
	}
	// Sets the element at the specified position.
	public void set(int index, Object element) {
		arr[index] = element;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < length - 1; ++i) {
			sb.append(arr[i] + ", ");
		}
		if (length != 0) {
			sb.append(arr[length - 1]);
		}
		sb.append("]");
		return sb.toString();
	}

	// Returns the element at the specified position.
	public Object get(int index) {
		return arr[index];
	}
	
	// Returns the index of the first occurrence of the specified element,
	// or -1 if this array does not contain the element.
	// (use 'equals' method to check an occurrence)
	public int indexOf(final Object element) {
		for (int i = 0; i < length; ++i) {
			if (element == arr[i]) {
				return i;
			}
		}
		return -1;
	}
	
	// Removes the element at the specified position.
	public void remove(int i) {
		if (i >= length || i < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			int numMoved = length - i - 1;
			System.arraycopy(arr, i + 1, arr, i, numMoved);
			--length;
			arr[length] = null;			
		}
	}
	
	// Removes all of the elements.
	public void clear() {
		for (int i = 0; i < length; ++i) {
			arr[i] = null;
		}
		length = 0;
	}
	
	// Returns the number of elements.
	public int size() {
		return length;
	}
	
	public Iterator<Object> iterator() {
			return new IteratorImpl(); 
		}
	
	private class IteratorImpl implements Iterator {
		
		private int currentIndex;
		private boolean isRemovingAvailable;
		
		public boolean hasNext() {
			return (currentIndex < length);
		}
		
		public Object next() {
			if(!hasNext()){
			      throw new NoSuchElementException();
			}
			isRemovingAvailable = true;
			return arr[currentIndex++];
		}

		@Override
		public void remove() {
			if (isRemovingAvailable) {
				ArrayImpl.this.remove(currentIndex - 1);
				--currentIndex;
			} else {
				throw new IllegalStateException();
			}
			isRemovingAvailable = false;
		}
		
	} 
}