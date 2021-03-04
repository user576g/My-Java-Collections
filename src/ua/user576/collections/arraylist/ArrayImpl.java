package ua.user576.collections.arraylist;

import java.util.Iterator;

public class ArrayImpl implements Array {
	
	private Object arr[];
	private int length;
	private int capacity = 10;
	
	public ArrayImpl() {
		arr = new Object[capacity];
	}
	
	public ArrayImpl(int capacity) {
		this.capacity = capacity;
		arr = new Object[capacity];
	}
	
	public void add(Object o) {
		if (length + 1 > capacity) {
			grow();
		}
		arr[length] = o;
		++length;
	}
	
	private void grow() {
		if (capacity < 10) {
			capacity += 10;
		} else {
			capacity *= 1.5;
		}
		
		Object[] newArr = new Object[capacity];
		System.arraycopy(arr, 0, newArr, 0, length);
		arr = newArr;
	}
	// Sets the element at the specified position.
	public void set(int index, Object element) {
		arr[index] = element;
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
			if (element.equals(arr[i])) {
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
			return new IteratorImpl(); //<Object> was crossed out
		}
	
	private class IteratorImpl implements Iterator {
		
		private int currentIndex;
		
		public boolean hasNext() {
			if (currentIndex <= length) {
				return true;
			} else {
				return false;
			}
		}
		
		public Object next() {
			return arr[currentIndex++];
		}
		
	} 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
